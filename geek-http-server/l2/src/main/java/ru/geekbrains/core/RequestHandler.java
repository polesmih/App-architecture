package ru.geekbrains.core;

import ru.geekbrains.core.domain.HttpRequest;
import ru.geekbrains.core.domain.HttpResponse;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;
import ru.geekbrains.core.parser.RequestParserImpl;
import ru.geekbrains.core.serialiser.ResponseSerializerImpl;
import ru.geekbrains.service.file_service.FileServiceImpl;
import ru.geekbrains.service.socket_service.SocketServiceImpl;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private static final Logger logger = new ConsoleLogger();
    private final SocketServiceImpl socketService;
    private final FileServiceImpl fileService;
    private final RequestParserImpl requestParser;
    private final ResponseSerializerImpl responseSerializer;

    public RequestHandler(SocketServiceImpl socketService,
                          FileServiceImpl fileService,
                          RequestParserImpl requestParser,
                          ResponseSerializerImpl responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> request = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(request);

        if(!fileService.exists(httpRequest.getUrl())) {
            HttpResponse response = new HttpResponse();
            response.setStatusCode(404);
            response.setStatusCodeName("NOT_FOUND");
            response.getHeaders().put("Content-Type", "text/html; charset=utf-8");
            socketService.writeResponse(responseSerializer.serialize(response));
        }

        HttpResponse response = new HttpResponse();
        response.setStatusCode(200);
        response.setStatusCodeName("OK");
        response.getHeaders().put("Content-Type", "text/html; charset=utf-8");
        response.setBody(fileService.readFile(httpRequest.getUrl()));
        socketService.writeResponse(responseSerializer.serialize(response));

        try {
            socketService.close();
        }catch (IOException e) {
            throw new IllegalStateException(e);
        }
        logger.info("Client disconnected!");
    }

}
