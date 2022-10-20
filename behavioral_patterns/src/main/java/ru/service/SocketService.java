package ru.service;

import ru.logger.ConsoleLogger;
import ru.logger.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

public class SocketService implements Closeable {
    private static final Logger logger = new ConsoleLogger();
    private final Socket socket;
    public SocketService(Socket socket) {
        this.socket = socket;
    }

    public Deque<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready());

            Deque<String> response = new LinkedList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                response.add(line);
            }
            return response;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(String headers) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(headers);
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
