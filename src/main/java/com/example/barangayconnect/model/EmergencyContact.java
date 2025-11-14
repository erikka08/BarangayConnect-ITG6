package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergencycontacts")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "Fire Department", "Police Station", etc.
    @Column(nullable = false)
    private String service;

    // e.g. "911" or "(02) 8XXX-XXXX"
    @Column(nullable = false)
    private String phone;

    // ACTIVE / INACTIVE
    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

    /* getters/setters */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
