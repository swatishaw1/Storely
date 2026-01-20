package com.example.StorelyBackend.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.OutputStream;

public interface EncrypDecryp {
    OutputStream encrypt(MultipartFile file, int key);
    OutputStream decrypt(MultipartFile file,int key);
}
