package ru.handler;

import ru.config.ServerConfig;
import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.SocketService;

abstract class MethodHandlerImpl implements MethodHandler{
    private final String method;
    private final MethodHandlerImpl next;
    protected final SocketService socketService;
    protected final ResponseSerializerImpl responseSerializer;
    protected final ServerConfig config;

    protected MethodHandlerImpl(String method, MethodHandlerImpl next, SocketService socketService,
                                ResponseSerializerImpl responseSerializer, ServerConfig config) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
        this.config = config;
    }

    @Override
    public void handle(HttpRequest request) {
        HttpResponse response;
        if (method.equals(request.getMethod())){
            response = handleInternal(request);
        } else if (next != null) {
            next.handle(request);
            return;
        }else {
            response = HttpResponse.createBuilder()
                    .withStatusCode(405)
                    .withStatusCodeName("METHOD_NOT_ALLOWED")
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Метод не поддерживается</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(response);
        socketService.writeResponse(rawResponse);

    }
    protected abstract HttpResponse handleInternal(HttpRequest request);
}
