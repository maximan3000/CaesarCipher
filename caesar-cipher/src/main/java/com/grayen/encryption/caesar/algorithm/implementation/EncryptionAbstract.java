package com.grayen.encryption.caesar.algorithm.implementation;

import com.grayen.encryption.caesar.algorithm.init.EncryptionParameters;
import com.grayen.encryption.caesar.algorithm.Caesar;

import java.util.HashMap;
import java.util.Map;

/**
 * The class contains methods to encode/decode text using encryption table.
 * That table is creating in abstract method. Inherit the class
 * to make your own encryption table.
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
abstract class EncryptionAbstract implements Caesar {
    @Override
    public String encrypt(String sourceText) {
        return encrypt(sourceText, EncryptionParameters.keywordDefault, EncryptionParameters.offsetDefault);
    }

    @Override
    public String encrypt(String sourceText, String keyword) {
        return encrypt(sourceText, keyword, EncryptionParameters.offsetDefault);
    }

    @Override
    public String encrypt(String sourceText, Integer offset) {
        return encrypt(sourceText, EncryptionParameters.keywordDefault, offset);
    }

    @Override
    public String encrypt(String sourceText, String keyword, Integer offset) {
        Map<String, String> encryptionTable = getEncryptionTable(keyword, offset);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    @Override
    public String decrypt(String sourceText) {
        return decrypt(sourceText, EncryptionParameters.keywordDefault, EncryptionParameters.offsetDefault);
    }

    @Override
    public String decrypt(String sourceText, String keyword) {
        return decrypt(sourceText, keyword, EncryptionParameters.offsetDefault);
    }

    @Override
    public String decrypt(String sourceText, Integer offset) {
        Map<String, String> encryptionTable = getEncryptionTable(EncryptionParameters.keywordDefault, offset);
        encryptionTable = invertEncryptionTable(encryptionTable);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    @Override
    public String decrypt(String sourceText, String keyword, Integer offset) {
        Map<String, String> encryptionTable = getEncryptionTable(keyword, offset);
        encryptionTable = invertEncryptionTable(encryptionTable);
        return convertTextWithEncryptionTable(sourceText, encryptionTable);
    }

    /**
     * Invert encryption table to make decryption same with encryption
     * @param encryptionTable Encryption table
     * @return Inverted encryption table - swap key and value in the map
     */
    private Map<String, String> invertEncryptionTable(Map<String, String> encryptionTable) {
        Map<String, String> sourceMap = encryptionTable;
        Map<String, String> invertedMap = new HashMap<>();

        for (Map.Entry<String, String> pair : sourceMap.entrySet()) {
            invertedMap.put(pair.getValue(),pair.getKey());
        }
        return invertedMap;
    }

    /**
     * Convert each letter in the text using See Also method
     * @see EncryptionAbstract#convertCharacterWithEncryptionTable(char, Map)
     * @param sourceText Text need to convert
     * @param encryptionTable Encryption table
     * @return Converted text
     */
    private String convertTextWithEncryptionTable(String sourceText, Map<String, String> encryptionTable) {
        StringBuilder encryptedText = new StringBuilder();
        char[] sourceCharacters = sourceText.toCharArray();

        for (char currentCharacter : sourceCharacters) {
            encryptedText.append(convertCharacterWithEncryptionTable(currentCharacter, encryptionTable));
        }

        return encryptedText.toString();
    }

    /**
     * Convert a letter with the encryption table.
     * If a letter has the same key in the encryption table,
     * the method convert this letter into the value of the key
     * @param character A letter need to be converted
     * @param encryptionTable Encryption table
     * @return Converted letter
     */
    private String convertCharacterWithEncryptionTable(char character, Map<String, String> encryptionTable) {
        String characterToEncode = Character.toString(character);
        if ( encryptionTable.containsKey(characterToEncode) ) {
            String encryptedCharacter = encryptionTable.get(characterToEncode);
            return encryptedCharacter;
        }
        return characterToEncode;
    }

    /**
     * The method creates encryption table that is using for Caesar Cipher.
     * Notes:
     * 1) letters in lower and upper case are different,
     * so you need to create map entries for both types of letters;
     * 2) a letter can contain only 1 char symbol
     * @param keyword Keyword for Caesar Cipher
     * @param offset Offset of the keyword for Caesar Cipher
     * @return Encryption table
     */
    protected abstract Map<String, String> getEncryptionTable(String keyword, Integer offset);
}
