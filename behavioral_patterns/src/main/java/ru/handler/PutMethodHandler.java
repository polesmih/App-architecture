package ru.handler;

import ru.config.ServerConfig;
import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.SocketService;

@Handler(method = "PUT", order = 2)
public class PutMethodHandler extends MethodHandlerImpl{
    protected PutMethodHandler(MethodHandlerImpl next, SocketService socketService, ResponseSerializerImpl responseSerializer, ServerConfig config) {
        super("PUT", next, socketService, responseSerializer, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeaders("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>PUT method handled</h1>")
                .build();

    }
}
