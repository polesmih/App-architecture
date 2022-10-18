package ru.handler;

import ru.config.ServerConfig;
import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.SocketService;

@Handler(method = "POST", order = 1)
public class PostMethodHandler extends MethodHandlerImpl{


    protected PostMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializerImpl responseSerializer, ServerConfig config) {
        super("POST", next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeaders("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>POST method handled</h1>")
                .build();
    }
}
