package com.grayen;

import com.grayen.encryption.cesar.keyword.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.CesarEncryptionKeywordFabric;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHackFactory;
import com.grayen.files.WorkWithFiles;
import com.grayen.files.WorkWithFilesFactory;

import java.io.*;

public class App {
    static final String sourceFilesPath = "src/main/resources/source/";
    static final String encryptedFilesPath = "src/main/resources/encrypted/";
    static final String decryptedFilesPath = "src/main/resources/decrypted/";
    static final String hackedFilesPath = "src/main/resources/hacked/";

    public static void main(String[] args) throws Exception {
        System.out.println("Application loaded from: " + new File("").getAbsolutePath());

        CesarEncryptionKeyword encryption = CesarEncryptionKeywordFabric.getEncryptionSystem();
        CesarEncryptionHack hackEncryption = CesarEncryptionHackFactory.getCesarEncryptionHack();

        WorkWithFiles source = WorkWithFilesFactory.getWorkWithFiles(sourceFilesPath);
        WorkWithFiles encrypted = WorkWithFilesFactory.getWorkWithFiles(encryptedFilesPath);
        WorkWithFiles decrypted = WorkWithFilesFactory.getWorkWithFiles(decryptedFilesPath);
        WorkWithFiles hacked = WorkWithFilesFactory.getWorkWithFiles(hackedFilesPath);

        for (String fileName : source.getListFiles()) {
            String sourceText = source.getFileContent(fileName);
            String encryptedText = encryption.encrypt(sourceText);
            encrypted.putNewFileContent(fileName, encryptedText);
            decrypted.putNewFileContent(fileName, encryption.decrypt(encryptedText));
            hacked.putNewFileContent(fileName, hackEncryption.hack(encryptedText));
        }
    }
}
