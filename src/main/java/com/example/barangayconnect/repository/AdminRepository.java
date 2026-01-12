package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // ✅ find admin by email (password check happens in service using BCrypt)
    Optional<Admin> findByEmail(String email);

    // ✅ find admin by phone number (password check happens in service using BCrypt)
    Optional<Admin> findByPhoneNumber(String phoneNumber);
}
