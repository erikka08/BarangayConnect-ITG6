package com.example.barangayconnect.dto;

/*
======================================================
✅ PURPOSE OF THIS DTO (OPTION A)
------------------------------------------------------
WHY THIS EXISTS:
- Admin needs to VIEW uploaded images/documents
- We must NOT expose UploadedFile entity directly
- We must NOT expose raw byte[] fileData
- We must ONLY expose Base64 (READ-ONLY)

WHAT THIS DTO CONTAINS:
✔ File metadata (id, name, type, size, status)
✔ Base64 string (for <img> or <iframe> display)

WHAT THIS DTO DOES NOT DO:
❌ Does NOT upload files
❌ Does NOT store Base64 in DB
❌ Does NOT break any existing feature
======================================================
*/

public class UploadedFileResponseDto {

    private Long id;
    private String label;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String status;

    // ✅ Base64 for ADMIN DISPLAY ONLY
    private String fileBase64;

    public UploadedFileResponseDto() {}

    /* ===== GETTERS & SETTERS ===== */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }
}
