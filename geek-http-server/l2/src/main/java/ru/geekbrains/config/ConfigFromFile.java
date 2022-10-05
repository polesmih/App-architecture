package ru.geekbrains.config;

import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;

import java.io.IOException;
import java.util.Properties;

public class ConfigFromFile implements ServerConfig {

    private final String www;
    private final int port;
    private static final Logger logger = new ConsoleLogger();

    public ConfigFromFile(String fileName) {
        logger.info("Getting config from file");
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.www = prop.getProperty("www.home");
        this.port = Integer.parseInt(prop.getProperty("port"));
    }

    @Override
    public String getWww() {
        return this.www;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
