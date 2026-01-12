package com.example.barangayconnect.service;

import com.example.barangayconnect.model.Announcement;
import com.example.barangayconnect.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public Announcement update(Integer id, Announcement updated) {
        return announcementRepository.findById(id).map(a -> {
            a.setTitle(updated.getTitle());
            a.setType(updated.getType());
            a.setStatus(updated.getStatus());
            a.setDescription(updated.getDescription());
            a.setAuthor(updated.getAuthor());
            a.setAnnouncementDate(updated.getAnnouncementDate());
            a.setFeatured(updated.isFeatured());
            return announcementRepository.save(a);
        }).orElse(null);
    }

    public void delete(Integer id) {
        announcementRepository.deleteById(id);
    }

    /* ✅ ADDED: Fetch latest 3 announcements for dashboard */
    public List<Announcement> getLatestAnnouncements() {
        // ✅ UPDATED METHOD NAME here
        return announcementRepository.findTop3ByOrderByAnnouncementDateDesc();
    }
}
