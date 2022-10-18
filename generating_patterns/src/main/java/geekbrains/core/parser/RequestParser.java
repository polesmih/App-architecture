package geekbrains.core.parser;

import geekbrains.core.domain.HttpRequest;
import java.util.Deque;

public interface RequestParser {
    HttpRequest parse(Deque<String> rawRequest);
}
