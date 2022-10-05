package geekbrains.service.file_service;

public interface FileService {

    boolean exists(String fileName);
    boolean isDirectory(String fileName);
    String readFile(String fileName);
}
