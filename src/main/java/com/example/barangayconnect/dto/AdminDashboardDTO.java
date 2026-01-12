package com.example.barangayconnect.dto;

public class AdminDashboardDTO {

    private long totalResidents;
    private long reportsGenerated;
    private long pendingRequests;

    public AdminDashboardDTO(long totalResidents, long reportsGenerated, long pendingRequests) {
        this.totalResidents = totalResidents;
        this.reportsGenerated = reportsGenerated;
        this.pendingRequests = pendingRequests;
    }

    public long getTotalResidents() { return totalResidents; }
    public long getReportsGenerated() { return reportsGenerated; }
    public long getPendingRequests() { return pendingRequests; }
}
