package com.rjasw.demo.jpa_sql.api.msg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageSrvc {

	private final Path uploadDir = Paths.get("uploads");

    public FileStorageSrvc() {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // ensures the path is created
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public String store(MultipartFile file) {
        // For example: save to /uploads/ and return the path
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get("uploads/" + fileName);
        try {
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName; // Or a full URL if hosted
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
	
}
