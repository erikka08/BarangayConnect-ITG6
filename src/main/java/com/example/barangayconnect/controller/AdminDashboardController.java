package com.example.barangayconnect.controller;

import com.example.barangayconnect.dto.AdminDashboardDTO;
import com.example.barangayconnect.repository.ReportRepository;
import com.example.barangayconnect.repository.RequestRepository;
import com.example.barangayconnect.repository.ResidentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "*")
public class AdminDashboardController {

    private final ResidentRepository residentRepository;
    private final ReportRepository reportRepository;
    private final RequestRepository requestRepository;

    public AdminDashboardController(
            ResidentRepository residentRepository,
            ReportRepository reportRepository,
            RequestRepository requestRepository
    ) {
        this.residentRepository = residentRepository;
        this.reportRepository = reportRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping
    public AdminDashboardDTO getAdminDashboardCounts() {

        // ✅ Total Residents (all)
        long totalResidents = residentRepository.count();

        // ✅ Reports Generated (all reports)
        long reportsGenerated = reportRepository.count();

        // ✅ Pending Requests
        long pendingRequests = requestRepository.countByStatusIgnoreCase("Pending");

        return new AdminDashboardDTO(totalResidents, reportsGenerated, pendingRequests);
    }
}
