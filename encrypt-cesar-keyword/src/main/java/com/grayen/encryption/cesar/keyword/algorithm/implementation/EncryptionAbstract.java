package com.grayen.encryption.cesar.keyword.algorithm.implementation;

import com.grayen.encryption.cesar.keyword.algorithm.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.algorithm.init.EncryptionParameters;

import java.util.HashMap;
import java.util.Map;

public abstract class EncryptionAbstract implements CesarEncryptionKeyword {
    @Override
    public String encrypt(String sourceText) {
        return encrypt(sourceText, EncryptionParameters.keyword);
    }

    @Override
    public String encrypt(String sourceText, String keyword) {
        return encrypt(sourceText, keyword, EncryptionParameters.offset);
    }

    @Override
    public String encrypt(String sourceText, String keyword, Integer offset) {
        Map<String, String> encryptionTable = getEncryptionTable(keyword, offset);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    public String decrypt(String sourceText) {
        return decrypt(sourceText, EncryptionParameters.keyword);
    }

    public String decrypt(String sourceText, String keyword) {
        return decrypt(sourceText, keyword, EncryptionParameters.offset);
    }

    public String decrypt(String sourceText, String keyword, Integer offset) {
        Map<String, String> encryptionTable = getEncryptionTable(keyword, offset);
        encryptionTable = invertEncryptionTable(encryptionTable);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    private Map<String, String> invertEncryptionTable(Map<String, String> encryptionTable) {
        Map<String, String> sourceMap = encryptionTable;
        Map<String, String> invertedMap = new HashMap<>();

        for (Map.Entry<String, String> pair : sourceMap.entrySet()) {
            invertedMap.put(pair.getValue(),pair.getKey());
        }
        return invertedMap;
    }

    private String convertTextWithEncryptionTable(String sourceText, Map<String, String> encryptionTable) {
        StringBuilder encryptedText = new StringBuilder();
        char[] sourceCharacters = sourceText.toCharArray();

        for (char currentCharacter : sourceCharacters) {
            encryptedText.append(convertCharacterWithEncryptionTable(currentCharacter, encryptionTable));
        }

        return encryptedText.toString();
    }

    private String convertCharacterWithEncryptionTable(char character, Map<String, String> encryptionTable) {
        String characterToEncode = Character.toString(character);
        if ( encryptionTable.containsKey(characterToEncode) ) {
            String encryptedCharacter = encryptionTable.get(characterToEncode);
            return encryptedCharacter;
        }
        return characterToEncode;
    }

    protected abstract Map<String, String> getEncryptionTable(String keyword, Integer offset);
}
