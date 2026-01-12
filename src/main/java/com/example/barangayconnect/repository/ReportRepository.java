package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    // ⭐ REQUIRED for cancelled reports & status filtering
    List<Report> findByStatus(String status);
    
}