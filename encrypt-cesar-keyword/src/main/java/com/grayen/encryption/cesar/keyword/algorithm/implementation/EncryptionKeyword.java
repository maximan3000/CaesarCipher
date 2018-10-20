package com.grayen.encryption.cesar.keyword.algorithm.implementation;

import com.grayen.encryption.cesar.keyword.algorithm.init.EncryptionParameters;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class EncryptionKeyword extends EncryptionAbstract {

    private String alphabet;
    private Integer encryptionTableSize;
    private String[] prepareEncryptionTable;

    @Override
    protected Map<String,String> getEncryptionTable(String keyword, Integer offset) {
        String securedKeyword = secureKeyword(keyword);
        alphabet = EncryptionParameters.alphabetAscending;
        encryptionTableSize = alphabet.length();
        prepareEncryptionTable = new String[encryptionTableSize];

        fillEncryptionTableWithKeyword(securedKeyword, offset);

        fillEncryptionTableWithAlphabet(securedKeyword, offset);

        Map<String,String> encryptionTable = getMapFromPrepareEncryptionTable();

        setEncryptionTableCaseInsensitive(encryptionTable);

        return encryptionTable;
    }

    private String secureKeyword(String keyword) {
        String distinctCharactersKeyword = keyword
                .codePoints()
                .distinct()
                .boxed()
                .collect(Collector.of(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append, StringBuilder::toString));

        return distinctCharactersKeyword.toUpperCase().replaceAll("[^A-Z]","");
    }

    private void fillEncryptionTableWithKeyword(String keyword, Integer offset) {
        for (Integer index = 0; index < keyword.length(); index++ ) {
            String encryptedCharacter = keyword.substring(index, index+1);
            prepareEncryptionTable[offset+index] = encryptedCharacter;
            alphabet = alphabet.replace(encryptedCharacter, "");
        }
    }

    private void fillEncryptionTableWithAlphabet(String keyword, Integer offset) {
        Integer index = offset + keyword.length();
        Integer alphabetIndex = 0;
        while (index != offset) {
            prepareEncryptionTable[index] = alphabet.substring(alphabetIndex, alphabetIndex+1);
            alphabetIndex++;
            index = ++index < encryptionTableSize ? index : 0;
        }
    }

    private Map<String,String> getMapFromPrepareEncryptionTable() {
        Map<String,String> encryptionTable = new HashMap<>();
        for(Integer i = 0; i < encryptionTableSize; i++) {
            encryptionTable.put(EncryptionParameters.alphabetAscending.substring(i, i+1), prepareEncryptionTable[i]);
        }
        return encryptionTable;
    }

    private void setEncryptionTableCaseInsensitive(Map<String,String> encryptionTable) {
        Map<String,String> lowerCaseTable = new HashMap<>();
        for (Map.Entry<String,String> entry : encryptionTable.entrySet()) {
            lowerCaseTable.put(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
        }
        encryptionTable.putAll(lowerCaseTable);
    }
}
