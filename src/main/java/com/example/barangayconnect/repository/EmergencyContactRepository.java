package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Integer> {
}
