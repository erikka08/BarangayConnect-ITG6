package com.example.barangayconnect.controller;

import com.example.barangayconnect.model.Feedback;
import com.example.barangayconnect.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*") // allow frontend calls
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ✅ Save Feedback
    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        feedback.setDate(java.time.LocalDateTime.now());
        feedback.setReadStatus(false);
        return feedbackRepository.save(feedback);
    }

    // ✅ Get All Feedback (for Admin)
    @GetMapping("/all")
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

}
