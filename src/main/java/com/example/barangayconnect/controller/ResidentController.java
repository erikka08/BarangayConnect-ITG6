package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resident")
@CrossOrigin(origins = "*")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    @PostMapping("/register")
    public String registerResident(@RequestParam String firstname,
                                   @RequestParam String lastname,
                                   @RequestParam String phone_num,
                                   @RequestParam(required = false) String email,
                                   @RequestParam String password) {

        Resident resident = new Resident();
        resident.setFirstname(firstname);
        resident.setLastname(lastname);
        resident.setPhoneNum(phone_num);
        resident.setEmail(email);
        resident.setPassword(password);
        resident.setStatus("PENDING");

        residentService.register(resident);
        return "Registration submitted! Wait for approval.";
    }

    @GetMapping("/pending")
    public List<Resident> getPendingResidents() {
        return residentService.getPendingResidents();
    }

    @PostMapping("/approve/{id}")
    public String approveResident(@PathVariable Integer id) {
        return residentService.approveResident(id);
    }

    @GetMapping("/active")
    public List<Resident> getActiveResidents() {
        return residentService.getActiveResidents();
    }

    @GetMapping("/all")
    public List<Resident> getAllResidents() {
        return residentService.getAllResidents();
    }

    // ✅ Approved users for User Details page
    @GetMapping("/approved")
    public List<Resident> getApprovedResidents() {
        return residentService.getApprovedResidents();
    }

    // ✅ Counts for dashboard
    @GetMapping("/counts")
    public Map<String, Long> getUserCounts() {
        return residentService.getUserCounts();
    }
}
