package ru.handler;

import ru.config.ServerConfig;
import ru.core.domain.HttpRequest;
import ru.core.domain.HttpResponse;
import ru.core.serialiser.ResponseSerializerImpl;
import ru.service.SocketService;

import java.util.function.Function;

public class StrategyMethodHandler extends MethodHandlerImpl{
    private final Function<HttpRequest, HttpResponse> strategy;

    protected StrategyMethodHandler(String method, MethodHandlerImpl next, SocketService socketService,
                                    ResponseSerializerImpl responseSerializer, ServerConfig config,
                                    Function<HttpRequest, HttpResponse> strategy) {
        super(method, next, socketService, responseSerializer, config);

        this.strategy = strategy;
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest request) {
        return strategy.apply(request);
    }
}
