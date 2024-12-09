package com.amatta.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Configuration
@EnableEncryptableProperties
class JasyptConfigTest {

    @Test
    public void encryptDecryptTest() {
        String text = "amatta";//변경할 값

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        encryptor.setPassword("amatta");
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        String encryptedText = encryptor.encrypt(text);
        System.out.println("enc : " + encryptedText);

        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("dec : " + decryptedText);

        assertEquals(text, decryptedText);

        List.of("")
            .stream()
            .forEach(s -> {
                System.out.println(s + " -> ENC(" + encryptor.encrypt(s)+")");
            });
    }
}