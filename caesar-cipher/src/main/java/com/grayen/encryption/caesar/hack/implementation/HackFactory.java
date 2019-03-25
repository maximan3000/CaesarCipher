package com.grayen.encryption.caesar.hack.implementation;

import com.grayen.encryption.caesar.hack.Hack;
import com.grayen.encryption.caesar.hack.implementation.HackFrequencyCharactersAndDictionary;

/**
 * Creates an instance of class that implements {@link Hack}
 */
public class HackFactory {
    /**
     * Creates hack system to hack input text
     * @param encryptedText Encrypted text
     * @param dictionarySource Array of strings that is used to create dictionary of words
     *                         (Use big texts like "War and Peace")
     * @return Hack system
     */
    public static Hack getCaesarEncryptionHack (String encryptedText, String[] dictionarySource) {
        return new HackFrequencyCharactersAndDictionary(encryptedText, dictionarySource);
    }
}
