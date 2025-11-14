package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    public void register(Resident resident) {
        if (resident.getStatus() == null)
            resident.setStatus("PENDING");
        residentRepository.save(resident);
    }

    public List<Resident> getPendingResidents() {
        return residentRepository.findByStatusIgnoreCase("PENDING");
    }

    public Resident getResidentById(Integer id) {
        return residentRepository.findById(id).orElse(null);
    }

    public String approveResident(Integer id) {
        Resident resident = getResidentById(id);
        if (resident == null) return "User not found!";

        resident.setStatus("ACTIVE");
        resident.setApprovedDateTime(LocalDateTime.now());
        residentRepository.save(resident);

        return "User approved!";
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public List<Resident> getActiveResidents() {
        return residentRepository.findByIsLoggedInTrue();
    }

    // ✅ Get all approved users
    public List<Resident> getApprovedResidents() {
        return residentRepository.findByStatusIgnoreCase("ACTIVE");
    }

    // ✅ Dashboard counters logic
    public Map<String, Long> getUserCounts() {
        long totalUsers = residentRepository.findByStatusIgnoreCase("ACTIVE").size();
        long pendingUsers = residentRepository.findByStatusIgnoreCase("PENDING").size();
        long activeUsers = residentRepository.findByIsLoggedInTrue().size();
        long offlineUsers = totalUsers - activeUsers;
        if (offlineUsers < 0) offlineUsers = 0;

        Map<String, Long> counts = new HashMap<>();
        counts.put("totalUsers", totalUsers);
        counts.put("activeUsers", activeUsers);
        counts.put("offlineUsers", offlineUsers);
        counts.put("pendingUsers", pendingUsers);
        return counts;
    }
}
