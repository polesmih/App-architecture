package ru.geekbrains.serialiser;

import ru.geekbrains.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
