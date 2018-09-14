package com.grayen.files.implementations;

import com.grayen.files.WorkWithFiles;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithFilesImplementation implements WorkWithFiles {

    String pathToDirectory;

    public WorkWithFilesImplementation(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    @Override
    public String getFileContent(String fileName) throws Exception {
        File sourceFile = new File(pathToDirectory+fileName);
        InputStream stream = new FileInputStream(sourceFile);

        String fileContent;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
             fileContent =  reader.lines().collect(Collectors.joining("\n"));
        }

        return fileContent;
    }

    @Override
    public void putNewFileContent(String fileName, String fileContent) throws Exception {
        File destinationFile = new File(pathToDirectory+fileName);
        destinationFile.createNewFile();

        try (BufferedWriter writer = new BufferedWriter( new FileWriter( destinationFile ) )) {
            writer.write(fileContent);
        }
    }

    @Override
    public List<String> getListFiles() {
        File directory = new File(pathToDirectory);
        File[] files = directory.listFiles();

        List<String> filesNames = new LinkedList<>();

        for (File file : files) {
            filesNames.add(file.getName());
        }
        return filesNames;
    }
}
