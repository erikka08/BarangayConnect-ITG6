package com.example.barangayconnect.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "emergency_procedures")
public class EmergencyProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String steps;   // <-- store JSON here

    private String icon;

    public EmergencyProcedure() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
