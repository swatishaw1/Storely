package com.example.StorelyBackend.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

public interface EncrypDecryp {
    OutputStream encrypt(MultipartFile file);
    void decrypt(InputStream file, OutputStream outputStream);
}
