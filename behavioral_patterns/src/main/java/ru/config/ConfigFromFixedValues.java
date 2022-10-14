package ru.config;

public class ConfigFromFixedValues implements ServerConfig{

    @Override
    public String getWww() {
        return "C:\\Олеся\\Программирование\\GeekBrains - Java\\App-architecture\\app-architecture\\www";
    }

    @Override
    public int getPort() {
        return 8080;
    }
}
