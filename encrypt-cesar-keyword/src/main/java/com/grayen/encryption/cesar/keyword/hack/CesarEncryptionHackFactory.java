package com.grayen.encryption.cesar.keyword.hack;

import com.grayen.encryption.cesar.keyword.hack.implementation.HackFrequencyCharactersAndDictionary;

public class CesarEncryptionHackFactory {
    public static CesarEncryptionHack getCesarEncryptionHack (String encryptedText, String[] dictionarySource) {
        return new HackFrequencyCharactersAndDictionary(encryptedText, dictionarySource);
    }
}
