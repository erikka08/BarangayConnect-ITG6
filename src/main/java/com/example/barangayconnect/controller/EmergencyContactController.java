package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.EmergencyContact;
import com.example.barangayconnect.service.EmergencyContactService;
import com.example.barangayconnect.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency/contacts")
@CrossOrigin(origins = "*")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService contactService;

    @Autowired
    private ActivityLogService activityLogService; // ✅ ADDED

    @GetMapping
    public List<EmergencyContact> getAllContacts() {
        return contactService.getAll();
    }

    @PostMapping
    public EmergencyContact addContact(@RequestBody EmergencyContact contact) {
        EmergencyContact saved = contactService.save(contact);

        // ✅ OPTIONAL: log add contact
        activityLogService.record(
                "Emergency contact added",
                saved.getServiceName() + " contact was added",
                "UPDATED",
                "fa-phone"
        );

        return saved;
    }

    @PutMapping("/{id}")
    public EmergencyContact updateContact(
            @PathVariable Integer id,
            @RequestBody EmergencyContact contact
    ) {
        EmergencyContact updated = contactService.update(id, contact);

        // ✅ OPTIONAL: log update contact
        activityLogService.record(
                "Emergency contact updated",
                updated.getServiceName() + " contact was updated",
                "UPDATED",
                "fa-phone"
        );

        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Integer id) {

        // ✅ OPTIONAL: get the contact info first before deleting
        EmergencyContact contact = contactService.getById(id);

        contactService.delete(id);

        // ✅ MAIN FIX: RECORD DELETE ACTION
        activityLogService.record(
                "Emergency contact deleted",
                contact.getServiceName() + " contact was removed",
                "UPDATED",
                "fa-phone"
        );
    }
}
