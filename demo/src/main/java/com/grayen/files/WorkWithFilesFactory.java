package com.grayen.files;

import com.grayen.files.implementations.WorkWithFilesImplementation;

public class WorkWithFilesFactory {
    public static WorkWithFiles getWorkWithFiles(String pathToDirectory) {
        return new WorkWithFilesImplementation(pathToDirectory);
    }
}
