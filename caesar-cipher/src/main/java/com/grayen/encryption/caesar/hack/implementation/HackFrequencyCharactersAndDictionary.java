package com.grayen.encryption.caesar.hack.implementation;

import com.grayen.encryption.caesar.hack.Hack;
import com.grayen.encryption.caesar.hack.init.HackParameters;

import java.util.*;
import java.util.regex.Matcher;

/**
 * Class that uses dictionary of english words and frequency of english letters to hack encryption table
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
class HackFrequencyCharactersAndDictionary extends HackAbstract implements Hack {
    private HashSet<String> dictionary;

    @Override
    public String hack() {
        correctEncryptionTableWithDictionary();
        return decryptTextWithEncryptionTable(this.encryptedText);
    }

    public HackFrequencyCharactersAndDictionary(String encryptedText, String[] sourceOfDictionary) {
        super(encryptedText);
        this.dictionary = DictionaryForHacking.getDictionary(sourceOfDictionary);
        correctEncryptionTableWithDictionary();
    }

    /**
     * Correct encryption table with dictionary.
     * For each word in hacked text the method call function in See Also
     * @see HackFrequencyCharactersAndDictionary#correctEncryptionTableWithDecryptedWordAndDictionary(String)
     */
    public void correctEncryptionTableWithDictionary() {
        Matcher wordsMatches = HackParameters.wordPattern.matcher(encryptedText);

        while (wordsMatches.find()) {
            String encryptedWord = wordsMatches.group();
            encryptedWord = encryptedWord.toLowerCase();
            String decryptedWord = decryptTextWithEncryptionTable(encryptedWord);

            correctEncryptionTableWithDecryptedWordAndDictionary(decryptedWord);
        }
    }

    @Override
    protected Map<String, String> getHackedEncryptionTable(String encryptedText) {
        return FrequencyCharactersForHacking.getHackedEncryptionTable(encryptedText);
    }

    /**
     * Method searches word in the dictionary that is same with input word and
     * corrects encryption table with See Alse method
     * @see HackAbstract#replaceSymbolInEncryptionTable(Map, String, String)
     * @param decryptedWord Hacked word
     */
    private void correctEncryptionTableWithDecryptedWordAndDictionary(String decryptedWord) {
        String closestWord = DictionaryForHacking.findClosestWordInDictionary(decryptedWord, this.dictionary);

        if ( closestWord.equals("") )
            return;

        for (Integer i = 0; i < decryptedWord.length(); i++) {
            String decryptedSymbol = String.valueOf( decryptedWord.charAt(i) );
            String correctSymbol = String.valueOf( closestWord.charAt(i) );
            if ( !decryptedSymbol.equals(correctSymbol) ) {
                this.encryptionTable = replaceSymbolInEncryptionTable(encryptionTable, decryptedSymbol, correctSymbol);
            }
        }
    }
}
