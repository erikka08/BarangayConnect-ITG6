package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    // ORIGINAL methods (UNCHANGED)
    Optional<Resident> findByPhoneNum(String phoneNum);
    Optional<Resident> findByEmail(String email);

    Optional<Resident> findByPhoneNumAndPassword(String phoneNum, String password);
    Optional<Resident> findByEmailAndPassword(String email, String password);

    List<Resident> findByStatusIgnoreCase(String status);

    List<Resident> findByIsLoggedInTrue();

    List<Resident> findByStatusIgnoreCaseAndIsLoggedInFalse(String status);

    // ORIGINAL count methods (UNCHANGED)
    Long countByStatus(String status);
    Long countByIsLoggedInFalse();

    // ✅ ADDED: Needed to count true-active users (logged in)
    // This is the ONLY change in this file.
    Long countByIsLoggedInTrue();
}
