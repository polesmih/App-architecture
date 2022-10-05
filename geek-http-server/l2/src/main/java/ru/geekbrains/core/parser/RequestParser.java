package ru.geekbrains.core.parser;

import ru.geekbrains.core.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {
    HttpRequest parse(Deque<String> rawRequest);
}
