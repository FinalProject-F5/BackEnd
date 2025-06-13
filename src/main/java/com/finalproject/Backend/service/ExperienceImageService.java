package com.finalproject.Backend.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExperienceImageService {

    @Value("${file.upload.directory:uploads/experiences}")
    private String uploadDirectory;

    public String saveBase64File(String base64Data, String originalName, String contentType) throws IOException { 

        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
 
        String fileExtension = getFileExtension(originalName);
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
         
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
         
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.write(filePath, decodedBytes);
        
        return filePath.toString();
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex);
    }

    public void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("Error deleting file: " + filePath);
        }
    }

    public String extractFileName(String filePath) {
        return Paths.get(filePath).getFileName().toString();
    }
}