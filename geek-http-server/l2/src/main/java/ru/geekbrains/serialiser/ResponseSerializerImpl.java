package ru.geekbrains.serialiser;

import ru.geekbrains.service.SocketService;
import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializerImpl implements ResponseSerializer {

    private SocketService socketService;

    @Override
    public String serialize(HttpResponse httpResponse) {

        StringBuilder responseText = new StringBuilder(String.format("HTTP/1.1 %s %s\n",
                httpResponse.getStatusCode()
        ));

        httpResponse.getHeaders().forEach(
                (title, body) -> responseText
                        .append(title)
                        .append(": ")
                        .append(body)
                        .append("\n\n")
        );
        responseText.append(httpResponse.getBody());
        return responseText.toString();
    }

    private String getStatusName(int statusCode) {
        String result;
        switch (statusCode) {
            case 404: result = "NOT_FOUND";
                  break;
            case 200: result = "OK";
                break;
            case 400: result = "BAD_REQUEST";
                break;
            default: result = null;
        };
        return result;
    }
}
