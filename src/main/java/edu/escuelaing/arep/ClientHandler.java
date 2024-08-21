package edu.escuelaing.arep;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null) return;

            // Parsear la solicitud
            String[] tokens = RequestParser.parseRequestLine(requestLine);
            String method = tokens[0];
            String resource = tokens[1];

            // Lógica de manejo de solicitudes
            if (method.equals("GET")) {
                if (resource.startsWith("/leerArchivo?nombre=")) {
                    String fileName = resource.split("=")[1];
                    FileHandler.mostrarArchivo(out, fileName);
                } else if (resource.equals("/usuarios")) {
                    UsuarioHandler.mostrarUsuarios(out);
                } else {
                    FileHandler.manejarArchivosEstaticos(out, resource);
                }
            } else if (method.equals("POST") && resource.equals("/crearUsuario")) {
                UsuarioHandler.crearUsuario(out, in);
            } else {
                sendResponse(out, "405 Method Not Allowed", "text/plain", "Método no permitido".getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("HTTP/1.1 " + status);
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length);
        writer.println();
        writer.flush();

        out.write(content);
        out.flush();
    }
}

