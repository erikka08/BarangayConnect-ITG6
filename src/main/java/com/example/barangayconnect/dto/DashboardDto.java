package com.example.barangayconnect.dto;

public class DashboardDto {

    private String status;
    private Long requestsCount;
    private Long notificationsCount;

    public DashboardDto() {}

    public DashboardDto(String status, Long requestsCount, Long notificationsCount) {
        this.status = status;
        this.requestsCount = requestsCount;
        this.notificationsCount = notificationsCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(Long requestsCount) {
        this.requestsCount = requestsCount;
    }

    public Long getNotificationsCount() {
        return notificationsCount;
    }

    public void setNotificationsCount(Long notificationsCount) {
        this.notificationsCount = notificationsCount;
    }
}
