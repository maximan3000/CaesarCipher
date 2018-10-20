package com.grayen;

import com.grayen.encryption.cesar.keyword.algorithm.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.algorithm.CesarEncryptionKeywordFabric;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHackFactory;
import com.grayen.files.WorkWithFiles;
import com.grayen.files.WorkWithFilesFactory;

import java.io.*;
import java.util.Scanner;

public class App {
    static final String sourceFilesPath = "src/main/resources/source/";
    static final String encryptedFilesPath = "src/main/resources/encrypted/";
    static final String decryptedFilesPath = "src/main/resources/decrypted/";
    static final String hackedFilesPath = "src/main/resources/hacked/";
    static final String sourceOfDictionaryPath = "src/main/resources/dictionarySources/";

    public static void main(String[] args) throws Exception {
        System.out.println("Application loaded from: " + new File("").getAbsolutePath() + "\n");
        System.out.println(
                "Folder with sources files: " + sourceFilesPath + "\n" +
                "Folder with encrypted files: " + encryptedFilesPath + "\n" +
                "Folder with decrypted files: " + decryptedFilesPath + "\n" +
                "Folder with hacked files: " + hackedFilesPath + "\n"
        );

        Scanner scanner = new Scanner(System.in);

        WorkWithFiles sourceFiles = WorkWithFilesFactory.getWorkWithFiles(sourceFilesPath);
        WorkWithFiles encryptedFiles = WorkWithFilesFactory.getWorkWithFiles(encryptedFilesPath);
        WorkWithFiles decryptedFiles = WorkWithFilesFactory.getWorkWithFiles(decryptedFilesPath);
        WorkWithFiles hackedFiles = WorkWithFilesFactory.getWorkWithFiles(hackedFilesPath);

        WorkWithFiles sourceOfDictionaryFiles = WorkWithFilesFactory.getWorkWithFiles(sourceOfDictionaryPath);
        String[] sourceOfDictionary = sourceOfDictionaryFiles.getFileLines("WarAndPeace.txt");

        CesarEncryptionKeyword encryption = CesarEncryptionKeywordFabric.getEncryptionSystem();

        System.out.println("Type filename you want to --> ENCRYPT <-- or skip this (just press <Enter> without typing)");
        String needToEncryptFilename = scanner.nextLine();

        if (!needToEncryptFilename.equals("")) {
            String sourceText = sourceFiles.getFileContent(needToEncryptFilename);
            String encryptedText = encryption.encrypt(sourceText);
            encryptedFiles.putNewFileContent(needToEncryptFilename, encryptedText);

            System.out.println("Encrypted text moved to file: " + needToEncryptFilename);
        }

        System.out.println("Type filename you want to --> DECRYPT <-- or skip this (just press <Enter> without typing)");
        String needToDecryptFilename = scanner.nextLine();

        if (!needToDecryptFilename.equals("")) {
            String encryptedText = encryptedFiles.getFileContent(needToDecryptFilename);
            String decryptedText = encryption.decrypt(encryptedText);
            decryptedFiles.putNewFileContent(needToDecryptFilename, decryptedText);

            System.out.println("Decrypted text moved to file: " + needToDecryptFilename);
        }

        System.out.println("Type filename you want to --> HACK <-- or skip this (just press <Enter> without typing)");
        String needToHackFilename = scanner.nextLine();

        if (!needToHackFilename.equals("")) {
            String encryptedText = encryptedFiles.getFileContent(needToHackFilename);
            CesarEncryptionHack hack = CesarEncryptionHackFactory.getCesarEncryptionHack(encryptedText, sourceOfDictionary);

            String hackedText = hack.hack();
            hackedFiles.putNewFileContent(needToHackFilename, hackedText);
            System.out.println("Hacked text moved to file: " + needToHackFilename);

            String fromCharacter;
            String toCharacter;
            do {
                System.out.println("Type what character you want to change (or type 'end' if you want to exit): ");
                fromCharacter = scanner.nextLine();
                if (fromCharacter.equals("end"))
                    break;
                System.out.println("To character: ");
                toCharacter = scanner.nextLine();

                hack.correctEncryptionTableWithHand(fromCharacter, toCharacter);
                hackedText = hack.hack();
                hackedFiles.putNewFileContent(needToHackFilename, hackedText);
                System.out.println("Hacked text moved to file: " + needToHackFilename);
            } while (true);
        }
    }
}
