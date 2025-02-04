package com.example.llm_service.services;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface ArchiveService {
    void saveArchive(String question, String response);
}
