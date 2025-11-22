package com.example.barangayconnect.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "residents")
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;

    @Column(name = "phone_num")
    private String phoneNum;

    private String email;
    private String password;

    private String status; // PENDING or ACTIVE
    private Boolean isLoggedIn;

    // ✔ Added correctly INSIDE the class
    private LocalDateTime approvedDateTime;

    // ✔ Added getters & setters for approvedDateTime
    public LocalDateTime getApprovedDateTime() {
        return approvedDateTime;
    }

    public void setApprovedDateTime(LocalDateTime approvedDateTime) {
        this.approvedDateTime = approvedDateTime;
    }

    // GETTERS & SETTERS BELOW

    public Integer getId() { 
        return id; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }

    public String getFirstname() { 
        return firstname; 
    }
    public void setFirstname(String firstname) { 
        this.firstname = firstname; 
    }

    public String getLastname() { 
        return lastname; 
    }
    public void setLastname(String lastname) { 
        this.lastname = lastname; 
    }

    public String getPhoneNum() { 
        return phoneNum; 
    }
    public void setPhoneNum(String phoneNum) { 
        this.phoneNum = phoneNum; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public Boolean getIsLoggedIn() { 
        return isLoggedIn; 
    }
    public void setIsLoggedIn(Boolean isLoggedIn) { 
        this.isLoggedIn = isLoggedIn; 
    }
}
