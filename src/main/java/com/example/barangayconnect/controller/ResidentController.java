package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resident")  
@CrossOrigin(origins = "*")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    // ---------------------------------------------------------
    // LOGIN  (ORIGINAL – UNTOUCHED)
    // ---------------------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String identifier,
                                   @RequestParam String password) {

        Resident resident = residentService.login(identifier, password);

        if (resident == null) {
            return ResponseEntity.status(401).body("INVALID_LOGIN");
        }

        return ResponseEntity.ok(resident);
    }

    // ---------------------------------------------------------
    // REGISTER (ORIGINAL – UNTOUCHED)
    // ---------------------------------------------------------
   @PostMapping("/register")
public ResponseEntity<?> register(Resident resident) {

        resident.setStatus("PENDING");
        resident.setIsLoggedIn(false);
        Resident saved = residentService.register(resident);
        return ResponseEntity.ok(saved);
    }

    // ---------------------------------------------------------
    // NEW ENDPOINTS (ADDED ONLY – NOTHING DELETED)
    // ---------------------------------------------------------

    // Get resident by ID → needed for waiting room
    @GetMapping("/{id}")
    public ResponseEntity<?> getResident(@PathVariable Integer id) {
        return ResponseEntity.of(residentService.findById(id));
    }

    // Get all PENDING users
    @GetMapping("/pending")
    public List<Resident> getPendingUsers() {
        return residentService.getPendingUsers();
    }

    // Get all APPROVED / ACTIVE users
    @GetMapping("/approved")
    public List<Resident> getApprovedUsers() {
        return residentService.getApprovedUsers();
    }

    // Approve resident
    @PostMapping("/approve/{id}")
    public Resident approveUser(@PathVariable Integer id) {
        return residentService.approveUser(id);
    }

    // Reject resident (delete)
    @PostMapping("/reject/{id}")
    public void rejectUser(@PathVariable Integer id) {
        residentService.rejectUser(id);
    }

    // Dashboard counts
    @GetMapping("/counts")
    public Map<String, Long> getCounts() {
        return residentService.getCounts();
    }
}

