package com.grayen.encryption.caesar.hack.implementation;

import com.grayen.encryption.caesar.hack.init.HackParameters;
import com.grayen.encryption.caesar.hack.util.HackUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Class that creates dictionary of english words from text
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
class DictionaryForHacking {
    /**
     * Text - source of dictionary
     */
    private String[] sourceOfDictionary;

    /**
     * Find word in dictionary same with input word depend on See Also
     * @see DictionaryForHacking#getWordsDistance(String, String)
     * @param word Input word
     * @param wordsDictionary Dictionary
     * @return Word from dictionary same with input word
     */
    public static String findClosestWordInDictionary(String word, HashSet<String> wordsDictionary) {
        String closestWord = "";
        Integer closestWordDistance = HackParameters.minWordLength;

        for (String dictionaryWord : wordsDictionary) {
            if (dictionaryWord.length() == word.length()) {
                Integer wordDistance = getWordsDistance(word, dictionaryWord);
                if ( wordDistance < closestWordDistance) {
                    closestWordDistance = wordDistance;
                    closestWord = dictionaryWord;
                }
                if ( wordDistance == 0 )
                    break;
            }
        }

        if ( closestWordDistance <= HackParameters.maxWordsDistance )
            return closestWord;

        return word;
    }

    /**
     * Get metrics over two words. Just count of difference letters on same positions
     * @see DictionaryForHacking#getCharactersDistance(String, String)
     * @param word1
     * @param word2
     * @return Metric - word's distance
     */
    public static Integer getWordsDistance(String word1, String word2) {
        Integer difference = 0;
        Map<String, String> differentLetters = new HashMap();

        for (int i = 0; i < word1.length(); i++) {
            String character1 = String.valueOf(word1.charAt(i));
            String character2 = String.valueOf(word2.charAt(i));

            if  (!character1.equals(character2)) {
                if (!HackUtils.containsKeyWithValue(character1, character2, differentLetters)) {
                    difference++;
                    differentLetters.put(character1, character2);
                }

                if (getCharactersDistance(character1, character2) > HackParameters.maxSymbolDistance) {
                    return word1.length();
                }
            }
        }

        return difference;
    }

    /**
     * Get wat's distance between two letters in the list of english letters sorted by descending frequency
     * @param character1
     * @param character2
     * @return Index difference between two letters
     */
    private static Integer getCharactersDistance(String character1, String character2) {
        Integer index1 = HackParameters.charactersWithFrequencyDescending.indexOf(character1);
        Integer index2 = HackParameters.charactersWithFrequencyDescending.indexOf(character2);

        return Math.abs(index1 - index2);
    }

    /**
     * Create dictionary from text
     * @see DictionaryForHacking#createDictionary()
     * @param sourceOfDictionary Text
     * @return Dictionary of english letters
     */
    public static HashSet<String> getDictionary(String[] sourceOfDictionary) {
        return new DictionaryForHacking(sourceOfDictionary).createDictionary();
    }

    private DictionaryForHacking(String[] sourceOfDictionary) {
        this.sourceOfDictionary = sourceOfDictionary;
    }

    /**
     * Create dictionary from the source text
     * @see DictionaryForHacking#findAllWords(String)
     * @return Dictionary of english letters
     */
    private HashSet<String> createDictionary() {
        HashSet<String> wordsDictionary = new HashSet<>();

        for (String dictionarySourceLine : this.sourceOfDictionary) {
            HashSet<String> wordsFromDictionarySourceLine = findAllWords(dictionarySourceLine);
            wordsDictionary.addAll( wordsFromDictionarySourceLine );
        }

        return wordsDictionary;
    }

    /**
     * Search all words in the string
     * @param stringWithWords String with words
     * @return Set of words
     */
    private HashSet<String> findAllWords(String stringWithWords) {
        HashSet<String> wordsSet = new HashSet<>();
        Matcher matcher = HackParameters.wordPattern.matcher(stringWithWords);

        while (matcher.find()) {
            String word = matcher.group();
            word = word.toLowerCase();
            wordsSet.add(word);
        }

        return wordsSet;
    }
}
