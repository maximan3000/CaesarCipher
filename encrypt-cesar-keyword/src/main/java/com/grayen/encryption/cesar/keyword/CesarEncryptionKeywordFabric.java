package com.grayen.encryption.cesar.keyword;

import com.grayen.encryption.cesar.keyword.implementation.CesarEncryptionKeywordImplementation;

public class CesarEncryptionKeywordFabric {
    public static CesarEncryptionKeyword getEncryptionSystem() {
        return new CesarEncryptionKeywordImplementation();
    }
}
