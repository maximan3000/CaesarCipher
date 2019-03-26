package com.grayen.encryption.caesar.hack.init;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Default parameters for the hacking system
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
public class HackParameters {
    /**
     * How many different letters hacked word and word in the dictionary could have.
     * If there is more different letters, we think that there are different words.
     * (for example, "algorithm" and "algorithq" are the same if maximum difference more than 1)
     */
    public static Integer maxWordsDistance = 1;

    /**
     * If we have a word like "closure" in our dictionary and hacked word like "closurx"
     * it will be bad idea to change "x" to "e" because "x" has much less frequency in english than "e"
     * so this words are probably different.
     */
    public static Integer maxSymbolDistance = 6;

    /**
     * List of english letters with descending frequency order
     * Taken from: https://en.wikipedia.org/wiki/English_alphabet
     */
    public static List<String> charactersWithFrequencyDescending = Arrays.asList(
        "E", "T", "A", "O", "I", "N", "S", "H", "R", "D", "L", "C", "U", "M", "W", "F", "G", "Y", "P", "B", "V", "K", "X", "J", "Q", "Z"
    );

    /**
     * Minimal word length of the dictionary for hacking
     */
    public static Integer minWordLength = 6;

    /**
     * A pattern for the encrypted word
     */
    public static Pattern wordPattern = Pattern.compile("[a-zA-Z]{" + minWordLength + ",}");

    /**
     * List of encrypted letters
     */
    public static List<String> encryptionAlphabet = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );
}
