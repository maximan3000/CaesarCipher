package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.util.HackUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class HackAbstract implements CesarEncryptionHack {
    protected Map<String, String> encryptionTable;
    protected String encryptedText;

    @Override
    public String hack() {
        return decryptTextWithEncryptionTable(this.encryptedText);
    }

    @Override
    public void correctEncryptionTableWithHand(String fromSymbol, String toSymbol) {
        this.encryptionTable = replaceSymbolInEncryptionTable(this.encryptionTable, fromSymbol, toSymbol);
    }

    public HackAbstract(String encryptedText) {
        this.encryptedText = encryptedText;
        this.encryptionTable = getHackedEncryptionTable(encryptedText);
        setEncryptionTableCaseInsensitive();
    }

    protected void setEncryptionTableCaseInsensitive() {
        Map<String, String> lowerCaseTable = new HashMap<>();

        for (Map.Entry<String, String> entry : this.encryptionTable.entrySet()) {
            lowerCaseTable.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }

        this.encryptionTable.putAll(lowerCaseTable);
    }

    protected Map<String, String> replaceSymbolInEncryptionTable(Map<String, String> encryptionTable, String fromSymbol, String toSymbol) {
        fromSymbol = fromSymbol.toLowerCase();
        toSymbol = toSymbol.toLowerCase();

        String keyToSymbol = HackUtils.getKeyByValue(encryptionTable, toSymbol);
        String keyFrom = HackUtils.getKeyByValue(encryptionTable, fromSymbol);

        encryptionTable.replace(keyFrom, toSymbol);
        encryptionTable.replace(keyToSymbol, fromSymbol);
        encryptionTable.replace(keyFrom.toUpperCase(), toSymbol.toUpperCase());
        encryptionTable.replace(keyToSymbol.toUpperCase(), fromSymbol.toUpperCase());

        return encryptionTable;
    }

    protected String decryptTextWithEncryptionTable(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();
        char[] sourceCharacters = encryptedText.toCharArray();

        for (char currentCharacter : sourceCharacters) {
            decryptedText.append(decryptCharacterWithEncryptionTable(currentCharacter));
        }

        return decryptedText.toString();
    }

    private String decryptCharacterWithEncryptionTable(char character) {
        String characterToEncode = Character.toString(character);

        if (this.encryptionTable.containsKey(characterToEncode)) {
            String encryptedCharacter = this.encryptionTable.get(characterToEncode);
            return encryptedCharacter;
        }

        return characterToEncode;
    }

    protected abstract Map<String, String> getHackedEncryptionTable(String encryptedText);
}