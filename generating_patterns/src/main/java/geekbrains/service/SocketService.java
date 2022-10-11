package geekbrains.service;

import java.util.Deque;

public interface SocketService {
    Deque<String> readRequest();
    void writeResponse(String headers);
}
