# README

## Descripción del Proyecto

Este proyecto implementa un servidor HTTP en Java que maneja solicitudes de archivos estáticos y datos en formato JSON. El servidor puede servir archivos como HTML, CSS, JS, imágenes y también manejar solicitudes para crear y recuperar usuarios.

## Instalación

### Requisitos

- Java JDK 11 o superior
- Apache Maven (opcional, para gestionar dependencias)

### Pasos de Instalación

1. **Clonar el Repositorio**

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_DIRECTORIO>
   ```

2. **Compilar el Proyecto**

   ```bash
   mvn clean package
   ```

3. **Ejecutar el Proyecto**

   ```bash
   java -cp target/classes edu.escuelaing.arep.HttpServer
   ```
4. **Ejecutar las Pruebas**

   ```bash
   mvn test
   ```

## Arquitectura

### Componentes del Proyecto

#### HttpServer

- **Descripción:** Configura y arranca el servidor HTTP.
- **Responsabilidades:**
  - Acepta conexiones de clientes.
  - Delegar el manejo de solicitudes a `ClientHandler`.

#### ClientHandler

- **Descripción:** Maneja solicitudes HTTP.
- **Responsabilidades:**
  - Decide qué hacer basado en el método HTTP (GET o POST) y el recurso solicitado.
  - Utiliza `FileHandler` para servir archivos estáticos.
  - Utiliza `UsuarioHandler` para manejar usuarios.

#### FileHandler

- **Descripción:** Sirve archivos estáticos (HTML, CSS, JS, imágenes).
- **Responsabilidades:**
  - Determina el tipo MIME adecuado para el archivo solicitado.
  - Envía respuestas HTTP con el archivo solicitado.

#### UsuarioHandler

- **Descripción:** Maneja la lista de usuarios y la creación de nuevos usuarios.
- **Responsabilidades:**
  - Responde con datos en formato JSON para la lista de usuarios y para la creación de nuevos usuarios.

#### RequestParser

- **Descripción:** Parsear la línea de solicitud HTTP.
- **Responsabilidades:**
  - Extraer el método HTTP y el recurso solicitado a partir de la línea de solicitud.

#### Usuario

- **Descripción:** Representa un usuario.
- **Responsabilidades:**
  - Métodos para convertir a y desde JSON.

### Estructura de Archivos

- **`src/main/java/edu/escuelaing/arep/`**: Código fuente de Java.
- **`target/classes/`**: Archivos compilados y archivos estáticos (HTML, CSS, JS, imágenes).


## Pruebas

### Pruebas de Archivos

Para probar que el servidor este sirviendo archivos estáticos como .png, .html, .txt, se va a modificar la url con el nombre del archivo junto con su extensión que se quiere buscar, ejemplo:

 `http://localhost:8080/google.png`
 `http://localhost:8080/test.txt`
 `http://localhost:8080/index.html`
 `http://localhost:8080/script.js`
 `http://localhost:8080/styles.css`

También, hay un campo en el formulario de la clase principal donde se escribe el nombre del archivo y se devuelve, las imágenes retornan como texto en binario por el momento.

### Pruebas Unitarias

Las pruebas unitarias se han implementado utilizando JUnit Jupiter. Estas pruebas verifican la funcionalidad de los componentes clave del servidor, asegurando que cada parte del sistema funcione correctamente de forma aislada. A continuación se presentan algunos ejemplos de pruebas implementadas:

#### Prueba de Devolución de Usuarios

- **Descripción:** Devuelve los usuarios que han sido creados mediante una solicitud GET.
- **Clase de Prueba:** `UsuarioHandlerTest`
- **Método de Prueba:**
  ```java
  @Test
    public void testMostrarUsuarios() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UsuarioHandler.mostrarUsuarios(out);

        String response = out.toString("UTF-8");
        assertTrue(response.contains("\"status\":\"success\""));
        assertTrue(response.contains("\"usuarios\":"));
    }


### Diagrama de Componentes

+------------------+
|    HttpServer    |
+------------------+
         |
         | (acepta conexiones)
         |
+------------------+
|  ClientHandler   |
+------------------+
         |
         | (maneja solicitudes)
         |
+------------------+       +-------------------+
|   FileHandler    | <---- |    UsuarioHandler |
+------------------+       +-------------------+
