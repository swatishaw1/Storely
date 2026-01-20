package com.example.StorelyBackend.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ResponseEntity<String> uploadFile(MultipartFile[] file) throws IOException;
    void getFiles(String fileName, HttpServletResponse response) throws IOException;
}
