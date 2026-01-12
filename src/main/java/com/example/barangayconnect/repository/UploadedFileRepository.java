package com.example.barangayconnect.repository;

import com.example.barangayconnect.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

    List<UploadedFile> findByRequestId(Long requestId);
}
