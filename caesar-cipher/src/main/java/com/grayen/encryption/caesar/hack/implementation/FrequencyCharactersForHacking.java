package com.grayen.encryption.caesar.hack.implementation;

import com.grayen.encryption.caesar.hack.init.HackParameters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class that builds encryption table depends on frequency of english letters
 * and frequency of letters in the input text
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
class FrequencyCharactersForHacking {
    /**
     * List with letters in frequency descending order
     */
    private static List<String> charactersWithFrequencyDescending = HackParameters.charactersWithFrequencyDescending;
    /**
     * List of encrypted letters
     */
    private static List<String> encryptionAlphabet = new LinkedList<>(HackParameters.encryptionAlphabet);


    public static Map<String, String> getHackedEncryptionTable(String encryptedText) {
        return new FrequencyCharactersForHacking().getEncryptionTableDependOnFrequencyCharacters(encryptedText);
    }

    /**
     * Get hacked encryption table. Table creates with matching the most popular letter in the text
     * with the most popular letter in english and so on for less popular letters
     * @param text Input text
     * @return Encryption table
     */
    private Map<String, String> getEncryptionTableDependOnFrequencyCharacters(String text) {
        char[] characters = text.toCharArray();

        Map<String, Integer> countCharacterTable = parseCharactersArrayToMapWithCharactersCount(characters);
        Map<String, Float> frequencyCharacterTable = getFrequencyFromCount(countCharacterTable);
        Map<String, String> hackedEncryptionTable = getEncryptionTableFromFrequencyTable(frequencyCharacterTable);

        return hackedEncryptionTable;
    }

    /**
     * Method counts letters in the text
     * @param characters Array with all letters in the text
     * @return Map with count of letters in the text
     */
    private Map<String, Integer> parseCharactersArrayToMapWithCharactersCount(char[] characters) {
        Map<String, Integer> countCharacterTable = new HashMap();

        for (char character : characters) {
            String letter = String.valueOf(character).toUpperCase();

            if ( !HackParameters.encryptionAlphabet.contains(letter) )
                continue;

            if ( !countCharacterTable.containsKey(letter)) {
                countCharacterTable.put(letter, 1);
            } else {
                Integer charactersCount = countCharacterTable.get(letter);
                countCharacterTable.put(letter, charactersCount + 1);
            }
            encryptionAlphabet.remove(letter);
        }

        for (String letter : encryptionAlphabet) {
            countCharacterTable.put(letter, 0);
        }

        return countCharacterTable;
    }

    /**
     * Convert map with count of letters into map with frequency of letters
     * @param countCharacterTable Map with count of letters
     * @return Map with frequency of letters
     */
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

    /**
     * Create encryption table from map with frequency of letters for text and english language
     * @param frequencyTable Map with frequency of letters
     * @return Encryption table
     */
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

    /**
     * Convert map with frequency of letters to list and sort it in descending order by frequency
     * @param frequencyTable Map with frequency of letters
     * @return List with frequency of letters sorted it in descending order by frequency
     */
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
