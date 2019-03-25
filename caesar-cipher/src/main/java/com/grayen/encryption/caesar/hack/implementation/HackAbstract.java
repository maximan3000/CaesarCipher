package com.grayen.encryption.caesar.hack.implementation;

import com.grayen.encryption.caesar.hack.Hack;
import com.grayen.encryption.caesar.hack.util.HackUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements decryption of Caesar Cipher with ready hacked encryption table and
 * correction this table with hands.
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
abstract class HackAbstract implements Hack {
    /**
     * Hacked encryption table
     */
    protected Map<String, String> encryptionTable;
    /**
     * Text need to hack
     */
    protected String encryptedText;
    /**
     * Letters that are have been corrected with hands
     */
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

    /**
     * Check if the letter have been corrected with hands
     * @param character Letter need to check
     * @return True if have been
     */
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

    /**
     * Set encryption table case insensitive
     */
    protected void setEncryptionTableCaseInsensitive() {
        Map<String, String> lowerCaseTable = new HashMap<>();

        for (Map.Entry<String, String> entry : this.encryptionTable.entrySet()) {
            lowerCaseTable.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }

        this.encryptionTable.putAll(lowerCaseTable);
    }

    /**
     * @see HackAbstract#replaceSymbolInEncryptionTable(Map, String, String, Boolean)
     */
    protected Map<String, String> replaceSymbolInEncryptionTable(Map<String, String> encryptionTable, String valueBefore, String valueAfter) {
        return replaceSymbolInEncryptionTable(encryptionTable, valueBefore, valueAfter, Boolean.TRUE);
    }

    /**
     * @see HackAbstract#replaceSymbolInEncryptionTable(Map, String, String, Boolean)
     * @param isSafe If True then if the letter has been corrected with hands - do nothing
     */
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

    /**
     * Replace one letter to another in the encryption table.
     * Repeat it for lower case letters.
     * Method search key that refers to "valueBefore" and key to "valueAfter" and swap values of the keys
     * @param encryptionTable Hacked encryption table
     * @param valueBefore Letter need to replace
     * @param valueAfter Replacement letter
     * @return Changed hacked encryption table
     */
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

    /**
     * Decrypt encrypted text with hacked encryption table
     * @see HackAbstract#decryptCharacterWithEncryptionTable(char)
     * @param encryptedText Encrypted text
     * @return Hacked (decrypted) text
     */
    protected String decryptTextWithEncryptionTable(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();
        char[] sourceCharacters = encryptedText.toCharArray();

        for (char currentCharacter : sourceCharacters) {
            decryptedText.append(decryptCharacterWithEncryptionTable(currentCharacter));
        }

        return decryptedText.toString();
    }

    /**
     * Decrypt encrypted letter with hacked encryption table
     * @param character Encrypted letter
     * @return Hacked (decrypted) letter
     */
    private String decryptCharacterWithEncryptionTable(char character) {
        String characterToDecode = Character.toString(character);

        if (this.encryptionTable.containsKey(characterToDecode)) {
            String encryptedCharacter = this.encryptionTable.get(characterToDecode);
            return encryptedCharacter;
        }

        return characterToDecode;
    }

    /**
     * The method hacks encryption table that is using for Caesar Cipher.
     * For this purpose using next methods:
     * 1) letter frequency in english language
     * 2) dictionary of english words
     * Notes:
     * 1) letters in lower and upper case are different,
     * so you need to create map entries for both types of letters;
     * 2) a letter can contain only 1 char symbol
     * @param encryptedText Encrypted text
     * @return Hacked encryption table
     */
    protected abstract Map<String, String> getHackedEncryptionTable(String encryptedText);
}