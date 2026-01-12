package com.example.barangayconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.barangayconnect.repository.ReportRepository;
import com.example.barangayconnect.model.Report;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // ⭐ ADDED — Return only cancelled reports
    public List<Report> getCancelledReports() {
        return reportRepository.findByStatus("Cancelled");
    }

    public boolean cancelReport(Long id) {
        Report report = reportRepository.findById(id.intValue()).orElse(null);
        if (report == null) return false;

        report.setStatus("Cancelled");
        reportRepository.save(report);
        return true;
    }

    public Report getReportById(Integer id) {
        return reportRepository.findById(id).orElse(null);
    }

    // ⭐⭐⭐ ADDED — THE MISSING UPDATE STATUS METHOD (REQUIRED BY YOUR CONTROLLER)
    public boolean updateReportStatus(Integer id, String status) {

        Report report = reportRepository.findById(id).orElse(null);
        if (report == null) return false;

        report.setStatus(status);
        reportRepository.save(report);
        return true;
    }
}