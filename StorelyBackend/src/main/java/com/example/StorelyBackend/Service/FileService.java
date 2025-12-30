package com.example.StorelyBackend.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    ResponseEntity<String> uploadFile(MultipartFile[] file) throws IOException;
    InputStream getFiles(String path,String fileName);
}
