package com.example.barangayconnect.dto;

import com.fasterxml.jackson.annotation.JsonInclude; // <-- ADDED

// Allow missing fields (such as remarks) without causing errors
@JsonInclude(JsonInclude.Include.NON_NULL)  // <-- ADDED
public class RequestStatusDto {

    private String status;
    private String remarks;

    public RequestStatusDto() {}

    public RequestStatusDto(String status, String remarks) {
        this.status = status;
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
