package co.edu.escuelaing.arep;


import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;


/**
 * Clase para leer contenido de una URL de forma segura utilizando SSL.
 */
public class SecureURLReader {


     /**
     * Lee el contenido de una URL de forma segura utilizando SSL y con autenticación básica.
     *
     * @param username El nombre de usuario para la autenticación básica.
     * @param password La contraseña para la autenticación básica.
     * @return El contenido de la URL como una cadena.
     * @throws KeyStoreException Si ocurre un error al cargar el almacén de claves.
     * @throws FileNotFoundException Si no se encuentra el archivo del almacén de claves.
     * @throws IOException Si ocurre un error de entrada/salida al leer la URL.
     * @throws NoSuchAlgorithmException Si el algoritmo SSL no está soportado.
     * @throws CertificateException Si ocurre un error al cargar el certificado.
     * @throws KeyManagementException Si ocurre un error al inicializar el contexto SSL.
     */
    public static String secureReadUrl(String username, String password) {
        String secureResponse = "";
        try {

            File trustStoreFile = new File("certificados/myTrustStore.p12");
            char[] trustStorePassword = "123456".toCharArray();


            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);


            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            tmf.init(trustStore);


            for (TrustManager t : tmf.getTrustManagers()) {
                System.out.println(t);
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);


            secureResponse = readURL("https://localhost:46000/login?user=" + username + "&pass=" + password);


        } catch (KeyStoreException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return secureResponse;
    }

    /**
     * Lee el contenido de una URL de forma no segura.
     *
     * @param siteToRead La URL a leer.
     * @return El contenido de la URL como una cadena.
     * @throws IOException Si ocurre un error de entrada/salida al leer la URL.
     */
    public static String readURL(String sitetoread) {
        StringBuffer response = new StringBuffer();
        try {

            URL siteURL = new URL(sitetoread);
            URLConnection urlConnection = siteURL.openConnection();

            System.out.println("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
        } catch (IOException x) {
            response.append(x);
        }
        return response.toString();
    }
}
