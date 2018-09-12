package com.grayen.encryption.cesar.keyword.implementation;

import com.grayen.encryption.cesar.keyword.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.parameters.DefaultEncryptionParameters;

import java.util.HashMap;
import java.util.Map;

public abstract class CesarEncryptionAbstract implements CesarEncryptionKeyword {
    @Override
    public String encrypt(String sourceText) {
        return encrypt(sourceText, DefaultEncryptionParameters.keyword);
    }

    @Override
    public String encrypt(String sourceText, String keyword) {
        return encrypt(sourceText, keyword, DefaultEncryptionParameters.offset);
    }

    @Override
    public String encrypt(String sourceText, String keyword, Integer offset) {
        Map encryptionTable = getEncryptionTable(keyword, offset);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    public String decrypt(String sourceText) {
        return decrypt(sourceText, DefaultEncryptionParameters.keyword);
    }

    public String decrypt(String sourceText, String keyword) {
        return decrypt(sourceText, keyword, DefaultEncryptionParameters.offset);
    }

    public String decrypt(String sourceText, String keyword, Integer offset) {
        Map encryptionTable = getEncryptionTable(keyword, offset);
        encryptionTable = invertEncryptionTable(encryptionTable);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    private Map invertEncryptionTable(Map encryptionTable) {
        Map<String, String> sourceMap = encryptionTable;
        Map<String, String> invertedMap = new HashMap<>();

        for (Map.Entry<String, String> pair : sourceMap.entrySet()) {
            invertedMap.put(pair.getValue(),pair.getKey());
        }
        return invertedMap;
    }

    private String convertTextWithEncryptionTable(String sourceText, Map encryptionTable) {
        char[] sourceCharacters = sourceText.toCharArray();
        StringBuilder encryptedText = new StringBuilder();

        for (char currentCharacter : sourceCharacters) {
            encryptedText.append(convertCharacterWithEncryptionTable(currentCharacter, encryptionTable));
        }

        return encryptedText.toString();
    }

    private String convertCharacterWithEncryptionTable(char character, Map encryptionTable) {
        String characterToEncode = Character.toString(character);
        String encryptedCharacter = (String) encryptionTable.get(characterToEncode);
        encryptedCharacter = encryptedCharacter == null ? " " : encryptedCharacter;
        return encryptedCharacter;
    }

    protected abstract Map getEncryptionTable(String keyword, Integer offset);
}
