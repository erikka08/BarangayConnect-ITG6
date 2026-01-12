package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.AnnualBudget;
import com.example.barangayconnect.model.AnnualBudgetHistory;
import com.example.barangayconnect.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin("*")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @GetMapping("/current")
    public ResponseEntity<AnnualBudget> getCurrent() {
        AnnualBudget b = service.getCurrentBudget();
        if (b == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(b);
    }

    @PutMapping("/current")
    public AnnualBudget update(@RequestBody AnnualBudget req) {
        return service.updateBudget(req.getAmount());
    }

    @GetMapping("/history")
    public List<AnnualBudgetHistory> history() {
        return service.getHistory();
    }
}
