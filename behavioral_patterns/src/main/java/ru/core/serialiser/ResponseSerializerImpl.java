package ru.core.serialiser;

import ru.core.domain.HttpResponse;

public class ResponseSerializerImpl implements ResponseSerializer {

    @Override
    public String serialize(HttpResponse httpResponse) {

        StringBuilder sb = new StringBuilder();

        sb.append("HTTP/1.1 " + httpResponse.getStatusCode() + " "
                + httpResponse.getStatusCode() + "\n");
        httpResponse.getHeaders().forEach(
                (header, value) -> sb.append(header + ": " + value + "\n"));
        sb.append("\n");
        sb.append(httpResponse.getBody());

        return sb.toString();
    }
}
