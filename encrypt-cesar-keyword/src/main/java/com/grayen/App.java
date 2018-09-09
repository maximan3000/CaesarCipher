package com.grayen;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Application loaded from: " + new File("").getAbsolutePath());

        String sourcePath = args[0];
        File sourceFile = new File(sourcePath);
        System.out.println(sourceFile.exists());

        InputStream stream = new FileInputStream(sourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        Stream<String> text =  reader.lines();

        String sourceText = text
                .map( x -> x = x.toUpperCase())
                .map( x -> x = x.replaceAll("[^A-Z ]", ""))
                .collect(Collectors.joining());
        System.out.println( sourceText );

    }
}
