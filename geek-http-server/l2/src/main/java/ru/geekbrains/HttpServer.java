package ru.geekbrains;

import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.config.ServerConfigFactory;
import ru.geekbrains.core.RequestHandler;
import ru.geekbrains.factories.FileServiceFactory;
import ru.geekbrains.factories.SocketServiceFactory;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;
import ru.geekbrains.core.parser.RequestParserImpl;
import ru.geekbrains.core.serialiser.ResponseSerializerImpl;

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

                new Thread(new RequestHandler(
                        SocketServiceFactory.create(socket),
                        FileServiceFactory.create(config.getWww()),
                        new RequestParserImpl(),
                        new ResponseSerializerImpl()

                )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
