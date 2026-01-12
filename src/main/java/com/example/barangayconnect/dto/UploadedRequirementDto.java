package com.example.barangayconnect.dto;

/*
======================================================
✅ CHANGES MADE IN THIS FILE (VERY IMPORTANT)
------------------------------------------------------
WHAT WAS REMOVED:
- ❌ fileBase64 field
- ❌ getFileBase64()
- ❌ setFileBase64()

WHY:
- Reports architecture does NOT upload images via DTO
- Images must be uploaded via MultipartFile (Controller)
- Images must be stored as byte[] BLOB (Entity)
- Base64 is ONLY for display and must come from Entity

WHAT THIS DTO IS NOW USED FOR:
- File METADATA only (name, type, size, status)
- Safe to send in JSON
- Does NOT carry binary data
======================================================
*/

public class UploadedRequirementDto {

    private Long id; // 🔥 REQUIRED (for mapping existing files)
    private String label;
    private String fileName;
    private String fileType;
    private long fileSize;
    private String status;

    // ⛔ REMOVED: Base64 image data
    // private String fileBase64;

    public UploadedRequirementDto() {}

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

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ⛔ REMOVED Base64 getters/setters
}
