package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.Announcement;
import com.example.barangayconnect.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin(origins = "*")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.findAll();
    }

    @PostMapping
    public Announcement createAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.save(announcement);
    }

    @PutMapping("/{id}")
    public Announcement updateAnnouncement(@PathVariable Integer id, @RequestBody Announcement announcement) {
        return announcementService.update(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable Integer id) {
        announcementService.delete(id);
    }

    /* ✅ CHANGED: Instead of calling announcementRepository (which doesn't exist here),
       we call announcementService.getLatestAnnouncements() */
    @GetMapping("/latest")
    public List<Announcement> getLatestAnnouncements() {
        return announcementService.getLatestAnnouncements(); // ✅ FIXED
    }

}
