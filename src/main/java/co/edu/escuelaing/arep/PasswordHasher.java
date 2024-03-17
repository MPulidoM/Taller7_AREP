package co.edu.escuelaing.arep;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para el hasheo de contraseñas utilizando el algoritmo SHA-3 de 256 bits.
 */
public class PasswordHasher {
    
     /**
     * Hashea una contraseña utilizando el algoritmo SHA-3 de 256 bits.
     *
     * @param password La contraseña a hashear.
     * @return El hash de la contraseña.
     * @throws NoSuchAlgorithmException Si el algoritmo SHA-3 de 256 bits no está soportado.
     */
    public static String HashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA3-256");

        byte[] hash = md.digest(password.getBytes());

        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    
    /**
     * Valida una contraseña hasheada contra un hash almacenado.
     *
     * @param passToValidate La contraseña a validar.
     * @param correctPassword El hash almacenado de la contraseña correcta.
     * @return Verdadero si la contraseña es válida, falso en caso contrario.
     * @throws NoSuchAlgorithmException Si el algoritmo SHA-3 de 256 bits no está soportado.
     */
    public static boolean validatePassword(String passToValidate, String correctPassword)
            throws NoSuchAlgorithmException {
        System.out.println(PasswordHasher.HashPassword(passToValidate));
        boolean resp = correctPassword.equals(PasswordHasher.HashPassword(passToValidate));
        return resp;
    }
}
