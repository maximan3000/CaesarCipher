package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.util.HackUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HackAbstract implements CesarEncryptionHack {
    protected Map<String, String> encryptionTable;
    protected String encryptedText;
    protected List<String> actualCharacters;

    @Override
    public String hack() {
        return decryptTextWithEncryptionTable(this.encryptedText);
    }

    @Override
    public void correctEncryptionTableWithHand(String fromSymbol, String toSymbol) {
        this.encryptionTable = replaceSymbolInEncryptionTable(this.encryptionTable, fromSymbol, toSymbol, Boolean.FALSE);

        if (!isCharacterActual(fromSymbol)) {
            String keyToValueAfter = HackUtils.getKeyByValue(encryptionTable, toSymbol);
            keyToValueAfter = keyToValueAfter.toLowerCase();
            this.actualCharacters.add(keyToValueAfter);
        }
    }

    protected Boolean isCharacterActual(String character) {
        String lowCharacter = character.toLowerCase();
        if (actualCharacters.contains(lowCharacter))
            return true;
        return false;
    }

    public HackAbstract(String encryptedText) {
        this.actualCharacters = new ArrayList<>();
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

    protected Map<String, String> replaceSymbolInEncryptionTable(Map<String, String> encryptionTable, String valueBefore, String valueAfter) {
        return replaceSymbolInEncryptionTable(encryptionTable, valueBefore, valueAfter, Boolean.TRUE);
    }

    private Map<String, String> replaceSymbolInEncryptionTable(Map<String, String> encryptionTable, String valueBefore, String valueAfter, Boolean isSafe) {
        String keyToValueBefore = HackUtils.getKeyByValue(encryptionTable, valueBefore);
        String keyToValueAfter = HackUtils.getKeyByValue(encryptionTable, valueAfter);

        if (isSafe)
            if (isCharacterActual(keyToValueBefore) || isCharacterActual(keyToValueAfter))
                return encryptionTable;
            else
                return unsafeReplaceSymbolInEncryptionTable(encryptionTable, valueBefore, valueAfter);
        else
            return unsafeReplaceSymbolInEncryptionTable(encryptionTable, valueBefore, valueAfter);
    }

    private Map<String, String> unsafeReplaceSymbolInEncryptionTable(Map<String, String> encryptionTable, String valueBefore, String valueAfter) {
        valueBefore = valueBefore.toLowerCase();
        valueAfter = valueAfter.toLowerCase();

        String keyToValueBefore = HackUtils.getKeyByValue(encryptionTable, valueBefore);
        String keyToValueAfter = HackUtils.getKeyByValue(encryptionTable, valueAfter);

        encryptionTable.replace(keyToValueBefore, valueAfter);
        encryptionTable.replace(keyToValueAfter, valueBefore);
        encryptionTable.replace(keyToValueBefore.toUpperCase(), valueAfter.toUpperCase());
        encryptionTable.replace(keyToValueAfter.toUpperCase(), valueBefore.toUpperCase());

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
        String characterToDecode = Character.toString(character);

        if (this.encryptionTable.containsKey(characterToDecode)) {
            String encryptedCharacter = this.encryptionTable.get(characterToDecode);
            return encryptedCharacter;
        }

        return characterToDecode;
    }

    protected abstract Map<String, String> getHackedEncryptionTable(String encryptedText);
}