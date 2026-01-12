package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    /* ✅ CHANGED:
       - datePosted ❌ (does not exist in Announcement model)
       - announcementDate ✅ (this exists in your Announcement model)
    */
    List<Announcement> findTop3ByOrderByAnnouncementDateDesc();
}
