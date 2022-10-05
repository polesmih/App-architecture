package ru.geekbrains.factories;

import ru.geekbrains.service.file_service.FileServiceImpl;

public class FileServiceFactory {
    public static FileServiceImpl create(String config){
        return new FileServiceImpl(config);
    }
}
