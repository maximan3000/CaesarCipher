package com.grayen.files.implementations;

import com.grayen.files.WorkWithFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class WorkWithFilesImplementation implements WorkWithFiles {
    private String pathToDirectory;

    public WorkWithFilesImplementation(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    @Override
    public String getFileContent(String fileName) throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath(pathToDirectory + fileName);

        String fileContent = Files
                .lines(sourceFile, StandardCharsets.UTF_8)
                .collect(Collectors.joining("\n"));

        return fileContent;
    }

    @Override
    public String[] getFileLines(String fileName) throws IOException {
        Path sourceFile = FileSystems.getDefault().getPath(pathToDirectory + fileName);

        List<String> fileLines = Files.readAllLines(sourceFile, StandardCharsets.UTF_8);
        String[] fileLinesArray = fileLines.toArray(new String[0]);

        return fileLinesArray;
    }

    @Override
    public void putNewFileContent(String fileName, String fileContent) throws Exception {
        Path destinationFile = FileSystems.getDefault().getPath(pathToDirectory + fileName);

        if ( !Files.exists(destinationFile) )
            Files.createFile(destinationFile);

        Files.write(destinationFile, fileContent.getBytes());
    }

    @Override
    public List<String> getListFiles() {
        File directory = new File(pathToDirectory);
        String[] files = directory.list();
        List<String> filesNames = Arrays.asList( files );

        return filesNames;
    }
}
