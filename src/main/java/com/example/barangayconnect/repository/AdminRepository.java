package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Find by email or phone number and match password
    Optional<Admin> findByEmailAndPassword(String email, String password);
    Optional<Admin> findByPhoneNumberAndPassword(String phoneNumber, String password);
}
 