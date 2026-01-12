package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {

    List<ActivityLog> findTop6ByOrderByCreatedAtDesc();
}
