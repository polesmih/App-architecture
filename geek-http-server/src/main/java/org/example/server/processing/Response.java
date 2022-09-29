package org.example.server.processing;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.example.server.Const.*;
import static org.example.server.processing.FileGetting.gettingFile;

public class Response {

    public static void response(Socket socket) throws IOException {

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String firstLine = null;

        while (firstLine == null || firstLine.isEmpty())
            firstLine = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
                .readLine();

        String[] parts = firstLine.split(" ");

        Path path = Paths.get(WWW, parts[1]);

        gettingFile(output, path);

    }

}
