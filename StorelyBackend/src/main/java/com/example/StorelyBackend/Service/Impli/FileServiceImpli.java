package com.example.StorelyBackend.Service.Impli;

import com.example.StorelyBackend.Service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpli implements FileService {
    @Override
    public ResponseEntity<String> uploadFile(MultipartFile[] files) throws IOException {
        String uploads = "uploads/";
        File directory = new File(uploads);
        if (!directory.exists()){
            directory.mkdir();
        }
        if (files.length==0||files==null) {
            return ResponseEntity.badRequest().body("File not Found");
        }
        for (MultipartFile file: files){
            Path path = Paths.get(uploads, file.getOriginalFilename());
            Files.write(path, file.getBytes());
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }
}
