package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    Optional<Resident> findByPhoneNum(String phoneNum);
    Optional<Resident> findByEmail(String email);
    List<Resident> findByStatus(String status);
    long countByStatus(String status);

    // ✅ Logged-in users only
    List<Resident> findByIsLoggedInTrue();

    // ✅ Approved (ACTIVE) users
    List<Resident> findByStatusIgnoreCase(String status);

    // ✅ Offline users = Approved users not logged in
    List<Resident> findByStatusIgnoreCaseAndIsLoggedInFalse(String status);
}
