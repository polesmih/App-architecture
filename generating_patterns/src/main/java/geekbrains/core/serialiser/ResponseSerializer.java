package geekbrains.core.serialiser;


import geekbrains.core.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);
}
