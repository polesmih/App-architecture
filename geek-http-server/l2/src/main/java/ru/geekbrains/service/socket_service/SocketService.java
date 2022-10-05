package ru.geekbrains.service.socket_service;

import java.util.Deque;

public interface SocketService {
    Deque<String> readRequest();
    void writeResponse(String headers);
}
