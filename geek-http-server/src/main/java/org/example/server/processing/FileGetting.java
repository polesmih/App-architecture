package org.example.server.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.example.server.Const.*;

public class FileGetting {

    public static void gettingFile(PrintWriter output, Path path) throws IOException {

        if (!Files.exists(path)) {
            output.println(HTTP_NOTFOUND);
            output.println(CONTEXT_TYPE);
            output.println();
            output.println(FILE_NOTFOUND);
            output.flush();
            return;
        }
        output.println(HTTP_OK);
        output.println(CONTEXT_TYPE);
        output.println();

        Files.newBufferedReader(path).transferTo(output);
    }


}
