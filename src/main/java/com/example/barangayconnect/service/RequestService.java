package com.example.barangayconnect.service;

import com.example.barangayconnect.dto.RequestDto;
import com.example.barangayconnect.dto.RequestStatusDto;
import com.example.barangayconnect.dto.UploadedFileResponseDto; // ✅ USED
import com.example.barangayconnect.model.Request;
import com.example.barangayconnect.model.UploadedFile;
import com.example.barangayconnect.repository.RequestRepository;
import com.example.barangayconnect.repository.UploadedFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Base64;                 // ✅ ADDED
import java.util.List;
import java.util.stream.Collectors;      // ✅ USED

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UploadedFileRepository uploadedFileRepository;

    // ⛔ UNCHANGED
    public RequestService(
            RequestRepository requestRepository,
            UploadedFileRepository uploadedFileRepository
    ) {
        this.requestRepository = requestRepository;
        this.uploadedFileRepository = uploadedFileRepository;
    }

    // ---------------- SAFE PARSERS (UNCHANGED) ----------------
    private LocalDate safeDate(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.isEmpty()) return null;
        try {
            return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return null;
        }
    }

    private LocalTime safeTime(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.isEmpty()) return null;
        try {
            return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return null;
        }
    }

    private LocalDateTime safeDateTime(String s) {
        if (s == null || s.trim().isEmpty()) return LocalDateTime.now();
        try {
            Instant instant = Instant.parse(s);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (Exception ex) {
                return LocalDateTime.now();
            }
        }
    }

    /* ======================================================
       CREATE REQUEST (JSON) — ⛔ UNCHANGED
    ====================================================== */
    @Transactional
    public Request createRequest(RequestDto dto) {

        Request request = new Request();
        request.setRequestId("REQ" + System.currentTimeMillis());

        request.setResidentId(dto.getResidentId());
        request.setDocumentType(dto.getDocumentType());
        request.setSubmissionDate(safeDateTime(dto.getSubmissionDate()));
        request.setStatus(dto.getStatus() != null ? dto.getStatus() : "pending");
        request.setRemarks(dto.getRemarks());

        request.setFirstName(dto.getFirstName());
        request.setMiddleName(dto.getMiddleName());
        request.setLastName(dto.getLastName());
        request.setEmail(dto.getEmail());
        request.setMobile(dto.getMobile());
        request.setAddress(dto.getAddress());
        request.setBirthdate(safeDate(dto.getBirthdate()));

        request.setPurpose(dto.getPurpose());
        request.setEventPlace(dto.getEventPlace());
        request.setOrganizationName(dto.getOrganizationName());
        request.setEventDate(safeDate(dto.getEventDate()));
        request.setEventTime(safeTime(dto.getEventTime()));

        request.setIncidentDetails(dto.getIncidentDetails());
        request.setIncidentDate(safeDate(dto.getIncidentDate()));
        request.setIncidentLocation(dto.getIncidentLocation());
        request.setPartiesInvolved(dto.getPartiesInvolved());

        request.setBusinessName(dto.getBusinessName());
        request.setBusinessLocation(dto.getBusinessLocation());
        request.setDtiSecRegistration(dto.getDtiSecRegistration());
        request.setConstructionPurpose(dto.getConstructionPurpose());

        request.setRequestNotes(dto.getRequestNotes());

        return requestRepository.save(request);
    }

    /* ======================================================
       CREATE REQUEST WITH FILES — ⛔ UNCHANGED
    ====================================================== */
    @Transactional
    public Request createRequestWithFiles(RequestDto dto, List<MultipartFile> files) throws Exception {

        Request request = createRequest(dto);

        if (files != null) {
            for (MultipartFile f : files) {
                if (f == null || f.isEmpty()) continue;

                UploadedFile file = new UploadedFile();
                file.setLabel("Uploaded File");
                file.setFileName(f.getOriginalFilename());
                file.setFileType(f.getContentType());
                file.setFileSize(f.getSize());
                file.setStatus("Submitted");
                file.setFileData(f.getBytes());

                request.addUploadedFile(file);
            }
        }

        return requestRepository.save(request);
    }

    /* ======================================================
       ✅ OPTION A — THIS IS THE CRITICAL FIX
       WHAT CHANGED:
       ✔ Convert byte[] → Base64
       ✔ fileBase64 is now NOT NULL
    ====================================================== */
    public List<UploadedFileResponseDto> getRequestFiles(Long requestId) {

        List<UploadedFile> files =
                uploadedFileRepository.findByRequestId(requestId);

        return files.stream().map(file -> {
            UploadedFileResponseDto dto = new UploadedFileResponseDto();

            dto.setId(file.getId());
            dto.setLabel(file.getLabel());
            dto.setFileName(file.getFileName());
            dto.setFileType(file.getFileType());
            dto.setFileSize(file.getFileSize());
            dto.setStatus(file.getStatus());

            // 🔥 FIX: REAL Base64 conversion
            if (file.getFileData() != null) {
                dto.setFileBase64(
                        Base64.getEncoder().encodeToString(file.getFileData())
                );
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // ---------------- OTHER METHODS (UNCHANGED) ----------------

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<RequestDto> getAllRequestDtos() {
        return requestRepository.findAll()
                .stream()
                .map(this::mapRequestToDto)
                .toList();
    }

    public boolean updateRequestStatus(Long id, RequestStatusDto dto) {
        Request request = getRequestById(id);
        if (request == null) return false;

        request.setStatus(dto.getStatus());
        request.setRemarks(dto.getRemarks());
        requestRepository.save(request);
        return true;
    }

    public RequestDto mapRequestToDto(Request request) {
        RequestDto dto = new RequestDto();

        dto.setId(request.getId());
        dto.setRequestId(request.getRequestId());
        dto.setResidentId(request.getResidentId());
        dto.setStatus(request.getStatus());
        dto.setDocumentType(request.getDocumentType());
        dto.setRemarks(request.getRemarks());

        dto.setFirstName(request.getFirstName());
        dto.setMiddleName(request.getMiddleName());
        dto.setLastName(request.getLastName());
        dto.setEmail(request.getEmail());
        dto.setMobile(request.getMobile());
        dto.setAddress(request.getAddress());

        if (request.getBirthdate() != null)
            dto.setBirthdate(request.getBirthdate().toString());

        if (request.getSubmissionDate() != null)
            dto.setSubmissionDate(request.getSubmissionDate().toString());

        dto.setPurpose(request.getPurpose());
        dto.setEventPlace(request.getEventPlace());
        dto.setOrganizationName(request.getOrganizationName());

        if (request.getEventDate() != null)
            dto.setEventDate(request.getEventDate().toString());

        if (request.getEventTime() != null)
            dto.setEventTime(request.getEventTime().toString());

        dto.setIncidentDetails(request.getIncidentDetails());

        if (request.getIncidentDate() != null)
            dto.setIncidentDate(request.getIncidentDate().toString());

        dto.setIncidentLocation(request.getIncidentLocation());
        dto.setPartiesInvolved(request.getPartiesInvolved());

        dto.setBusinessName(request.getBusinessName());
        dto.setBusinessLocation(request.getBusinessLocation());
        dto.setDtiSecRegistration(request.getDtiSecRegistration());
        dto.setConstructionPurpose(request.getConstructionPurpose());

        dto.setRequestNotes(request.getRequestNotes());

        return dto;
    }
}
