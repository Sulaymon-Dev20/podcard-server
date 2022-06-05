package com.example.podcastserver.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class PasswordEncoder {
    public static String encryptToSHA256(String line) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        line = "ASDUM_SALT_KEY_FOR_GENERATE_SHA256_PASSWORD" + line;
        Objects.requireNonNull(digest).update(line.getBytes());
        byte[] byteData = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
