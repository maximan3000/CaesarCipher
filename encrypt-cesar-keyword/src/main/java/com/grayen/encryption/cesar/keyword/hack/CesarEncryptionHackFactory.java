package com.grayen.encryption.cesar.keyword.hack;

import com.grayen.encryption.cesar.keyword.hack.implementation.CesarEncryptionHackFrequencyCharacters;

public class CesarEncryptionHackFactory {
    public static CesarEncryptionHack getCesarEncryptionHack () {
        return new CesarEncryptionHackFrequencyCharacters();
    }
}
