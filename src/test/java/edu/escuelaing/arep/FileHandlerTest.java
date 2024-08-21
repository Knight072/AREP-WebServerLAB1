package edu.escuelaing.arep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandlerTest {

    @Test
    public void testMostrarArchivo() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileHandler.mostrarArchivo(out, "test.txt");

        String response = out.toString("UTF-8");
        assertTrue(response.contains("Content-Type: text/plain"));
        assertTrue(response.contains("Content-Length: "));
        assertTrue(Files.exists(Paths.get("target/classes/public/test.txt")));
    }

    @Test
    public void testManejarArchivosEstaticos() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileHandler.manejarArchivosEstaticos(out, "/index.html");

        String response = out.toString("UTF-8");
        assertTrue(response.contains("Content-Type: text/html"));
        assertTrue(response.contains("Content-Length: "));
        assertTrue(Files.exists(Paths.get("target/classes/public/index.html")));
    }
}
