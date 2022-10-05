package ru.geekbrains.core.serialiser;

import ru.geekbrains.core.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);
}
