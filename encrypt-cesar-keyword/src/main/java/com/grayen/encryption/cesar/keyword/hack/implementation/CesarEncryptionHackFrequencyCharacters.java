package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.parameters.DefaultEncryptionParameters;

import java.util.*;
import java.util.stream.Collectors;

public class CesarEncryptionHackFrequencyCharacters extends CesarEncryptionAbstract implements CesarEncryptionHack {

    private static List<String> defaultCharactersWithFrequencyDescending = Arrays.asList(
            "E", "T", "A", "O", "I", "N", "S", "H", "R", "D", "L", "C", "U", "M", "W", "F", "G", "Y", "P", "B", "V", "K", "X", "J", "Q", "Z"
    );

    protected Map<String, String> getHackedEncryptionTable(String text) {
        char[] characters = text.toCharArray();

        Map<String, Integer> countCharacterTable = parseCharacterArray(characters);
        Map<String, Float> frequencyCharacterTable = getFrequencyFromCount(countCharacterTable);
        Map<String, String> hackedDecryptionTable = getEncryptionTableFromFrequencyTable(frequencyCharacterTable);
        return hackedDecryptionTable;
    }

    private Map<String, Integer> parseCharacterArray(char[] characters) {
        Map<String, Integer> countCharacterTable = new HashMap();

        for (char character : characters) {
            String characterSymbol = String.valueOf(character);

            if ( !DefaultEncryptionParameters.alphabetAscending.contains(characterSymbol) )
                continue;

            if ( !countCharacterTable.containsKey(characterSymbol)) {
                countCharacterTable.put(characterSymbol, 1);
            } else {
                Integer charactersCount = countCharacterTable.get(characterSymbol);
                countCharacterTable.put(characterSymbol, charactersCount + 1);
            }
        }

        return countCharacterTable;
    }

    private Map<String, Float> getFrequencyFromCount(Map<String, Integer> countCharacterTable) {
        Map<String, Float> frequencyTable = new HashMap<>();

        Integer summaryCharactersCount = 0;

        for (Integer charactersCount : countCharacterTable.values()) {
            summaryCharactersCount += charactersCount;
        }

        for (String key : countCharacterTable.keySet()) {
            Float charactersCount = countCharacterTable.get(key).floatValue();
            Float frequency =  charactersCount / summaryCharactersCount;
            frequencyTable.put(key, frequency);
        }

        return frequencyTable;
    }

    private Map<String, String> getEncryptionTableFromFrequencyTable(Map<String, Float> frequencyTable) {
        List<Map.Entry<String, Float>> entriesWithFrequencyDescending = frequencyTable.entrySet().stream()
                .sorted( (x, y) -> y.getValue().compareTo(x.getValue()) )
                .collect(Collectors.toList());

        Map<String, String> hackedDecryptionTable = new HashMap<>();

        Integer index = 0;
        for (; index < entriesWithFrequencyDescending.size(); index++ ) {
                hackedDecryptionTable.put(
                        entriesWithFrequencyDescending.get(index).getKey(),
                        defaultCharactersWithFrequencyDescending.get(index)
                );
        }
        for (; index < entriesWithFrequencyDescending.size(); index++ ) {
            hackedDecryptionTable.put(
                    defaultCharactersWithFrequencyDescending.get(index),
                    defaultCharactersWithFrequencyDescending.get(index)
            );
        }

        return hackedDecryptionTable;
    }

}
