package edu.escuelaing.arep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;

public class UsuarioHandlerTest {

    @Test
    public void testMostrarUsuarios() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UsuarioHandler.mostrarUsuarios(out);

        String response = out.toString("UTF-8");
        assertTrue(response.contains("\"status\":\"success\""));
        assertTrue(response.contains("\"usuarios\":"));
    }

    @Test
    public void testCrearUsuario() throws IOException {
        String jsonRequest = "{\"nombre\":\"Carlos\",\"edad\":29,\"email\":\"carlos@example.com\"}";
        BufferedReader in = new BufferedReader(new StringReader(jsonRequest));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        UsuarioHandler.crearUsuario(out, in);

        String response = out.toString("UTF-8");
        assertTrue(response.contains("\"status\":\"success\""));
        assertTrue(response.contains("\"usuario\":"));
        assertTrue(response.contains("\"nombre\":\"Carlos\""));
    }
}

