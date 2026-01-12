package com.example.barangayconnect.controller;

import com.example.barangayconnect.dto.ReportDto;
import com.example.barangayconnect.model.Report;
import com.example.barangayconnect.service.ReportService;
import com.example.barangayconnect.service.ActivityLogService; // ✅ ADDED

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")   // Allow frontend calls
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    // ⭐ FIXED: Proper Autowired placement
    @Autowired
    private ReportService reportService;

    @Autowired
    private ActivityLogService activityLogService; // ✅ ADDED

    // ⭐ UNTOUCHED — Load all active reports
    @GetMapping("/all")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // ⭐ NEW — Load cancelled reports only
    @GetMapping("/cancelled")
    public List<Report> getCancelledReports() {
        return reportService.getCancelledReports();
    }

    // ⭐ UNTOUCHED — Submit report
    @PostMapping("/submit")
    public String submitReport(
            @RequestPart("data") ReportDto data,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            Report report = new Report();
            report.setTitle(data.getTitle());
            report.setCategory(data.getCategory());
            report.setLocation(data.getLocation());
            report.setDescription(data.getDescription());

            if (imageFile != null) {
                report.setImage(imageFile.getBytes());
            }

            reportService.saveReport(report);

            // ✅ ADDED: RECORD LOG FOR REPORT SUBMISSION
            activityLogService.record(
                    "New report submitted",
                    "A report \"" + data.getTitle() + "\" was submitted",
                    "NEW",
                    "fa-file-alt"
            );

            return "SUCCESS";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    // ⭐ UNTOUCHED — Cancel report endpoint
    @PutMapping("/cancel/{id}")
    public String cancelReport(@PathVariable Long id) {
        try {

            // ✅ ADDED: Get report details before cancelling
            Report report = reportService.getReportById(id.intValue());

            boolean updated = reportService.cancelReport(id);

            if (updated) {
                // ✅ ADDED: RECORD LOG FOR CANCEL
                activityLogService.record(
                        "Report cancelled",
                        "Report \"" + report.getTitle() + "\" was cancelled",
                        "UPDATED",
                        "fa-ban"
                );
            }

            return updated ? "SUCCESS" : "ERROR";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    // ⭐ UNTOUCHED — Fetch report by ID
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id);
    }

    // ⭐ NEW — UPDATE STATUS (Admin dropdown)
    // URL: PUT http://localhost:8080/api/reports/update-status/{id}
    @PutMapping("/update-status/{id}")
    public String updateStatus(@PathVariable Integer id, @RequestBody String status) {
        try {
            // remove quotes from JSON "Resolved"
            status = status.replace("\"", "");

            // ✅ ADDED: Get report info first before updating
            Report report = reportService.getReportById(id);
            String oldStatus = report.getStatus();

            boolean updated = reportService.updateReportStatus(id, status);

            if (updated) {
                // ✅ ADDED: RECORD LOG FOR STATUS UPDATE
                activityLogService.record(
                        "Report status updated",
                        "Report \"" + report.getTitle() + "\" changed from " + oldStatus + " to " + status,
                        "UPDATED",
                        "fa-check-circle"
                );
            }

            return updated ? "SUCCESS" : "ERROR";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

} // END OF CLASS
