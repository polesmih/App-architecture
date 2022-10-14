package ru.handler;

import ru.config.ServerConfig;
import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.FileService;
import ru.service.SocketService;

@Handler(method = "GET", order = 0)
class GetMethodHandler extends MethodHandlerImpl {

    private final FileService fileService;

    GetMethodHandler(MethodHandlerImpl next, SocketService socketService,
                     ResponseSerializerImpl responseSerializer, ServerConfig config,
                     FileService fileService) {
        super("GET", next, socketService, responseSerializer, config);
        this.fileService = fileService;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        if (!fileService.exists(request.getUrl())) {
            return HttpResponse.createBuilder()
                    .withStatusCode(404)
                    .withStatusCodeName("NOT_FOUND")
                    .withHeaders("Content-Type", "text/html; charset=utf-8")
                    .build();
        }
        return HttpResponse.createBuilder()
                .withStatusCode(200)
                .withStatusCodeName("OK")
                .withHeaders("Content-Type", "text/html; charset=utf-8")
                .withBody(fileService.readFile(request.getUrl()))
                .build();
    }
}
