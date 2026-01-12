package com.example.barangayconnect.service;

import com.example.barangayconnect.model.TransparencyItem;
import com.example.barangayconnect.repository.TransparencyItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransparencyItemService {

    private final TransparencyItemRepository repo;

    public TransparencyItemService(TransparencyItemRepository repo) {
        this.repo = repo;
    }

    public List<TransparencyItem> getAll() { return repo.findAll(); }

    public Optional<TransparencyItem> getById(Long id) { return repo.findById(id); }

    public TransparencyItem create(TransparencyItem item) { return repo.save(item); }

    public TransparencyItem update(Long id, TransparencyItem data) {
        data.setId(id);
        return repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
