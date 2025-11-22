package com.example.barangayconnect.service;

import com.example.barangayconnect.model.EmergencyProcedure;
import com.example.barangayconnect.repository.EmergencyProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyProcedureService {

    @Autowired
    private EmergencyProcedureRepository repository;

    public List<EmergencyProcedure> getAll() {
        return repository.findAll();
    }

    public EmergencyProcedure save(EmergencyProcedure p) {
        return repository.save(p);
    }

    public EmergencyProcedure update(Integer id, EmergencyProcedure updated) {
        EmergencyProcedure existing = repository.findById(id).orElseThrow();
        existing.setTitle(updated.getTitle());
        existing.setSteps(updated.getSteps());
        existing.setIcon(updated.getIcon());
        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
