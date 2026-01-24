package com.example.StorelyBackend.Service.Impli;
import com.example.StorelyBackend.Service.EncrypDecryp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncrypDecrypImpli implements EncrypDecryp {

    private final SecretKey secretKey;
    private final byte[] IV;
    private final Integer T_LEN;

    public EncrypDecrypImpli(@Value("${secret_key_String}") String secretKey, @Value("${IV_String}") String iv,@Value("${tLen}") Integer tLen) {
        this.secretKey = new SecretKeySpec(decode(secretKey),"AES");
        IV = decode(iv);
        T_LEN = tLen;
    }

    private static String encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] decode(String encryptedString){
        return Base64.getDecoder().decode(encryptedString);
    }

    @Override
    public OutputStream encrypt(MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            Cipher encryptedCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
            encryptedCipher.init(Cipher.ENCRYPT_MODE,secretKey,spec);
            byte[] encryptedBytes = encryptedCipher.doFinal(data);
            OutputStream out =
                    new ByteArrayOutputStream() {{
                        write(encode(data).getBytes(StandardCharsets.UTF_8));
                    }};
            return out;
        } catch (Exception e) {
            System.out.println("The Error is in Encrption: "+e.getMessage());
        }
        return null;
    }

    @Override
    public void decrypt(InputStream file,OutputStream outputStream) {
        try {
            byte[] encryptedBytes = file.readAllBytes();
            Cipher decryptedCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec spec = new GCMParameterSpec(T_LEN, IV);
            decryptedCipher.init(Cipher.DECRYPT_MODE,secretKey,spec);
            byte[] decryptedBytes = decryptedCipher.doFinal(encryptedBytes);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(decryptedBytes);
        } catch (Exception e) {
            System.out.println("The Error is in Decrypt: "+e.getMessage());
        }
    }
}
