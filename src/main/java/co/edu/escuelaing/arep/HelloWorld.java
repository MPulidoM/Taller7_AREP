package co.edu.escuelaing.arep;

import static spark.Spark.*;

/**
 * Clase que define la configuración y los endpoints de la aplicación.
 *
 */
public class HelloWorld {

    /**
     * Método principal que configura y ejecuta la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);

         // Configura la seguridad de la aplicación utilizando un certificado.
        secure("certificados/ecikeystore.p12", "123456", null, null);

        // Configura el puerto en el que se ejecutará la aplicación.
        port(getPort());

         // Configura la ubicación de los archivos estáticos.
        staticFiles.location("/public");

        // Define el endpoint que devuelve un mensaje de bienvenida.
        get("/hello", (req, res) -> "Hello World");

        // Define el endpoint que maneja el inicio de sesión de un usuario.
        post("/login", (req, res) -> {
            // Obtiene el nombre de usuario y la contraseña del request.
            String username = req.queryParams("user");
            String password = req.queryParams("pass");
            
            // Llama al método que realiza la lectura segura de la URL.
            return SecureURLReader.secureReadUrl(username, password);
        });
    }

    /**
     * Devuelve el número de puerto en el que la aplicación debe escuchar.
     * Si la variable de entorno "PORT" está establecida, su valor se utilizará como número de puerto.
     * De lo contrario, se utilizará el número de puerto predeterminado 5000.
     *
     * @return el número de puerto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}
