package com.example.barangayconnect.controller;

import com.example.barangayconnect.dto.DashboardDto;
import com.example.barangayconnect.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{residentId}")
    public DashboardDto getDashboard(@PathVariable String residentId) {
        return dashboardService.getDashboardData(residentId);
    }
}
