package com.example.barangayconnect.service;

import com.example.barangayconnect.dto.DashboardDto;
import com.example.barangayconnect.model.Resident;
import com.example.barangayconnect.repository.RequestRepository;
import com.example.barangayconnect.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private RequestRepository requestRepository;

    public DashboardDto getDashboardData(String residentId) {

        // ✅ Find resident
        Resident resident = residentRepository.findById(Integer.parseInt(residentId)).orElse(null);

        // ✅ Status fallback
        String status = "Pending";
        if (resident != null && resident.getStatus() != null) {
            status = resident.getStatus();
        }

        // ✅ Count requests for this residentId (STRING)
        Long requestCount = requestRepository.countByResidentId(residentId);

        // ✅ Notifications not yet implemented
        Long notifCount = 0L;

        return new DashboardDto(status, requestCount, notifCount);
    }
}
