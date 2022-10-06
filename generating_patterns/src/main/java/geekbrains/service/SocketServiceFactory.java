package geekbrains.service;


import java.net.Socket;

public class SocketServiceFactory {
    public static SocketServiceImpl create(Socket socket) {
        return new SocketServiceImpl(socket);
    }
}
