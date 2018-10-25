package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.init.HackParameters;

import java.util.HashSet;
import java.util.regex.Matcher;

class DictionaryForHacking {
    private String[] sourceOfDictionary;

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

    public static Integer getWordsDistance(String word1, String word2) {
        Integer difference = 0;

        for (int i = 0; i < word1.length(); i++) {
            String character1 = String.valueOf(word1.charAt(i));
            String character2 = String.valueOf(word2.charAt(i));

            if  (!character1.equals(character2)) {
                difference++;
                if (getCharactersDistance(character1, character2) > HackParameters.maxSymbolDistance) {
                    //если частота символов сильно отличается, то считаем слова разными,
                    // a значит из distance максимальна и равна длиине слова
                    return word1.length();
                }
            }
        }

        return difference;
    }

    private static Integer getCharactersDistance(String character1, String character2) {
        Integer index1 = HackParameters.charactersWithFrequencyDescending.indexOf(character1);
        Integer index2 = HackParameters.charactersWithFrequencyDescending.indexOf(character2);

        return Math.abs(index1 - index2);
    }

    public static HashSet<String> getDictionary(String[] sourceOfDictionary) {
        return new DictionaryForHacking(sourceOfDictionary).createDictionary();
    }

    private DictionaryForHacking(String[] sourceOfDictionary) {
        this.sourceOfDictionary = sourceOfDictionary;
    }

    private HashSet<String> createDictionary() {
        HashSet<String> wordsDictionary = new HashSet<>();

        for (String dictionarySourceLine : this.sourceOfDictionary) {
            HashSet<String> wordsFromDictionarySourceLine = findAllWords(dictionarySourceLine);
            wordsDictionary.addAll( wordsFromDictionarySourceLine );
        }

        return wordsDictionary;
    }

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
