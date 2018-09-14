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
        String securedText = secureText(sourceText);

        StringBuilder encryptedText = new StringBuilder();
        char[] sourceCharacters = securedText.toCharArray();

        for (char currentCharacter : sourceCharacters) {
            encryptedText.append(convertCharacterWithEncryptionTable(currentCharacter, encryptionTable));
        }

        return encryptedText.toString();
    }

    private String secureText(String text) {
        return text;
    }

    private String convertCharacterWithEncryptionTable(char character, Map<String, String> encryptionTable) {
        String characterToEncode = Character.toString(character);
        if ( encryptionTable.containsKey(characterToEncode) ) {
            String encryptedCharacter = encryptionTable.get(characterToEncode);
            return encryptedCharacter;
        }
        return characterToEncode;
    }

    protected abstract Map<String, String> getHackedEncryptionTable(String encryptedText);
}
