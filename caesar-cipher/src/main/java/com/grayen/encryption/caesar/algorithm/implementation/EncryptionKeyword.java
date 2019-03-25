package com.grayen.encryption.caesar.algorithm.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collector;
import com.grayen.encryption.caesar.algorithm.init.EncryptionParameters;

/**
 * The class is using to create encryption table for Caesar Cipher
 * and to encode/decode texts throw {@link EncryptionAbstract} methods
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
class EncryptionKeyword extends EncryptionAbstract {
    /**
     * Letters used in encryption table
     */
    private List<String> alphabet;
    private Integer encryptionTableSize;
    private String[] prepareEncryptionTable;

    @Override
    protected Map<String,String> getEncryptionTable(String keyword, Integer offset) {
        String securedKeyword = secureKeyword(keyword);
        alphabet = new LinkedList<>(EncryptionParameters.alphabetAscending);
        encryptionTableSize = alphabet.size();
        prepareEncryptionTable = new String[encryptionTableSize];

        fillEncryptionTableWithKeyword(securedKeyword, offset);

        fillEncryptionTableWithAlphabet(securedKeyword, offset);

        Map<String,String> encryptionTable = getMapFromPrepareEncryptionTable();

        setEncryptionTableCaseInsensitive(encryptionTable);

        return encryptionTable;
    }

    /**
     * Take all unique letters from keyword
     * @param keyword Keyword
     * @return Keyword with removed unused symbols and repeated letters
     */
    private String secureKeyword(String keyword) {
        String distinctCharactersKeyword = keyword
                .codePoints()
                .distinct()
                .boxed()
                .collect(Collector.of(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append, StringBuilder::toString));

        return distinctCharactersKeyword.toUpperCase().replaceAll("[^A-Z]","");
    }

    /**
     * Fill encryption table with unique keyword letters
     * @param keyword Keyword
     * @param offset Offset of the keyword
     */
    private void fillEncryptionTableWithKeyword(String keyword, Integer offset) {
        for (Integer index = 0; index < keyword.length(); index++ ) {
            String encryptedCharacter = keyword.substring(index, index+1);
            prepareEncryptionTable[offset+index] = encryptedCharacter;
            alphabet.remove(encryptedCharacter);
        }
    }

    /**
     * Fill encryption table with letters that keyword doesn't contain
     * @param keyword Keyword
     * @param offset Offset
     */
    private void fillEncryptionTableWithAlphabet(String keyword, Integer offset) {
        Integer index = offset + keyword.length();
        Integer alphabetIndex = 0;
        while (index != offset) {
            prepareEncryptionTable[index] = alphabet.get(alphabetIndex);
            alphabetIndex++;
            index = ++index < encryptionTableSize ? index : 0;
        }
    }

    /**
     * Create encryption table from the prepared array of letters
     * @return Encryption table
     */
    private Map<String,String> getMapFromPrepareEncryptionTable() {
        Map<String,String> encryptionTable = new HashMap<>();
        for(Integer i = 0; i < encryptionTableSize; i++) {
            encryptionTable.put(EncryptionParameters.alphabetAscending.get(i), prepareEncryptionTable[i]);
        }
        return encryptionTable;
    }

    /**
     * Make encryption table case insensitive. So the method
     * creates lower case duplicate for each upper case letter
     * @param encryptionTable Encryption table
     */
    private void setEncryptionTableCaseInsensitive(Map<String,String> encryptionTable) {
        Map<String,String> lowerCaseTable = new HashMap<>();
        for (Map.Entry<String,String> entry : encryptionTable.entrySet()) {
            lowerCaseTable.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }
        encryptionTable.putAll(lowerCaseTable);
    }
}
