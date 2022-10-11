package geekbrains.service;


import geekbrains.service.FileServiceImpl;

public class FileServiceFactory {
    public static FileServiceImpl create(String config){
        return new FileServiceImpl(config);
    }
}
