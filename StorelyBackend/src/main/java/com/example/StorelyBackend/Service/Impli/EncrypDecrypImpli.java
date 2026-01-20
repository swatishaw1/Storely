package com.example.StorelyBackend.Service.Impli;
import com.example.StorelyBackend.Service.EncrypDecryp;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EncrypDecrypImpli implements EncrypDecryp {
    @Override
    public OutputStream encrypt(MultipartFile file, int key) {
        return null;
    }

    @Override
    public OutputStream decrypt(MultipartFile file, int key) {
        return null;
    }
}
