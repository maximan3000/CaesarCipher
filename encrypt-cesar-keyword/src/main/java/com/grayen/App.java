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
    static final String sourceFilesPath = "src/main/resources/files/";
    static final String sourceOfDictionaryPath = "src/main/resources/dictionary/";
    static WorkWithFiles sourceFiles;

    static Scanner scanner;
    static WorkWithFiles sourceOfDictionaryFiles;
    static String[] sourceOfDictionary;
    static CesarEncryptionKeyword encryption;

    public static void main(String[] args) throws Exception {
        //System.out.println("Application loaded from: " + new File("").getAbsolutePath() + "\n");
        System.out.println("Root folder is: " + sourceFilesPath);

        scanner = new Scanner(System.in);
        sourceFiles = WorkWithFilesFactory.getWorkWithFiles(sourceFilesPath);

        WorkWithFiles sourceOfDictionaryFiles = WorkWithFilesFactory.getWorkWithFiles(sourceOfDictionaryPath);
        sourceOfDictionary = sourceOfDictionaryFiles.getFileLines("WarAndPeace.txt");

        encryption = CesarEncryptionKeywordFabric.getEncryptionSystem();

        executeHelp();
        while (Boolean.TRUE){
            String command = scanner.nextLine();
            try {
                parseConsoleCommand(command);
            } catch (Exception ex) {
                return ;
            }
        }
    }

    static void parseConsoleCommand(String command) throws Exception {
        command = command.trim();
        String[] commandData = command.split(" ");
        String commandName = commandData[0];

        switch (commandName) {
            case "!help":
                executeHelp();
                break;
            case "!encode":
                executeEncode(commandData[1]);
                break;
            case "!decode":
                executeDecode(commandData[1]);
                break;
            case "!hack":
                executeHack(commandData[1]);
                break;
            case "!exit":
                throw new Exception("Exit program");
            default:
                System.out.println("No such command!");
        }
    }

    private static void executeHelp() {
        System.out.println("!help !encode path_to_file !decode path_to_file !hack path_to_file");
    }

    private static void executeEncode(String srcFile) {
        try {
            String sourceText = sourceFiles.getFileContent(srcFile);
            String encryptedText = encryption.encrypt(sourceText);

            String encryptedFile = srcFile.replace(".txt", "-enc.txt");
            sourceFiles.putNewFileContent(encryptedFile, encryptedText);

            System.out.println("Encrypted text moved to file: " + encryptedFile);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private static void executeDecode(String encryptedFile) {
        try {
            String encryptedText = sourceFiles.getFileContent(encryptedFile);
            String decryptedText = encryption.decrypt(encryptedText);

            String decryptedFile = encryptedFile.replace(".txt", "-dec.txt");
            sourceFiles.putNewFileContent(decryptedFile, decryptedText);

            System.out.println("Decrypted text moved to file: " + decryptedFile);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private static void executeHack(String encryptedFile) {
        try {
            String encryptedText = sourceFiles.getFileContent(encryptedFile);
            CesarEncryptionHack hack = CesarEncryptionHackFactory.getCesarEncryptionHack(encryptedText, sourceOfDictionary);

            String hackedText = hack.hack();
            String hackedFile = encryptedFile.replace(".txt", "-hack.txt");
            sourceFiles.putNewFileContent(hackedFile, hackedText);
            System.out.println("Hacked text moved to file: " + hackedFile);

            handHack(hack, hackedFile);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    static void handHack(CesarEncryptionHack hack, String hackedFile) throws Exception {
        executeHackHelp();
        while (Boolean.TRUE){
            String command = scanner.nextLine();
            try {
                parseHackCommand(command, hack, hackedFile);
            } catch (Exception ex) {
                return ;
            }
        }
    }

    private static void parseHackCommand(String command, CesarEncryptionHack hack, String hackedFile) throws Exception {
        command = command.trim();
        String[] commandData = command.split(" ");
        String commandName = commandData[0];

        switch (commandName) {
            case "!help":
                executeHackHelp();
                break;
            case "!swap":
                executeHackSwap(commandData[1], commandData[2], hack);
                break;
            case "!apply":
                executeHackApply(hack, hackedFile);
                break;
            case "!stop":
                throw new Exception("Exit program");
            default:
                System.out.println("No such command!");
        }
    }

    private static void executeHackHelp() {
        System.out.println("!help !swap from to !apply !stop");
    }

    private static void executeHackSwap(String from, String to, CesarEncryptionHack hack) {
        try {
            hack.correctEncryptionTableWithHand(from, to);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private static void executeHackApply(CesarEncryptionHack hack, String hackedFile) {
        try {
            String hackedText = hack.hack();
            sourceFiles.putNewFileContent(hackedFile, hackedText);
            System.out.println("Hacked text moved to file: " + hackedFile);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
