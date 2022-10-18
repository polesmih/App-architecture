package ru.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

 public class FileService{
    private final String dir;

    public FileService(String dir) {
        this.dir = dir;
    }
    public boolean exists(String fileName) {
        return Files.exists(Path.of(dir, fileName));
    }
    public boolean isDirectory(String fileName) {
        return Files.isDirectory(Path.of(dir, fileName));
    }

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
