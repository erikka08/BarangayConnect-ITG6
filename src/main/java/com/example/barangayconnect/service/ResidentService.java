package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    // ---------------------------------------------------
    // LOGIN (UNCHANGED)
    // ---------------------------------------------------
    public Resident login(String identifier, String password) {
        Optional<Resident> byPhone =
                residentRepository.findByPhoneNumAndPassword(identifier, password);
        if (byPhone.isPresent()) return byPhone.get();

        Optional<Resident> byEmail =
                residentRepository.findByEmailAndPassword(identifier, password);
        if (byEmail.isPresent()) return byEmail.get();

        return null;
    }

    // ---------------------------------------------------
    // REGISTER (UNCHANGED)
    // ---------------------------------------------------
    public Resident register(Resident resident) {
        return residentRepository.save(resident);
    }

    // ---------------------------------------------------
    // FIND BY ID (UNCHANGED)
    // ---------------------------------------------------
    public Optional<Resident> findById(Integer id) {
        return residentRepository.findById(id);
    }

    // ---------------------------------------------------
    // PENDING USERS (UNCHANGED)
    // ---------------------------------------------------
    public List<Resident> getPendingUsers() {
        return residentRepository.findByStatusIgnoreCase("PENDING");
    }

    // ---------------------------------------------------
    // APPROVED USERS (UNCHANGED)
    // ---------------------------------------------------
    public List<Resident> getApprovedUsers() {
        return residentRepository.findByStatusIgnoreCase("ACTIVE");
    }

    // ---------------------------------------------------
    // APPROVE USER (UNCHANGED EXCEPT APPROVED TIME)
    // ---------------------------------------------------
    public Resident approveUser(Integer id) {
        Resident r = residentRepository.findById(id).orElseThrow();
        r.setStatus("ACTIVE");
        r.setApprovedDateTime(LocalDateTime.now());
        return residentRepository.save(r);
    }

    // ---------------------------------------------------
    // REJECT USER (UNCHANGED)
    // ---------------------------------------------------
    public void rejectUser(Integer id) {
        residentRepository.deleteById(id);
    }

    // ---------------------------------------------------
    // COUNT API (ONLY PART UPDATED)
    // ---------------------------------------------------
    public Map<String, Long> getCounts() {
        Map<String, Long> map = new HashMap<>();

        // ORIGINAL data retrieval (unchanged)
        Long total = residentRepository.count();
        Long pending = residentRepository.countByStatus("PENDING");
        Long approved = residentRepository.countByStatus("ACTIVE");

        // ---------------------------------------------------
        // ✅ FIXED:
        // Active users = people who are logged in (isLoggedIn = true)
        // ---------------------------------------------------
        Long loggedIn = residentRepository.countByIsLoggedInTrue();

        // ---------------------------------------------------
        // ✔ Active users (for dashboard) = logged-in users only
        // ---------------------------------------------------
        map.put("activeUsers", loggedIn);

        // ---------------------------------------------------
        // ✔ Offline = approved users minus logged-in users
        // This matches your frontend display ("Active" / "Offline")
        // ---------------------------------------------------
        map.put("offlineUsers", approved - loggedIn);

        // ---------------------------------------------------
        // Pending logic stays untouched
        // ---------------------------------------------------
        map.put("pendingUsers", pending);

        // ---------------------------------------------------
        // Total stays unchanged
        // ---------------------------------------------------
        map.put("totalUsers", total);

        return map;
    }
}
