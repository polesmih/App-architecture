package ru.handler;

import ru.core.domain.HttpRequest;

public interface MethodHandler {
    void handle(HttpRequest request);
}
