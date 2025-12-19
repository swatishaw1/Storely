package com.example.StorelyBackend.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String uploads = "uploads/";
        File directory = new File(uploads);
        if (!directory.exists()){
            directory.mkdirs();
        }
        Path path = Paths.get(uploads,file.getOriginalFilename());
        Files.write(path,file.getBytes());
        return ResponseEntity.ok("File uploaded successfully"+file.getOriginalFilename()+path);
    }
}
