package co.edu.escuelaing.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa una base de datos de usuarios en memoria.
 */
public class UsuariosDB {

    /**
     * Mapa que representa la base de datos de usuarios.
     */
    private static Map<String, String> BD = new HashMap<>();

     /**
     * Método principal que inicializa la aplicación y configura las rutas de Spark.
     *
     * @param args Argumentos de la línea de comandos.
     * @throws NoSuchAlgorithmException Si ocurre un error al crear el hasher de contraseñas.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

        BD.put("MarianaP", PasswordHasher.HashPassword("juniorluna21"));
        BD.put("apolo20", PasswordHasher.HashPassword("juliana28"));
        BD.put("Juanita5", PasswordHasher.HashPassword("twicenayeon5"));

        // Spark configuration
        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());
        get("/login", (req, res) -> {
            String username = req.queryParams("user");
            String password = req.queryParams("pass");

            if (!BD.containsKey(username)) {
                return "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Usuario No encontrado</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            background-color: #000000;\n" +
                        "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                        "            color: #FAEBD7;\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            align-items: center;\n" +
                        "            height: 100vh;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .button {\n" +
                        "            display: inline-block;\n" +
                        "            padding: 10px 20px;\n" +
                        "            background-color: #FF69B4;\n" +
                        "            color: #000000;\n" +
                        "            text-decoration: none;\n" +
                        "            border-radius: 5px;\n" +
                        "            margin-top: 20px;\n" +
                        "        }\n" +
                        "        .button:hover {\n" +
                        "            background-color: #E6E6FA;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <h1>Usuario No encontrado, Ingrese Un usuario Valido</h1>\n" +
                        "        <a href=\"/index.html\" class=\"button\">Volver al Inicio</a>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
            } else if (password.equals("")) {
                return "Porfavor Digite la clave correpondiente :)";
            }

            if (PasswordHasher.validatePassword(password, BD.get(username))) {
                return "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Mensaje de éxito</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            background-color: #000000;\n" +
                        "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                        "            color: #FAEBD7;\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            align-items: center;\n" +
                        "            height: 100vh;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .button {\n" +
                        "            display: inline-block;\n" +
                        "            padding: 10px 20px;\n" +
                        "            background-color: #FF69B4;\n" +
                        "            color: #000000;\n" +
                        "            text-decoration: none;\n" +
                        "            border-radius: 5px;\n" +
                        "            margin-top: 20px;\n" +
                        "        }\n" +
                        "        .button:hover {\n" +
                        "            background-color: #E6E6FA;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <h1>Ha entrado exitosamente :)</h1>\n" +
                        "        <a href=\"/index.html\" class=\"button\">Volver al Inicio</a>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
            } else {
                return "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Mensaje Fallo</title>\n" +
                        "    <style>\n" +
                        "        body {\n" +
                        "            background-color: #000000;\n" +
                        "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                        "            color: #FAEBD7;\n" +
                        "            display: flex;\n" +
                        "            justify-content: center;\n" +
                        "            align-items: center;\n" +
                        "            height: 100vh;\n" +
                        "            margin: 0;\n" +
                        "        }\n" +
                        "        .container {\n" +
                        "            text-align: center;\n" +
                        "        }\n" +
                        "        .button {\n" +
                        "            display: inline-block;\n" +
                        "            padding: 10px 20px;\n" +
                        "            background-color: #FF69B4;\n" +
                        "            color: #000000;\n" +
                        "            text-decoration: none;\n" +
                        "            border-radius: 5px;\n" +
                        "            margin-top: 20px;\n" +
                        "        }\n" +
                        "        .button:hover {\n" +
                        "            background-color: #E6E6FA;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"container\">\n" +
                        "        <h1>Clave Incorrecta</h1>\n" +
                        "        <a href=\"/index.html\" class=\"button\">Volver al Inicio</a>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
            }
        });
    }

     /**
     * Obtiene el puerto en el que se ejecutará la aplicación.
     *
     * @return El puerto en el que se ejecutará la aplicación.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 46000;
    }
}
