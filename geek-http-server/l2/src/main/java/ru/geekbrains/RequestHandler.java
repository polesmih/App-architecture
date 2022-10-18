package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;
import ru.geekbrains.parser.RequestParserImpl;
import ru.geekbrains.serialiser.ResponseSerializerImpl;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.Const.DISCONNECTED;

public class RequestHandler implements Runnable {

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    private final FileService fileService;

    public RequestHandler(SocketService socketService, FileService fileService) {
        this.socketService = socketService;
        this.fileService = fileService;
    }

    @Override
    public void run() {
        Deque<String> request = socketService.readRequest();

        // done
        HttpRequest httpRequest = new RequestParserImpl().parse(request);
        HttpResponse httpResponse = new HttpResponse();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=utf-8");

        if (fileService.isDirectory(httpRequest.getPath())) {
            httpResponse = new HttpResponse(400, headers, "<h1>Это директория</h1>");
        }else if (!fileService.exists(httpRequest.getPath())) {
            httpResponse = new HttpResponse(200, headers, "<h1>Файл не найден!</h1>");
        }
        socketService.writeResponse(new ResponseSerializerImpl().serialize(httpResponse));

        try {
            socketService.close();

        }catch (IOException e) {
            throw new IllegalStateException(e);
        }

        logger.info(DISCONNECTED);
    }

}
