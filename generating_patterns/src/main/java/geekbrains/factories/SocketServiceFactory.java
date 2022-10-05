package geekbrains.factories;


import geekbrains.service.socket_service.SocketServiceImpl;

import java.net.Socket;

public class SocketServiceFactory {
    public static SocketServiceImpl create(Socket socket) {
        return new SocketServiceImpl(socket);
    }
}
