package org.example.server.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.example.server.Const.*;

public class Response {

    public static void response(Socket socket) throws IOException {

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String firstLine = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
                .readLine();

        String[] parts = firstLine.split(" ");

        Path path = Paths.get(WWW, parts[1]);
        if (!Files.exists(path)) {
            output.println(HTTP_NOTFOUND);
            output.println(CONTEXT_TYPE);
            output.println();
            output.println(FILE_NOTFOUND);
            output.flush();
            return;
        }

        output.println(HTTP_OK);
        output.println(CONTEXT_TYPE);
        output.println();

        Files.newBufferedReader(path).transferTo(output);
    }

}
