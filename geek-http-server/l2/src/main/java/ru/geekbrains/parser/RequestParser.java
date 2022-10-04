package ru.geekbrains.parser;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {

    HttpRequest parse(Deque<String> rawRequest);
}
