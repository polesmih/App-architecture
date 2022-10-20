package ru.core.parser;

import ru.core.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {
    HttpRequest parse(Deque<String> rawRequest);
}
