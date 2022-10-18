package ru;

import ru.config.ServerConfig;
import ru.config.ServerConfigFactory;
import ru.core.parser.RequestParserImpl;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.handler.MethodHandlerFactory;
import ru.logger.ConsoleLogger;
import ru.logger.Logger;
import ru.service.FileService;
import ru.service.RequestHandler;
import ru.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class HttpServer {

    private static final Logger logger = new ConsoleLogger();

    public static void start(String[] args) {

        ServerConfig config = ServerConfigFactory.create(args);

        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                logger.info("New client connected!");

                SocketService socketService = new SocketService(socket);

                new Thread(new RequestHandler(
                        socketService,
                        new RequestParserImpl(),
                        MethodHandlerFactory.create(socketService, new ResponseSerializerImpl(),
                                config, new FileService(config.getWww()))
                        )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
