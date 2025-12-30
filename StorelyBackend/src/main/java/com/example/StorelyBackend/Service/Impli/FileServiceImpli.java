package com.example.StorelyBackend.Service.Impli;

import com.example.StorelyBackend.Service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpli implements FileService {
    @Override
    public ResponseEntity<String> uploadFile(MultipartFile[] files) throws IOException {
        String uploads = "uploads";
        File directory = new File(uploads);
        if (!directory.exists()){
            directory.mkdir();
        }
        if (files.length==0||files==null) {
            return new ResponseEntity<>("File Not Found", HttpStatus.NOT_FOUND);
        }
        for (MultipartFile file: files){
            String name = UUID.randomUUID().toString()+file.getOriginalFilename()+File.separator;
            Path path = Paths.get(uploads, name);
            Files.write(path, file.getBytes());
        }
        return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
    }

    @Override
    public InputStream getFiles(String path, String fileName) {
        String fullname = path + File.separator + fileName;
        //File.seperator has / or \ it added where it is needed
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(fullname);//It reads file byte by byte
            //DB logic to return input string
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//if file not found
        }
        return inputStream;
    }
}