package com.example.barangayconnect.service;

import com.example.barangayconnect.model.EmergencyContact;
import com.example.barangayconnect.repository.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactService {

    @Autowired
    private EmergencyContactRepository repository;

    public List<EmergencyContact> getAll() {
        return repository.findAll();
    }

    public EmergencyContact save(EmergencyContact c) {
        return repository.save(c);
    }

    public EmergencyContact update(Integer id, EmergencyContact updated) {
        EmergencyContact existing = repository.findById(id).orElseThrow();
        existing.setServiceName(updated.getServiceName());
        existing.setContactNumber(updated.getContactNumber());
        existing.setDescription(updated.getDescription());
        existing.setLastUpdated(updated.getLastUpdated());
        existing.setIcon(updated.getIcon());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
