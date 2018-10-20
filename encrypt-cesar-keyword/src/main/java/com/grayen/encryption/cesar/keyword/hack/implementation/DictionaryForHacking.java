package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.init.HackParameters;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DictionaryForHacking {
    private static Integer minWordLength = HackParameters.minWordLength;
    private static Integer maxWordsDistance = HackParameters.maxWordsDistance;
    private static Pattern wordPattern = HackParameters.wordPattern;

    private String[] sourceOfDictionary;

    public static String findClosestWordInDictionary(String word, HashSet<String> wordsDictionary) {
        String closestWord = "";
        Integer closestWordDistance = minWordLength;

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

        if ( closestWordDistance <= maxWordsDistance )
            return closestWord;

        return word;
    }

    public static Integer getWordsDistance(String word1, String word2) {
        Integer difference = 0;

        for (int i = 0; i < word1.length(); i++) {
            if ( word1.charAt(i) != word2.charAt(i) )
                difference++;
            //TODO переместить в другое место и переписать (тут неправильно) (суть - если 2 буквы находятся по частоте далеко друг от друга, то вероятнее всего 2 данных слова - разные)
            /*if ( Math.abs( word1.charAt(i) - word2.charAt(i) ) > this.maxSymbolDistance)
                return word1.length();*/
        }

        return difference;
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
        Matcher matcher = wordPattern.matcher(stringWithWords);

        while (matcher.find()) {
            String word = matcher.group();
            word = word.toLowerCase();
            wordsSet.add(word);
        }

        return wordsSet;
    }
}
