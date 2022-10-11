package geekbrains.service;

public interface FileService {

    boolean exists(String fileName);
    boolean isDirectory(String fileName);
    String readFile(String fileName);
}
