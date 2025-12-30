package com.example.StorelyBackend.Controller;



import com.example.StorelyBackend.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
        fileName = Paths.get(fileName).getFileName().toString();//This prevents attack
        Path path = Paths.get("uploads").resolve(fileName);//Build actual paths
        response.setContentType(Files.probeContentType(path));//detects file type
        response.setHeader("Content-Disposition",
                "inline; filename=\"" + fileName + "\"");//To see the documents in browser
        Files.copy(path, response.getOutputStream());//Write files to http responce
        response.getOutputStream().flush();//Sends all remaining bytes to client
    }
}
