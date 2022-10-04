package ru.geekbrains;

import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static ru.geekbrains.Const.*;

public class HttpServer {

    private static final Logger logger = new ConsoleLogger();
    private static FileService fileService;

    public static void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info(STARTED);

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info(CONNECTED);

                new Thread(new RequestHandler(new SocketService(socket), fileService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
