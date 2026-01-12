package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Admin;
import com.example.barangayconnect.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin login(String identifier, String password) {

        // ✅ Try login by email
        Optional<Admin> byEmail = adminRepository.findByEmail(identifier);
        if (byEmail.isPresent()) {
            Admin admin = byEmail.get();

            // ✅ hashed password check
            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin;
            }

            // ✅ OPTIONAL: support old plain-text admin passwords
            if (password.equals(admin.getPassword())) {
                admin.setPassword(passwordEncoder.encode(password));
                adminRepository.save(admin);
                return admin;
            }
        }

        // ✅ Try login by phone number
        Optional<Admin> byPhone = adminRepository.findByPhoneNumber(identifier);
        if (byPhone.isPresent()) {
            Admin admin = byPhone.get();

            if (passwordEncoder.matches(password, admin.getPassword())) {
                return admin;
            }

            // ✅ OPTIONAL: support old plain-text admin passwords
            if (password.equals(admin.getPassword())) {
                admin.setPassword(passwordEncoder.encode(password));
                adminRepository.save(admin);
                return admin;
            }
        }

        return null;
    }
}
