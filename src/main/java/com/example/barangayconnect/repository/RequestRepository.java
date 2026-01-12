package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Request findByRequestId(String requestId);

    Long countByResidentId(String residentId);

    /* ✅ ADDED:
       Needed for Admin Dashboard to count Pending Requests */
    long countByStatusIgnoreCase(String status);
}
