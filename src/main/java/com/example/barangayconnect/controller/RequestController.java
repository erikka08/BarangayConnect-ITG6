package com.example.barangayconnect.controller;

import com.example.barangayconnect.dto.RequestDto;
import com.example.barangayconnect.dto.RequestStatusDto;
import com.example.barangayconnect.dto.UploadedFileResponseDto;
import com.example.barangayconnect.model.Request;
import com.example.barangayconnect.service.RequestService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /* ======================================================
       CREATE REQUEST (JSON) — ⛔ UNCHANGED
    ====================================================== */
    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto dto) {
        Request saved = requestService.createRequest(dto);
        return ResponseEntity.ok(
                requestService.mapRequestToDto(saved)
        );
    }

    /* ======================================================
       CREATE REQUEST (MULTIPART) — ⛔ UNCHANGED
    ====================================================== */
    @PostMapping(
            value = "/submit",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> submitRequestMultipart(
            @RequestPart("data") RequestDto dto,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        try {
            requestService.createRequestWithFiles(dto, files);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("ERROR");
        }
    }

    /* ======================================================
       ✅ OPTION A — GET FILES FOR A REQUEST (FIXED)
       🔧 FIX:
       - Call correct service method
       - NO remapping here
    ====================================================== */
    @GetMapping("/{id}/files")
    public ResponseEntity<List<UploadedFileResponseDto>> getRequestFiles(@PathVariable Long id) {
        return ResponseEntity.ok(
                requestService.getRequestFiles(id) // ✅ CORRECT METHOD
        );
    }

    /* ======================================================
       GET ALL REQUESTS — ⛔ UNCHANGED
    ====================================================== */
    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {
        return ResponseEntity.ok(
                requestService.getAllRequestDtos()
        );
    }

    /* ======================================================
       GET SINGLE REQUEST — ⛔ UNCHANGED
    ====================================================== */
    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable Long id) {

        Request request = requestService.getRequestById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                requestService.mapRequestToDto(request)
        );
    }

    /* ======================================================
       UPDATE STATUS — ⛔ UNCHANGED
    ====================================================== */
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody RequestStatusDto dto
    ) {
        boolean updated = requestService.updateRequestStatus(id, dto);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
