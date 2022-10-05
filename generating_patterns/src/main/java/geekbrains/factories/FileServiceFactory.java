package geekbrains.factories;


import geekbrains.service.file_service.FileServiceImpl;

public class FileServiceFactory {
    public static FileServiceImpl create(String config){
        return new FileServiceImpl(config);
    }
}
