package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.Admin;
import com.example.barangayconnect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Accept 'identifier' (can be email or phone number)
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestParam String identifier, @RequestParam String password) {
        Admin admin = adminService.login(identifier, password);

        if (admin != null) {
            return ResponseEntity.ok("Login successful! Welcome, " + admin.getFull_name());
        } else {
            return ResponseEntity.badRequest().body("Invalid phone/email or password. Please try again.");
        }
    }
}
