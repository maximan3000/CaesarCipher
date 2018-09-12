package com.grayen.encryption.cesar.keyword;

public interface CesarEncryptionKeyword {
    String encrypt(String sourceText);
    String encrypt(String sourceText, String keyword);
    String encrypt(String sourceText, String keyword, Integer offset);
    String decrypt(String sourceText);
    String decrypt(String sourceText, String keyword);
    String decrypt(String sourceText, String keyword, Integer offset);
}
