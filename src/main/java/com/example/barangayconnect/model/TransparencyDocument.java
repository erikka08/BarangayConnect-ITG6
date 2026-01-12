package com.example.barangayconnect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "transparency_document")
public class TransparencyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private TransparencyItem item;

    public TransparencyDocument() {}

    public TransparencyDocument(String fileName, String filePath, TransparencyItem item) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.item = item;
    }

    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public TransparencyItem getItem() { return item; }
}
