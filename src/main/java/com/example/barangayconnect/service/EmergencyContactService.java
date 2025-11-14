package com.example.barangayconnect.service;

import com.example.barangayconnect.model.EmergencyContact;
import com.example.barangayconnect.repository.EmergencyContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmergencyContactService {
    private final EmergencyContactRepository repo;

    public EmergencyContactService(EmergencyContactRepository repo) {
        this.repo = repo;
    }

    public List<EmergencyContact> findAll() {
        return repo.findAll();
    }

    public EmergencyContact create(EmergencyContact ec) {
        ec.setLastUpdated(LocalDateTime.now());
        if (ec.getStatus() == null || ec.getStatus().isBlank()) ec.setStatus("ACTIVE");
        return repo.save(ec);
    }

    public EmergencyContact update(Long id, EmergencyContact payload) {
        return repo.findById(id).map(ec -> {
            if (payload.getService() != null) ec.setService(payload.getService());
            if (payload.getPhone() != null)   ec.setPhone(payload.getPhone());
            if (payload.getStatus() != null)  ec.setStatus(payload.getStatus());
            ec.setLastUpdated(LocalDateTime.now());
            return repo.save(ec);
        }).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
