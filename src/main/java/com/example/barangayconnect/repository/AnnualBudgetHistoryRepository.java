package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.AnnualBudgetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualBudgetHistoryRepository extends JpaRepository<AnnualBudgetHistory, Long> {
}
