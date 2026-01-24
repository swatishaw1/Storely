package com.example.StorelyBackend.Service.Impli;

import com.example.StorelyBackend.Service.EncrypDecryp;
import com.example.StorelyBackend.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EncrypDecryp encrypDecryp;

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
            OutputStream fileOutputStream = encrypDecryp.encrypt(file);
            Files.write(path, ((ByteArrayOutputStream)fileOutputStream).toByteArray());
        }
        return new ResponseEntity<>("File Uploaded Successfully", HttpStatus.OK);
    }



    @Override
    public void getFiles(String fileName, HttpServletResponse response) throws IOException {
        fileName = Paths.get(fileName).getFileName().toString();//This prevents attack
        Path path = Paths.get("uploads").resolve(fileName);//Build actual paths
        response.setContentType(Files.probeContentType(path));//detects file type
        response.setHeader("Content-Disposition",
                "inline; filename=\"" + fileName + "\"");//To see the documents in browser
        InputStream encryptedFile = Files.newInputStream(path);//Encrypted Files
        encrypDecryp.decrypt(encryptedFile,response.getOutputStream());
        response.getOutputStream().flush();//Sends all remaining bytes to client
    }

    /*@Override
    public void getFiles(String fileName, HttpServletResponse response) throws IOException {
        fileName = Paths.get(fileName).getFileName().toString();//This prevents attack
        Path path = Paths.get("uploads").resolve(fileName);//Build actual paths
        response.setContentType(Files.probeContentType(path));//detects file type
        response.setHeader("Content-Disposition",
                "inline; filename=\"" + fileName + "\"");//To see the documents in browser
        *//*OutputStream outputStream = encrypDecryp.decrypt(response.getOutputStream());*//*
        Files.copy(path, response.getOutputStream());//Write files to http responce
        response.getOutputStream().flush();//Sends all remaining bytes to client
    }*/

}