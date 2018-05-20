package com.enos.enos.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AuthenticationUtils {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationUtils.class.getName());

    public static String generateSalt() {

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");

            byte[] salt = new byte[50];
            sr.nextBytes(salt);
            return Base64.getEncoder().encodeToString(salt);

        } catch(NoSuchAlgorithmException|NoSuchProviderException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return "";
    }

    public static String getEncryptedPassword(String password, String salt) {

        String encryptedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedPassword = sb.toString();
        } catch(NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return encryptedPassword;
    }
}