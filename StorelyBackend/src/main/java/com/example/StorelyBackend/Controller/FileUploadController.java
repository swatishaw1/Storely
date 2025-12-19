package com.example.StorelyBackend.Controller;


import com.example.StorelyBackend.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile[] file) throws IOException {
        return fileService.uploadFile(file);
    }
}
