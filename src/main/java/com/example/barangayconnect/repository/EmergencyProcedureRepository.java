package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.EmergencyProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyProcedureRepository extends JpaRepository<EmergencyProcedure, Integer> {
}
