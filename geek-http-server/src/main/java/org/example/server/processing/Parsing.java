package org.example.server.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Parsing {

    public static void parsing(Socket socket) throws IOException {
    BufferedReader input = new BufferedReader(
            new InputStreamReader(
                    socket.getInputStream(), StandardCharsets.UTF_8));

        while (!input.ready());
        String firstLine = input.readLine();
        System.out.println(firstLine);
        while (input.ready()) {
                System.out.println(input.readLine());
        }

    }

}
