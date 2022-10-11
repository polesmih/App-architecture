package geekbrains;

import geekbrains.config.ServerConfig;
import geekbrains.config.ServerConfigFactory;
import geekbrains.service.RequestHandler;
import geekbrains.core.parser.RequestParserImpl;
import geekbrains.core.serialiser.ResponseSerializerImpl;
import geekbrains.service.FileServiceFactory;
import geekbrains.service.SocketServiceFactory;
import geekbrains.logger.ConsoleLogger;
import geekbrains.logger.Logger;

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
