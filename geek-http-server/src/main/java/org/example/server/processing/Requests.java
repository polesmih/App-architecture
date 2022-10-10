package org.example.server.processing;

import java.io.IOException;
import java.net.Socket;

import static org.example.server.Const.*;
import static org.example.server.processing.Parsing.parsing;
import static org.example.server.processing.Response.response;

public class Requests {

    public static void handleRequest(Socket socket) throws IOException {

        parsing(socket);

        response(socket);

        System.out.println(DISCONNECTED);
    }

}
