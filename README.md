# Taller 7: APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

Este laboratorio se enfoco en el desarrollo de una aplicación web segura utilizando Spark como framework para la creación de un servicio de inicio de sesión. El objetivo principal es demostrar la implementación de autenticación, autorización y garantizar la integridad de los usuarios, cumpliendo así con los estándares de seguridad necesarios para proteger la información sensible.

Para lograr esto, se han creado credenciales personalizadas que permiten el acceso seguro a la aplicación. Además, se utiliza el protocolo HTTPS para asegurar la comunicación entre el navegador y la aplicación, lo que añade una capa adicional de seguridad para proteger la información durante la transferencia.

Una de las características destacadas de este laboratorio es el despliegue del servicio en una instancia de EC2 en AWS (Amazon Web Services). Esto proporciona un entorno escalable y confiable para ejecutar la aplicación, asegurando su disponibilidad y rendimiento incluso ante un aumento en la demanda de usuarios.

## Empezando

El proyecto contiene:

- Se tiene el siguiente directorio [co/edu/escuelaing/arep](https://github.com/MPulidoM/Taller7_AREP/tree/main/src/main/java/co/edu/escuelaing/arep) que tiene:

--> La clase [HelloWorld](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/java/co/edu/escuelaing/arep/HelloWorld.java) Esta crea una aplicación web utilizando el framework Spark, configurando HTTPS, manejando solicitudes GET y POST, y sirviendo archivos estáticos. La aplicación se ejecuta en el puerto especificado por la variable de entorno "PORT" o en el puerto 5000 si no se proporciona dicha variable.

--> La clase [PasswordHasher](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/java/co/edu/escuelaing/arep/PasswordHasher.java) Esta proporciona dos métodos para el hashing y la validación de contraseñas utilizando el algoritmo SHA-3 de 256 bits. 

--> La clase[SecureURLReader](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/java/co/edu/escuelaing/arep/SecureURLReader.java) Esta proporciona dos métodos para leer el contenido de una URL. El método secureReadUrl toma dos parámetros: un nombre de usuario y una contraseña. proporciona una forma segura de leer el contenido de una URL, utilizando SSL y un almacén de confianza para autenticar al servidor y evitar ataques de intercepción de datos.

--> La clase [UsuariosDB](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/java/co/edu/escuelaing/arep/UsuariosDB.java) Esta crea una base de datos simulada de usuarios y contraseñas hasheadas y proporciona una ruta GET en "/login" para autenticar a los usuarios. La clase también proporciona un método para devolver el puerto en el que el servidor debe ejecutarse.

- Se tienen los [Recursos](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/java/co/edu/escuelaing/arep/UsuariosDB.java) :

--> [Página](https://github.com/MPulidoM/Taller7_AREP/blob/main/src/main/resources/public/index.html) este código HTML crea un formulario de inicio de sesión con dos campos de entrada de texto y un botón de envío, y utiliza JavaScript y la API Fetch para enviar una solicitud HTTP POST al servidor cuando se hace clic en el botón de envío.

## Requisitos previos

[Docker](https://www.docker.com/): Proporciona un entorno de contenedorización que garantiza la consistencia y portabilidad de la aplicación.

[Maven](https://maven.apache.org/) : Con esta herramienta se creo la estructura del proyecto y se manejan las dependencias que se necesitan

[Git](https://git-scm.com/) : Se basa en un sistema de control de versiones distribuido, donde cada desarrollador tiene una copia completa del historial del proyecto.

Para asegurar una correcta instalación de Maven, es crucial confirmar que la versión del JDK de Java sea compatible. Si el JDK no está actualizado, la instalación de las versiones actuales de Maven podría fallar, generando problemas durante el uso de la herramienta.
```
java -version 
```

## Arquitectura

El proyecto está compuesto por tres clases Java y un archivo HTML con JavaScript.

- La primera clase, PasswordHasher, proporciona dos métodos estáticos para el hasheo y la validación de contraseñas utilizando el algoritmo SHA-3 de 256 bits. El método HashPassword toma una cadena de texto como parámetro y devuelve una cadena de texto que representa el hash de la contraseña. El método validatePassword toma dos cadenas de texto como parámetros: la contraseña a validar y la contraseña correcta (que ya ha sido hasheada). El método devuelve un valor booleano que indica si la contraseña a validar coincide con la contraseña correcta.

- La segunda clase, SecureURLReader, proporciona un método estático para leer el contenido de una URL de forma segura utilizando SSL y un almacén de confianza. El método secureReadUrl toma dos parámetros: un nombre de usuario y una contraseña. La función crea un contexto SSL y utiliza un almacén de confianza para establecer una conexión segura con el servidor. El método readURL toma una URL como parámetro y devuelve el contenido de la URL como una cadena de texto. La función crea una conexión URL y lee el contenido de la URL utilizando un objeto BufferedReader.

- La tercera clase, UsuariosDB, crea una base de datos simulada de usuarios y contraseñas hasheadas utilizando un mapa de Java. La clase proporciona un método main que inicializa el servidor Spark y configura una ruta GET en "/login" para autenticar a los usuarios. El método main crea un mapa de usuarios y contraseñas hasheadas utilizando la clase PasswordHasher. La ruta "/login" toma dos parámetros de consulta: "user" y "pass". La clase UsuariosDB también proporciona un método getPort() que devuelve el puerto en el que el servidor Spark debe ejecutarse. Si la variable de entorno "PORT" está definida, el método devuelve el valor de esa variable. De lo contrario, el método devuelve el puerto 46000.

En cuanto a la arquitectura general del proyecto, tenemos tres clases Java y un archivo HTML con JavaScript. La clase PasswordHasher proporciona métodos para el hasheo y la validación de contraseñas utilizando el algoritmo SHA-3 de 256 bits. La clase SecureURLReader proporciona un método estático para leer el contenido de una URL de forma segura utilizando SSL y un almacén de confianza. La clase UsuariosDB crea una base de datos simulada de usuarios y contraseñas hasheadas utilizando un mapa de Java, inicializa el servidor Spark y configura una ruta GET en "/login" para autenticar a los usuarios. El archivo HTML proporciona un formulario de inicio de sesión con dos campos de entrada de texto para el nombre de usuario y la contraseña, y un botón de envío. El JavaScript en el archivo HTML maneja la presentación del formulario y la respuesta del servidor.

## Diseño

El proyecto que has proporcionado parece ser una aplicación web escrita en Java utilizando el framework Spark para el backend y HTML/CSS/JavaScript para el frontend. Aquí tienes un resumen del diseño del proyecto:

--> Backend (Java/Spark):

El backend está escrito en Java utilizando el framework Spark, que es un framework web ligero para Java.

- Se definen diferentes rutas para manejar las solicitudes HTTP utilizando métodos como get y post.
- Se utiliza seguridad SSL para conexiones seguras, como se indica en el uso de secure() y el manejo de certificados.
- Se implementan funcionalidades como el hashing de contraseñas, validación de contraseñas, y acceso a una base de datos simulada de usuarios.
  
--> Frontend (HTML/CSS/JavaScript):

- El frontend está compuesto por un formulario de inicio de sesión HTML.
- Se utiliza CSS para estilizar el formulario y la página en general.
- Se incorpora JavaScript para realizar una solicitud de inicio de sesión asincrónica utilizando fetch().
- Se muestra un mensaje de respuesta en la página después de enviar el formulario de inicio de sesión.
  
--> Base de Datos (Simulada):

- La base de datos de usuarios está simulada utilizando un mapa (HashMap) en Java.
- Los nombres de usuario y las contraseñas se almacenan en este mapa para la validación durante el inicio de sesión.
  
--> Seguridad:

- Se utiliza el algoritmo de hash SHA3-256 para almacenar las contraseñas de los usuarios de forma segura.
- Se implementa la validación de contraseñas hash durante el proceso de inicio de sesión.
  
--> Flujo de la Aplicación:

- El usuario ingresa su nombre de usuario y contraseña en el formulario de inicio de sesión.
- Cuando el usuario envía el formulario, se realiza una solicitud POST al backend utilizando JavaScript.
- El backend valida las credenciales del usuario y devuelve una respuesta adecuada.
- El mensaje de respuesta se muestra en la página HTML del frontend.
  
El proyecto es una aplicación web de inicio de sesión seguro que utiliza tecnologías Java para el backend y HTML/CSS/JavaScript para el frontend, con medidas de seguridad implementadas para proteger las contraseñas de los usuarios y garantizar conexiones seguras a través de SSL.

## Instalando

Debemos clonar el repositorio:
```
git clone https://github.com/MPulidoM/Taller6_AREP_2.git
```
Se accede al directorio del proyecto:
```
cd Taller6_AREP_2
```
Damos el siguiente comando de Maven:
```
mvn clean install
```
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/fa307be1-7b1c-4c0e-9a5a-cc38eddd646b)

Se corren las clases correspondientes , cada una en una linea de comandos independiente (; en Windows - : Linux )
```
java -cp "target/classes;target/dependency/*" co.edu.escuelaing.arep.HelloWorld
```
```
java -cp "target/classes;target/dependency/*" co.edu.escuelaing.arep.UsuariosDB
```


 ![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/a67f24e3-999d-41b0-8fcd-b335e81b2048)

Despues teniendo ambas corriendo entramos al link de la página

```
https://localhost:5000/index.html
```
**Tenga en cuenta** Que al abrir la pagina puede salir un mensaje diciendo que no es seguro , ignore el aviso y dele continuar aceptando el riesgo. Esto sucede por las llaves que se han generado, ignorar este aviso no hara ningun daño a su maquina.

 ![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/af35a25f-0eba-4fa8-a11c-f14162086a4b)

## Pruebas

- *LOCAL*

**Usuario Inexistente (Como vemos debemos dar al boton de volver al inicio para poder regresar a la pagina del login):**

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/bcc2efc2-02cf-4512-be2a-ce21666c7183)

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/57ad8eae-2ed8-43ab-a8b8-5cf592143af3)

**Usuario Correcto:**

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/f306d42d-b755-43c7-92b2-fba419758e82)


![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/ec5a559d-f0c4-4063-8e27-813dc1f28247)

**Clave Incorrecta:**
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/68443452-9b5a-4af6-baa8-3f88e6cd0fda)

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/b71da068-e7b5-410e-bc84-5e9e1f570316)

**Otros Usuarios:**

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/95fec903-edca-4df6-99e0-68fe9ca3cf03)
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/860535b3-cdfc-44c6-bc56-93cd724d845c)

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/56c76255-509c-470f-a304-6da0e508c9cf)

![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/0d935f46-d730-427e-be34-50fe8bd140df)

- *AWS*

**Corriendo el proyecto en la maquina**
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/6d2fb0da-ce92-46f0-88c7-de398bf952c6)


**Prueba de Funcionamiento**
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/ea07f133-e148-4c8c-b8b0-db883ecf671b)
![image](https://github.com/MPulidoM/Taller7_AREP/assets/118181543/e96075dc-1a2a-4824-b123-3b8fdb0ded6f)














