package edu.escuelaing.arep;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    public static void mostrarArchivo(OutputStream out, String fileName) throws IOException {
        // Definir la ruta del archivo a partir del nombre recibido
        String filePath = "target/classes/public/" + fileName;

        // Crear un Path para el archivo
        Path path = Paths.get(filePath);

        // Verificar si el archivo existe
        if (Files.exists(path)) {
            // Determinar el tipo MIME del archivo
            String contentType = Files.probeContentType(path);

            // Leer los bytes del archivo (sirve para archivos binarios y de texto)
            byte[] fileBytes = Files.readAllBytes(path);

            // Enviar la respuesta con el archivo binario
            sendResponse(out, "200 OK", contentType, fileBytes);
        } else {
            // En caso de que el archivo no exista, devolver un error 404
            sendResponse(out, "404 Not Found", "text/plain", "Archivo no encontrado".getBytes());
        }
    }

    public static void manejarArchivosEstaticos(OutputStream out, String resource) throws IOException {
        // Definir el archivo por defecto como index.html si la solicitud es "/"
        String filePath = "target/classes/public" + resource;
        if (resource.equals("/")) {
            filePath += "index.html";
        }

        // Crear un Path para el archivo
        Path path = Paths.get(filePath);

        // Verificar si el archivo existe
        if (Files.exists(path)) {
            // Determinar el tipo MIME del archivo
            String contentType = Files.probeContentType(path);

            // Leer los bytes del archivo
            byte[] fileBytes = Files.readAllBytes(path);

            // Enviar la respuesta con el archivo
            sendResponse(out, "200 OK", contentType, fileBytes);
        } else {
            // En caso de que el archivo no exista, devolver un error 404
            sendResponse(out, "404 Not Found", "text/plain", "Archivo no encontrado".getBytes());
        }
    }

    private static void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException {
        // Enviar los encabezados HTTP
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 " + status);
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length);
        writer.println();
        writer.flush();

        // Enviar el contenido binario (incluidas imágenes, HTML, etc.)
        out.write(content);
        out.flush();
    }
}


