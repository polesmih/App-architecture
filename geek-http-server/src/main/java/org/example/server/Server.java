package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.example.server.Const.*;
import static org.example.server.processing.Requests.handleRequest;

public class Server {

    public void start() {
        try (
            ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println(STARTED);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(CONNECTED);

                new Thread(() -> {
                    try {
                        handleRequest(socket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
