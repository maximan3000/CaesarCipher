package com.grayen.encryption.cesar.keyword.system.encrypt;

import com.grayen.encryption.cesar.keyword.system.encrypt.implementation.CesarEncryptionKeywordImplementation;

public class CesarEncryptionKeywordFabric {
    public static CesarEncryptionKeyword getEncryptionSystem() {
        return new CesarEncryptionKeywordImplementation();
    }
}
