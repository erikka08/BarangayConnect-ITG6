package com.example.barangayconnect.service;

import com.example.barangayconnect.model.AnnualBudget;
import com.example.barangayconnect.model.AnnualBudgetHistory;
import com.example.barangayconnect.repository.AnnualBudgetHistoryRepository;
import com.example.barangayconnect.repository.AnnualBudgetRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BudgetService {

    private final AnnualBudgetRepository budgetRepo;
    private final AnnualBudgetHistoryRepository historyRepo;

    public BudgetService(AnnualBudgetRepository budgetRepo,
                         AnnualBudgetHistoryRepository historyRepo) {
        this.budgetRepo = budgetRepo;
        this.historyRepo = historyRepo;
    }

    public AnnualBudget getCurrentBudget() {
        return budgetRepo.findAll()
                .stream()
                .max(Comparator.comparing(AnnualBudget::getId))
                .orElse(null);
    }

    public AnnualBudget updateBudget(Double newAmount) {
        AnnualBudget previousBudget = getCurrentBudget();

        AnnualBudget newBudget = new AnnualBudget();
        newBudget.setAmount(newAmount);
        budgetRepo.save(newBudget);

        if (previousBudget != null) {
            AnnualBudgetHistory h = new AnnualBudgetHistory();
            h.setPrevious(previousBudget.getAmount());
            h.setNewAmount(newAmount);
            historyRepo.save(h);
        }

        return newBudget;
    }

    public List<AnnualBudgetHistory> getHistory() {
        return historyRepo.findAll();
    }
}
