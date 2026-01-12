package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.TransparencyItem;
import com.example.barangayconnect.service.TransparencyItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin("*")
public class TransparencyItemController {

    private final TransparencyItemService service;

    public TransparencyItemController(TransparencyItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransparencyItem> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TransparencyItem create(@RequestBody TransparencyItem item) {
        return service.create(item);
    }

    @PutMapping("/{id}")
    public TransparencyItem update(@PathVariable Long id, @RequestBody TransparencyItem item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
