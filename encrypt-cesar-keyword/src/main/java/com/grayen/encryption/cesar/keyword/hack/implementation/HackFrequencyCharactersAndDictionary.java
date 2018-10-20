package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.init.HackParameters;

import java.util.*;
import java.util.regex.Matcher;

public class HackFrequencyCharactersAndDictionary extends HackAbstract implements CesarEncryptionHack {
    private HashSet<String> dictionary;

    public HackFrequencyCharactersAndDictionary(String encryptedText, String[] sourceOfDictionary) {
        super(encryptedText);
        this.dictionary = DictionaryForHacking.getDictionary(sourceOfDictionary);
        correctEncryptionTableWithDictionary();
    }

    @Override
    protected Map<String, String> getHackedEncryptionTable(String encryptedText) {
        return FrequencyCharactersForHacking.getHackedEncryptionTable(encryptedText);
    }

    private void correctEncryptionTableWithDictionary() {
        Matcher wordsMatches = HackParameters.wordPattern.matcher(encryptedText);

        while (wordsMatches.find()) {
            String encryptedWord = wordsMatches.group();
            encryptedWord = encryptedWord.toLowerCase();
            String decryptedWord = decryptTextWithEncryptionTable(encryptedWord);

            correctEncryptionTableWithDecryptedWordAndDictionary(decryptedWord);
        }
    }

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
