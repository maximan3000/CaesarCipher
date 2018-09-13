package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.CesarEncryptionKeyword;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.parameters.DefaultEncryptionParameters;

import java.util.HashMap;
import java.util.Map;

public abstract class CesarEncryptionAbstract implements CesarEncryptionHack {

    @Override
    public String hack(String encryptedText) {
        Map<String, String> encryptionTable = getHackedEncryptionTable(encryptedText);
        return convertTextWithEncryptionTable(encryptedText, encryptionTable);
    }

    private String convertTextWithEncryptionTable(String sourceText, Map<String, String> encryptionTable) {
        char[] sourceCharacters = sourceText.toCharArray();
        StringBuilder encryptedText = new StringBuilder();

        for (char currentCharacter : sourceCharacters) {
            encryptedText.append(convertCharacterWithEncryptionTable(currentCharacter, encryptionTable));
        }

        return encryptedText.toString();
    }

    private String convertCharacterWithEncryptionTable(char character, Map<String, String> encryptionTable) {
        String characterToEncode = Character.toString(character);
        String encryptedCharacter = (String) encryptionTable.get(characterToEncode);
        encryptedCharacter = encryptedCharacter == null ? " " : encryptedCharacter;
        return encryptedCharacter;
    }

 protected abstract Map<String, String> getHackedEncryptionTable(String encryptedText);
}
