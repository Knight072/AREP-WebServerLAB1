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
