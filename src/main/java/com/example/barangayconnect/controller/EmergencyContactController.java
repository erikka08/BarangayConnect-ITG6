package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.EmergencyContact;
import com.example.barangayconnect.service.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency/contacts")
@CrossOrigin(origins = "*")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService contactService;

    @GetMapping
    public List<EmergencyContact> getAllContacts() {
        return contactService.getAll();
    }

    @PostMapping
    public EmergencyContact addContact(@RequestBody EmergencyContact contact) {
        return contactService.save(contact);
    }

    @PutMapping("/{id}")
    public EmergencyContact updateContact(
            @PathVariable Integer id,
            @RequestBody EmergencyContact contact
    ) {
        return contactService.update(id, contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Integer id) {
        contactService.delete(id);
    }
}
