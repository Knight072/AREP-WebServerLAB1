package edu.escuelaing.arep;

public class RequestParser {
    public static String[] parseRequestLine(String requestLine) {
        return requestLine.split(" ");
    }
}

