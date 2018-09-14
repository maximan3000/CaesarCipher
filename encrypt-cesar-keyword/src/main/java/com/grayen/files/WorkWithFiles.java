package com.grayen.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface WorkWithFiles {
    String getFileContent(String fileName) throws FileNotFoundException, Exception;
    void putNewFileContent(String fileName, String fileContent) throws IOException, Exception;
    List<String> getListFiles();
}
