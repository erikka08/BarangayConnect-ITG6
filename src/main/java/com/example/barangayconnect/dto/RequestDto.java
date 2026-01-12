package com.example.barangayconnect.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) // allows requestStatus etc.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDto {

    private Long id;
    private String requestId;
    private String residentId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;

    // SAFE MODE: accept "" and null
    private String birthdate;

    private String documentType;

    private String purpose;
    private String eventPlace;
    private String organizationName;

    private String eventDate;
    private String eventTime;

    private String incidentDetails;
    private String incidentDate;
    private String incidentLocation;
    private String partiesInvolved;

    private String businessName;
    private String businessLocation;
    private String dtiSecRegistration;
    private String constructionPurpose;

    private String requestNotes;
    private String remarks;

    private String submissionDate;

    private String status;

    private List<UploadedRequirementDto> uploadedRequirements;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getResidentId() { return residentId; }
    public void setResidentId(String residentId) { this.residentId = residentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getEventPlace() { return eventPlace; }
    public void setEventPlace(String eventPlace) { this.eventPlace = eventPlace; }

    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getEventTime() { return eventTime; }
    public void setEventTime(String eventTime) { this.eventTime = eventTime; }

    public String getIncidentDetails() { return incidentDetails; }
    public void setIncidentDetails(String incidentDetails) { this.incidentDetails = incidentDetails; }

    public String getIncidentDate() { return incidentDate; }
    public void setIncidentDate(String incidentDate) { this.incidentDate = incidentDate; }

    public String getIncidentLocation() { return incidentLocation; }
    public void setIncidentLocation(String incidentLocation) { this.incidentLocation = incidentLocation; }

    public String getPartiesInvolved() { return partiesInvolved; }
    public void setPartiesInvolved(String partiesInvolved) { this.partiesInvolved = partiesInvolved; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessLocation() { return businessLocation; }
    public void setBusinessLocation(String businessLocation) { this.businessLocation = businessLocation; }

    public String getDtiSecRegistration() { return dtiSecRegistration; }
    public void setDtiSecRegistration(String dtiSecRegistration) { this.dtiSecRegistration = dtiSecRegistration; }

    public String getConstructionPurpose() { return constructionPurpose; }
    public void setConstructionPurpose(String constructionPurpose) { this.constructionPurpose = constructionPurpose; }

    public String getRequestNotes() { return requestNotes; }
    public void setRequestNotes(String requestNotes) { this.requestNotes = requestNotes; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<UploadedRequirementDto> getUploadedRequirements() { return uploadedRequirements; }
    public void setUploadedRequirements(List<UploadedRequirementDto> uploadedRequirements) { this.uploadedRequirements = uploadedRequirements; }
}
