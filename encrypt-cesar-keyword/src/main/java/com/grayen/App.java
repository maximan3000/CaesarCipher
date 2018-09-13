package com.grayen;

import com.grayen.encryption.cesar.keyword.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.CesarEncryptionKeywordFabric;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHackFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Application loaded from: " + new File("").getAbsolutePath());

        CesarEncryptionKeyword encryption = CesarEncryptionKeywordFabric.getEncryptionSystem();
        CesarEncryptionHack hackEncryption = CesarEncryptionHackFactory.getCesarEncryptionHack();

        File directory = new File("src/main/resources/source");

        File[] listOfFiles = directory.listFiles();

        for (File file : listOfFiles) {
            String content = readFromFile(file.getAbsolutePath());
            File encrypted = new File("src/main/resources/encrypted/"+file.getName());
            File decrypted = new File("src/main/resources/encrypted/decrypted-"+file.getName() );
            File hacked = new File("src/main/resources/hacked/"+file.getName() );
            encrypted.createNewFile();
            decrypted.createNewFile();
            hacked.createNewFile();

            String encryptedString = encryption.encrypt( content );
            try (BufferedWriter writer = new BufferedWriter( new FileWriter( encrypted ) )) {
                writer.write( encryptedString );
            }
            try (BufferedWriter writer = new BufferedWriter( new FileWriter( decrypted ) )) {
                String decryptedString = encryption.decrypt( encryptedString );
                writer.write( decryptedString );
            }
            try (BufferedWriter writer = new BufferedWriter( new FileWriter( hacked ) )) {
                String decryptedString = hackEncryption.hack( encryptedString );
                writer.write( decryptedString );
            }
        }
    }

    private static String readFromFile(String filePath) {
        File sourceFile = new File(filePath);
        InputStream stream = null;

        try {
            stream = new FileInputStream(sourceFile);
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filePath);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        Stream<String> text =  reader.lines();

        String sourceText = text
                .map( x -> x = x.toUpperCase())
                .map( x -> x = x.replaceAll("[^A-Z ]", ""))
                .collect(Collectors.joining());

        return sourceText;
    }
}
