package com.grayen.encryption.cesar.keyword.algorithm;

import com.grayen.encryption.cesar.keyword.algorithm.implementation.EncryptionKeyword;

public class CesarEncryptionKeywordFabric {
    public static CesarEncryptionKeyword getEncryptionSystem() {
        return new EncryptionKeyword();
    }
}
