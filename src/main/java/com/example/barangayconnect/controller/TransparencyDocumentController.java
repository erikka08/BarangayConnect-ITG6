package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.TransparencyDocument;
import com.example.barangayconnect.service.TransparencyDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/items")
@CrossOrigin("*")
public class TransparencyDocumentController {

    private final TransparencyDocumentService service;

    public TransparencyDocumentController(TransparencyDocumentService service) {
        this.service = service;
    }

    @PostMapping("/{id}/documents")
    public ResponseEntity<?> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            TransparencyDocument doc = service.upload(id, file);
            return ResponseEntity.ok(doc);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
