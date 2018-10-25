package com.grayen.encryption.cesar.keyword.algorithm.implementation;

import java.util.Map;

public class EncryptionKeyword extends EncryptionAbstract {
    @Override
    protected Map<String,String> getEncryptionTable(String keyword, Integer offset) {
        return TableForEncryption.getTable(keyword, offset);
    }
}
