package ru.service;

import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.parser.RequestParserImpl;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.handler.MethodHandler;
import ru.logger.ConsoleLogger;
import ru.logger.Logger;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private static final Logger logger = new ConsoleLogger();
    private final SocketService socketService;
    private final RequestParserImpl requestParser;
    private final MethodHandler methodHandler;

    public RequestHandler(SocketService socketService,
                          RequestParserImpl requestParser,
                          MethodHandler methodHandler) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = methodHandler;
    }



    @Override
    public void run() {
        Deque<String> request = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(request);

        methodHandler.handle(httpRequest);

        try {
            socketService.close();
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }
        logger.info("Client disconnected!");
    }

}
