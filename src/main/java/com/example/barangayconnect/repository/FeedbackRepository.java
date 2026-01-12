package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
