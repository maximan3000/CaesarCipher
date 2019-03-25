package com.grayen.encryption.caesar.hack.implementation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

//TODO иногда не будет работать, так как параметр минимальной длины слова - константа извне
public class DictionaryForHackingTest {
    HashSet<String> expectedDictionary;

    @Before
    public void setUp() {
        expectedDictionary = new HashSet<>();
        expectedDictionary.addAll( Arrays.asList(
                "expression", "individual", "computing"
        ));
    }

    @Test
    public void getDictionary() {
        String[] dictionarySource = {
                "Hello, Mike! How are you?",
                "Today, today, today - it is a fine day!",
                "expression, individual, computing"
        };

        HashSet<String> realDictionary = DictionaryForHacking.getDictionary(dictionarySource);

        Assert.assertEquals(expectedDictionary, realDictionary);
    }

    @Test
    public void shouldGetCorrectWordDistance() {
        String word1 = "computer";
        String word2 = "camputir";

        Assert.assertEquals(Integer.valueOf(2), DictionaryForHacking.getWordsDistance(word1, word2));
    }

    @Test
    public void findClosestWordInDictionary() {
        String word = "ewpression";
        String expectedWord = "expression";

        String realWord = DictionaryForHacking.findClosestWordInDictionary(word, expectedDictionary);

        Assert.assertEquals(expectedWord, realWord);
    }
}