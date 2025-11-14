package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.EmergencyContact;
import com.example.barangayconnect.service.EmergencyContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency")
@CrossOrigin(origins = "*")
public class EmergencyContactController {

    private final EmergencyContactService service;

    public EmergencyContactController(EmergencyContactService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmergencyContact> list() {
        return service.findAll();
    }

    @PostMapping
    public EmergencyContact create(@RequestBody EmergencyContact body) {
        return service.create(body);
    }

    @PutMapping("/{id}")
    public EmergencyContact update(@PathVariable Long id, @RequestBody EmergencyContact body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
