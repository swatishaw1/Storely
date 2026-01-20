package com.example.StorelyBackend.Controller;



import com.example.StorelyBackend.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        return fileService.uploadFile(files);
    }

    //Method to serve File
    @GetMapping(value = "/uploads/{fileName}",produces = MediaType.ALL_VALUE)
    public void serveFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        fileService.getFiles(fileName,response);
    }
}
