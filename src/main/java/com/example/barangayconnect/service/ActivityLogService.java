package com.example.barangayconnect.service;

import com.example.barangayconnect.model.ActivityLog;
import com.example.barangayconnect.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogService {

    private final ActivityLogRepository repo;

    public ActivityLogService(ActivityLogRepository repo) {
        this.repo = repo;
    }

    public List<ActivityLog> getRecentActivity() {
        return repo.findTop6ByOrderByCreatedAtDesc();
    }

    // ✅ FIXED: No need to manually set createdAt
    public void record(String title, String subtitle, String status, String icon) {
        ActivityLog log = new ActivityLog();
        log.setTitle(title);
        log.setSubtitle(subtitle);
        log.setStatus(status);
        log.setIcon(icon);
        repo.save(log); // ✅ createdAt is auto set by @PrePersist
    }
}
