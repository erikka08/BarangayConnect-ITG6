package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.ActivityLog;
import com.example.barangayconnect.service.ActivityLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminRecentActivityController {

    private final ActivityLogService service;

    public AdminRecentActivityController(ActivityLogService service) {
        this.service = service;
    }

    @GetMapping("/recent-activity")
    public List<ActivityLog> getRecentActivity() {
        return service.getRecentActivity();
    }
}
