package com.grayen.files.implementations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class WorkWithFilesImplementationTest {
    static final String directory = "src/test/resources/";
    WorkWithFilesImplementation workWithFiles;

    @Before
    public void setUp() {
        workWithFiles = new WorkWithFilesImplementation(directory);
    }

    @Test
    public void getFileContent() throws IOException {
        String expected = "Hello!\nThis is file for testing Classes that are implements reading from files operation!\n";

        String real = workWithFiles.getFileContent("testInput.txt");

        Assert.assertEquals(expected, real);
    }

    @Test
    public void getFileLines() throws IOException {
        String[] expected = {
                "Hello!",
                "This is file for testing Classes that are implements reading from files operation!",
                ""
        };

        String[] real = workWithFiles.getFileLines("testInput.txt");

        Assert.assertArrayEquals(expected, real);
    }

    @Test
    public void putNewFileContent() throws Exception {
        String fileName = "testOutput.txt";
        String content = "This is output; random value is -> " + Double.valueOf( Math.random() ).toString();
        workWithFiles.putNewFileContent(fileName, content);

        String addedContent = workWithFiles.getFileContent(fileName);

        Assert.assertEquals(content, addedContent);
    }

    @Test
    public void getListFiles() {
        List<String> expectedListFiles = Arrays.asList(
                "encrypted",
                "source",
                "testInput.txt",
                "testOutput.txt"
        );

        List<String> realListFiles = workWithFiles.getListFiles();

        Assert.assertEquals(expectedListFiles, realListFiles);
    }
}