package ru.geekbrains.config;

import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;

public class ConfigFromCli implements ServerConfig{

    private final String www;
    private final int port;
    private static final Logger logger = new ConsoleLogger();

    public ConfigFromCli(String[] args) {
        logger.info("Getting config from line parameters");
        if (args.length < 2) {
            throw new IllegalStateException("Not enough parameter specified");
        }
        this.www = args[0];
        this.port = Integer.parseInt(args[1]);
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
