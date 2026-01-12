package com.example.barangayconnect.dto;

public class ReportDto {

    private String title;
    private String category;
    private String location;
    private String description;

    // 🔥 REQUIRED FOR IMAGE DISPLAY
    private String imageBase64;
    private String imageType; // image/png, image/jpeg, etc.

    /* ================= GETTERS & SETTERS ================= */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* ================= IMAGE ================= */

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}