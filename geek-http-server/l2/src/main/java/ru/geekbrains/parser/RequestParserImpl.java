package ru.geekbrains.parser;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParserImpl implements RequestParser {
    HttpRequest httpRequest = new HttpRequest();

    @Override
    public HttpRequest parse(Deque<String> rawRequest) {

        String[] requestRaw = rawRequest.pollFirst().split(" ");
        httpRequest.setMethod(requestRaw[0]);
        httpRequest.setPath(requestRaw[1]);

        Map<String, String> headers = new HashMap<>();
        while (true) {
            if (rawRequest.isEmpty() && rawRequest.getFirst().contains(":")) {
                requestRaw = rawRequest.pollFirst().split(":",2);
                headers.put(requestRaw[0],requestRaw[1]);
            }
            else break;
        }
        httpRequest.setBody(rawRequest.toString());
        return httpRequest;

    }
}