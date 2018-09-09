package com.grayen.encryption.cesar.keyword.system.encrypt.implementation;

import com.grayen.encryption.cesar.keyword.system.encrypt.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.system.parameters.DefaultEncryptionParameters;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CesarEncryptionKeywordImplementation implements CesarEncryptionKeyword {

    private static final String alphabetAscending = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String encrypt(String sourceText) {
        return encrypt(sourceText, DefaultEncryptionParameters.keyWord);
    }

    @Override
    public String encrypt(String sourceText, String keyWord) {
        return encrypt(sourceText, keyWord, DefaultEncryptionParameters.offset);
    }

    @Override
    public String encrypt(String sourceText, String keyWord, Integer offset) {
        Map encryptionTable = getEncryptionTable(keyWord, offset);

        char[] sourceCharacters = sourceText.toCharArray();
        StringBuilder encryptedText = new StringBuilder();
        for (Integer i = 0; i < sourceCharacters.length; i++) {
            String character = Character.toString(sourceCharacters[i]);
            String encryptedCharacter = (String) encryptionTable.get(character);
            encryptedCharacter = encryptedCharacter == null ? " " : encryptedCharacter;
            encryptedText.append(encryptedCharacter);
        }

        return encryptedText.toString();
    }

    private Map<String,String> getEncryptionTable(String keyWord, Integer offset) {
        keyWord.toUpperCase();
        //TODO из keyword нужно исключить все повторяющееся и ненужное
        keyWord.replaceAll("[^A-Z]","");

        String alphabet = alphabetAscending;
        Integer encryptionTableSize = alphabetAscending.length();
        String[] prepareEncryptionTable = new String[encryptionTableSize];

        for (Integer index = 0; index < keyWord.length(); index++ ) {
            String encryptedCharacter = keyWord.substring(index, index+1);
            prepareEncryptionTable[offset+index] = encryptedCharacter;
            alphabet = alphabet.replace(encryptedCharacter, "");
        }

        Integer index = offset + keyWord.length();
        Integer alphabetIndex = 0;
        while (index != offset) {
            prepareEncryptionTable[index] = alphabet.substring(alphabetIndex, alphabetIndex+1);
            alphabetIndex++;
            index = ++index < encryptionTableSize ? index : 0;
        }

        Map<String,String> encryptionTable = new HashMap<>();
        for(Integer i = 0; i < encryptionTableSize; i++) {
            encryptionTable.put(alphabetAscending.substring(i,i+1), prepareEncryptionTable[i]);
        }

        return encryptionTable;
    }
}
