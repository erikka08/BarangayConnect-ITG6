package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------------------------------------------------
    // ✅ LOGIN (UPDATED FOR HASHED PASSWORDS)
    // ---------------------------------------------------
    public Resident login(String identifier, String password) {

        // ✅ Try login by phone number first
        Optional<Resident> byPhone = residentRepository.findByPhoneNum(identifier);
        if (byPhone.isPresent()) {
            Resident resident = byPhone.get();

            // ✅ Compare raw password to hashed password
            if (passwordEncoder.matches(password, resident.getPassword())) {
                return resident;
            }

            // ✅ OPTIONAL: Support old plain-text passwords temporarily
            if (password.equals(resident.getPassword())) {
                // Convert old plain password to hashed automatically
                resident.setPassword(passwordEncoder.encode(password));
                residentRepository.save(resident);
                return resident;
            }
        }

        // ✅ Try login by email next
        Optional<Resident> byEmail = residentRepository.findByEmail(identifier);
        if (byEmail.isPresent()) {
            Resident resident = byEmail.get();

            if (passwordEncoder.matches(password, resident.getPassword())) {
                return resident;
            }

            // ✅ OPTIONAL: Support old plain-text passwords temporarily
            if (password.equals(resident.getPassword())) {
                resident.setPassword(passwordEncoder.encode(password));
                residentRepository.save(resident);
                return resident;
            }
        }

        return null;
    }

    // ---------------------------------------------------
    // ✅ REGISTER (UPDATED FOR HASHING)
    // ---------------------------------------------------
    public Resident register(Resident resident) {

        // ✅ hash password before saving
        resident.setPassword(passwordEncoder.encode(resident.getPassword()));

        // optional: force pending by default
        if (resident.getStatus() == null) {
            resident.setStatus("PENDING");
        }

        return residentRepository.save(resident);
    }

    // ---------------------------------------------------
    // FIND BY ID
    // ---------------------------------------------------
    public Optional<Resident> findById(Integer id) {
        return residentRepository.findById(id);
    }

    // ---------------------------------------------------
    // PENDING USERS
    // ---------------------------------------------------
    public List<Resident> getPendingUsers() {
        return residentRepository.findByStatusIgnoreCase("PENDING");
    }

    // ---------------------------------------------------
    // APPROVED USERS
    // ---------------------------------------------------
    public List<Resident> getApprovedUsers() {
        return residentRepository.findByStatusIgnoreCase("ACTIVE");
    }

    // ---------------------------------------------------
    // APPROVE USER
    // ---------------------------------------------------
    public Resident approveUser(Integer id) {
        Resident r = residentRepository.findById(id).orElseThrow();
        r.setStatus("ACTIVE");
        r.setApprovedDateTime(LocalDateTime.now());
        return residentRepository.save(r);
    }

    // ---------------------------------------------------
    // REJECT USER
    // ---------------------------------------------------
    public void rejectUser(Integer id) {
        residentRepository.deleteById(id);
    }

    // ---------------------------------------------------
    // COUNT API
    // ---------------------------------------------------
    public Map<String, Long> getCounts() {
        Map<String, Long> map = new HashMap<>();

        Long total = residentRepository.count();
        Long pending = residentRepository.countByStatus("PENDING");
        Long approved = residentRepository.countByStatus("ACTIVE");

        Long loggedIn = residentRepository.countByIsLoggedInTrue();

        map.put("activeUsers", loggedIn);
        map.put("offlineUsers", approved - loggedIn);
        map.put("pendingUsers", pending);
        map.put("totalUsers", total);

        return map;
    }
}
