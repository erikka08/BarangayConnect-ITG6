package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.EmergencyProcedure;
import com.example.barangayconnect.service.EmergencyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency/procedures")
@CrossOrigin(origins = "*")
public class EmergencyProcedureController {

    @Autowired
    private EmergencyProcedureService procedureService;

    @GetMapping
    public List<EmergencyProcedure> getAllProcedures() {
        return procedureService.getAll();
    }

    @PostMapping
    public EmergencyProcedure addProcedure(@RequestBody EmergencyProcedure procedure) {
        return procedureService.save(procedure);
    }

    @PutMapping("/{id}")
    public EmergencyProcedure updateProcedure(
            @PathVariable Integer id,
            @RequestBody EmergencyProcedure procedure
    ) {
        return procedureService.update(id, procedure);
    }

    @DeleteMapping("/{id}")
    public void deleteProcedure(@PathVariable Integer id) {
        procedureService.delete(id);
    }
}
