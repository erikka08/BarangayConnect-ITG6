package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", unique = true)
    private String requestId;

    @Column(name = "resident_id")
    private String residentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String email;
    private String mobile;
    private String address;

    private LocalDate birthdate;

    @Column(name = "document_type")
    private String documentType;

    // Community / event
    private String purpose;

    @Column(name = "event_place")
    private String eventPlace;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "event_time")
    private LocalTime eventTime;

    // Legal / complaints
    @Column(name = "incident_details", columnDefinition = "TEXT")
    private String incidentDetails;

    @Column(name = "incident_date")
    private LocalDate incidentDate;

    @Column(name = "incident_location")
    private String incidentLocation;

    @Column(name = "parties_involved", columnDefinition = "TEXT")
    private String partiesInvolved;

    // Business / property
    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_location", columnDefinition = "TEXT")
    private String businessLocation;

    @Column(name = "dti_sec_registration")
    private String dtiSecRegistration;

    @Column(name = "construction_purpose")
    private String constructionPurpose;

    @Column(name = "request_notes", columnDefinition = "TEXT")
    private String requestNotes;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    private String status;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadedFile> uploadedFiles = new ArrayList<>();

    public Request() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getResidentId() { return residentId; }
    public void setResidentId(String residentId) { this.residentId = residentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getEventPlace() { return eventPlace; }
    public void setEventPlace(String eventPlace) { this.eventPlace = eventPlace; }

    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public LocalTime getEventTime() { return eventTime; }
    public void setEventTime(LocalTime eventTime) { this.eventTime = eventTime; }

    public String getIncidentDetails() { return incidentDetails; }
    public void setIncidentDetails(String incidentDetails) { this.incidentDetails = incidentDetails; }

    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }

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

    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<UploadedFile> getUploadedFiles() { return uploadedFiles; }
    public void setUploadedFiles(List<UploadedFile> uploadedFiles) { this.uploadedFiles = uploadedFiles; }

    public void addUploadedFile(UploadedFile file) {
        uploadedFiles.add(file);
        file.setRequest(this);
    }
}
