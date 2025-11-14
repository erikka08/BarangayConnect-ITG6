package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Admin;
import com.example.barangayconnect.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String identifier, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(identifier, password);
        if (admin.isEmpty()) {
            admin = adminRepository.findByPhoneNumberAndPassword(identifier, password);
        }
        return admin.orElse(null);
    }
}
