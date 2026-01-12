package com.example.barangayconnect.service;

import com.example.barangayconnect.model.TransparencyDocument;
import com.example.barangayconnect.model.TransparencyItem;
import com.example.barangayconnect.repository.TransparencyDocumentRepository;
import com.example.barangayconnect.repository.TransparencyItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.UUID;

@Service
public class TransparencyDocumentService {

    private final TransparencyDocumentRepository docRepo;
    private final TransparencyItemRepository itemRepo;

    public TransparencyDocumentService(
            TransparencyDocumentRepository docRepo,
            TransparencyItemRepository itemRepo) {
        this.docRepo = docRepo;
        this.itemRepo = itemRepo;
    }

    public TransparencyDocument upload(Long itemId, MultipartFile file) throws Exception {

        TransparencyItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Ensure folder structure: uploads/item-{id}
        String folder = "uploads/item-" + itemId;
        Files.createDirectories(Paths.get(folder));

        // Generate unique filename
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        Path path = Paths.get(folder, filename);

        // Save file to server
        Files.write(path, file.getBytes());

        // FIXED: Store the correct browser-accessible path
        String dbPath = "/uploads/item-" + itemId + "/" + filename;

        // Save database entry
        TransparencyDocument doc =
                new TransparencyDocument(file.getOriginalFilename(), dbPath, item);

        return docRepo.save(doc);
    }
}
