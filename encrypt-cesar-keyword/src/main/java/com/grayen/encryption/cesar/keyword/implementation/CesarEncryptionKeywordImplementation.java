package com.grayen.encryption.cesar.keyword.implementation;

import com.grayen.encryption.cesar.keyword.parameters.DefaultEncryptionParameters;

import java.util.HashMap;
import java.util.Map;

public class CesarEncryptionKeywordImplementation extends CesarEncryptionAbstract {

    private String alphabet;
    private Integer encryptionTableSize;
    private String[] prepareEncryptionTable;

    @Override
    protected Map<String,String> getEncryptionTable(String keyword, Integer offset) {
        String securedKeyword = secureKeyword(keyword);
        alphabet = DefaultEncryptionParameters.alphabetAscending;
        encryptionTableSize = alphabet.length();
        prepareEncryptionTable = new String[encryptionTableSize];

        fillEncryptionTableWithKeyword(securedKeyword, offset);

        fillEncryptionTableWithAlphabet(securedKeyword, offset);

        return getMapFromPrepareEncryptionTable();
    }

    private String secureKeyword(String keyword) {
        //TODO из keyword нужно исключить все повторяющееся и ненужное
        return keyword.toUpperCase().replaceAll("[^A-Z]","");
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
            encryptionTable.put(DefaultEncryptionParameters.alphabetAscending.substring(i, i+1), prepareEncryptionTable[i]);
        }
        return encryptionTable;
    }
}
