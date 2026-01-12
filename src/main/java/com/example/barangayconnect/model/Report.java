package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.util.Date;

/* ============================
   ✅ ADDED (Base64 support)
   - Needed to convert byte[] image to Base64 string for frontend display
============================ */
import jakarta.persistence.Transient;              // ✅ ADDED
import java.util.Base64;                           // ✅ ADDED
import com.fasterxml.jackson.annotation.JsonIgnore; // ✅ ADDED (hide raw byte[] in JSON)

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @JsonIgnore // ✅ ADDED: prevent returning raw byte[] in JSON response (frontend can't display this directly)
    private byte[] image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted = new Date();

    @Column(nullable = false)
    private String status = "Pending";

    /* ============================
       ✅ ADDED (Base64 Getter)
       - Adds "imageBase64" field in JSON automatically
       - Frontend will use: data:image/jpeg;base64,${imageBase64}
       - @Transient ensures it is NOT saved as a DB column
    ============================ */
    @Transient // ✅ ADDED
    public String getImageBase64() { // ✅ ADDED
        if (this.image == null || this.image.length == 0) return null;
        return Base64.getEncoder().encodeToString(this.image);
    }

    // -------------------
    // Getters and Setters
    // -------------------

    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public Date getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(Date dateSubmitted) { this.dateSubmitted = dateSubmitted; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

