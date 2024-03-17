package co.edu.escuelaing.arep;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordHasher {

    public static String HashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA3-256");

        byte[] hash = md.digest(password.getBytes());

        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }


    public static boolean validatePassword(String passToValidate, String correctPassword)
            throws NoSuchAlgorithmException {
        System.out.println(PasswordHasher.HashPassword(passToValidate));
        boolean resp = correctPassword.equals(PasswordHasher.HashPassword(passToValidate));
        return resp;
    }
}
