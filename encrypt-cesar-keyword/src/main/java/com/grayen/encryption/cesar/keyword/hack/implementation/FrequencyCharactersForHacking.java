package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.init.HackParameters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCharactersForHacking {
    private static List<String> charactersWithFrequencyDescending = HackParameters.charactersWithFrequencyDescending;
    private static String encryptionAlphabet = HackParameters.encryptionAlphabet;

    public static Map<String, String> getHackedEncryptionTable(String encryptedText) {
        return new FrequencyCharactersForHacking().getEncryptionTableDependOnFrequencyCharacters(encryptedText);
    }

    private Map<String, String> getEncryptionTableDependOnFrequencyCharacters(String text) {
        char[] characters = text.toCharArray();

        Map<String, Integer> countCharacterTable = parseCharactersArrayToMapWithCharactersCount(characters);
        Map<String, Float> frequencyCharacterTable = getFrequencyFromCount(countCharacterTable);
        Map<String, String> hackedEncryptionTable = getEncryptionTableFromFrequencyTable(frequencyCharacterTable);

        return hackedEncryptionTable;
    }

    private Map<String, Integer> parseCharactersArrayToMapWithCharactersCount(char[] characters) {
        Map<String, Integer> countCharacterTable = new HashMap();

        for (char character : characters) {
            String characterSymbol = String.valueOf(character).toUpperCase();

            if ( !encryptionAlphabet.contains(characterSymbol) )
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
        List<Map.Entry<String, Float>> encryptedCharactersWithFrequencyDescending = getCharactersSortedByFrequency(frequencyTable);
        Map<String, String> hackedEncryptionTable = new HashMap<>();

        LinkedList<String> charactersThatWereNotInFrequencyTable = new LinkedList<>(charactersWithFrequencyDescending);

        Integer index = 0;
        for (; index < encryptedCharactersWithFrequencyDescending.size(); index++ ) {
            String fromCharacter = encryptedCharactersWithFrequencyDescending.get(index).getKey();
            String toCharacter = charactersWithFrequencyDescending.get(index);

            hackedEncryptionTable.put(fromCharacter, toCharacter);

            charactersThatWereNotInFrequencyTable.removeFirstOccurrence(fromCharacter);
        }

        for (; index < charactersWithFrequencyDescending.size(); index++ ) {
            String fromCharacter = charactersThatWereNotInFrequencyTable.getFirst();
            String toCharacter = charactersWithFrequencyDescending.get(index);

            hackedEncryptionTable.put(fromCharacter, toCharacter);
        }

        return hackedEncryptionTable;
    }

    private List<Map.Entry<String, Float>> getCharactersSortedByFrequency(Map<String, Float> frequencyTable) {
        List<Map.Entry<String, Float>> entriesWithFrequencyDescending =
                frequencyTable
                        .entrySet()
                        .stream()
                        .sorted( (x, y) -> y.getValue().compareTo(x.getValue()) )
                        .collect(Collectors.toList());

        return entriesWithFrequencyDescending;
    }
}
