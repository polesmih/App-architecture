package ru.core.serialiser;


import ru.core.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);
}
