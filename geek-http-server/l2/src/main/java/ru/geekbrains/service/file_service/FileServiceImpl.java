package ru.geekbrains.service.file_service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService{
    private final String dir;

    public FileServiceImpl(String dir) {
        this.dir = dir;
    }
    @Override
    public boolean exists(String fileName) {
        return Files.exists(Path.of(dir, fileName));
    }
    @Override
    public boolean isDirectory(String fileName) {
        return Files.isDirectory(Path.of(dir, fileName));
    }

    @Override
    public String readFile(String fileName) {
        try {
            StringBuilder sb = new StringBuilder();
            Files.readAllLines(Path.of(dir, fileName)).forEach(sb::append);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
