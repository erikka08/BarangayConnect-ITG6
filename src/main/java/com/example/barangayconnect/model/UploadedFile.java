package com.example.barangayconnect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

// ✅ ADDED (for Base64 conversion on READ, like Reports)
import jakarta.persistence.Transient;     // ✅ ADDED
import java.util.Base64;                  // ✅ ADDED

@Entity
@Table(name = "uploaded_files")
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "Valid ID"
    private String label;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;

    private String status;

    // 🔥 IMAGE / PDF STORED AS BLOB (UNCHANGED)
    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    @JsonIgnore // ⛔ VERY IMPORTANT: never expose raw bytes
    private byte[] fileData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Request request;

    /*
    ======================================================
    ✅ ADDED: Base64 getter (READ-ONLY)
    ------------------------------------------------------
    WHY:
    - Matches Reports architecture
    - Converts BLOB → Base64 ONLY when returning JSON
    - @Transient → NOT stored in DB
    - Used ONLY for admin display
    ======================================================
    */
    @Transient
    public String getFileBase64() {
        if (fileData == null || fileData.length == 0) return null;
        return Base64.getEncoder().encodeToString(fileData);
    }

    // ===== GETTERS & SETTERS (UNCHANGED) =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public byte[] getFileData() { return fileData; }
    public void setFileData(byte[] fileData) { this.fileData = fileData; }

    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }
}

/*
===========================
SUMMARY (Option A)
===========================

✅ ADDED:
- @Transient getFileBase64()
- Base64 import

❌ DELETED: NOTHING
⛔ CHANGED: NOTHING ELSE

✅ Safe
✅ Matches Reports architecture
✅ Does NOT break uploads
✅ Does NOT expose raw BLOBs
*/
