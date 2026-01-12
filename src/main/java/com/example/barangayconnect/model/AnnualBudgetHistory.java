package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "annual_budget_history")
public class AnnualBudgetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double previous;
    private Double newAmount;

    private LocalDateTime changedAt;

    public AnnualBudgetHistory() {}

    public Long getId() {
        return id;
    }

    public Double getPrevious() {
        return previous;
    }

    public void setPrevious(Double previous) {
        this.previous = previous;
    }

    public Double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Double newAmount) {
        this.newAmount = newAmount;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    @PrePersist
    public void onCreate() {
        changedAt = LocalDateTime.now();
    }
}
