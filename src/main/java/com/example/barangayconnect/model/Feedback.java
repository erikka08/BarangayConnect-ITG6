package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Column(length = 1000)
    private String message;

    private LocalDateTime date;

    private boolean readStatus = false;

    public Feedback() {}

    public Feedback(String status, String message) {
        this.status = status;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public boolean isReadStatus() { return readStatus; }
    public void setReadStatus(boolean readStatus) { this.readStatus = readStatus; }
}
