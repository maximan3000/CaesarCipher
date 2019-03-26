package com.grayen.encryption.caesar.algorithm;

/**
 * An object that encrypt/decrypt input text using Caesar Cipher algorithm.
 * The parameters of the encryption are keyword and offset. You can use it's default values from
 * {@link com.grayen.encryption.caesar.algorithm.init.EncryptionParameters} or type them as parameters.
 *
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Caesar {
    /**
     * @see Caesar#encrypt(String, String, Integer)
     * @param sourceText Text you want to encrypt
     * @return Encrypted text
     */
    String encrypt(String sourceText);

    /**
     * @see Caesar#encrypt(String, String, Integer)
     * @param sourceText Text you want to encrypt
     * @param keyword Keyword used in encryption
     * @return Encrypted text
     */
    String encrypt(String sourceText, String keyword);

    /**
     * @see Caesar#encrypt(String, String, Integer)
     * @param sourceText Text you want to encrypt
     * @param offset Offset of the keyword used in encryption
     * @return Encrypted text
     */
    String encrypt(String sourceText, Integer offset);
    /**
     * Encrypt input text with Caesar Cipher.
     * If parameters (offset, keyword) are not specified, method uses
     * default parameters from {@link com.grayen.encryption.caesar.algorithm.init.EncryptionParameters}
     * @param sourceText Text you want to encrypt
     * @param offset Offset of the keyword used in encryption
     * @param keyword Keyword used in encryption
     * @return Encrypted text
     */
    String encrypt(String sourceText, String keyword, Integer offset);

    /**
     * @see Caesar#decrypt(String, String, Integer)
     * @param sourceText Text you want to decrypt
     * @return Decrypted text
     */
    String decrypt(String sourceText);

    /**
     * @see Caesar#decrypt(String, String, Integer)
     * @param sourceText Text you want to decrypt
     * @param keyword Keyword used in encryption
     * @return Decrypted text
     */
    String decrypt(String sourceText, String keyword);

    /**
     * @see Caesar#decrypt(String, String, Integer)
     * @param sourceText Text you want to decrypt
     * @param offset Offset of the keyword used in encryption
     * @return Decrypted text
     */
    String decrypt(String sourceText, Integer offset);
    /**
     * Encrypt input text with Caesar Cipher.
     * If parameters (offset, keyword) are not specified, method uses
     * default parameters from {@link com.grayen.encryption.caesar.algorithm.init.EncryptionParameters}
     * @param sourceText Text you want to decrypt
     * @param offset Offset of the keyword used in encryption
     * @param keyword Keyword used in encryption
     * @return Decrypted text
     */
    String decrypt(String sourceText, String keyword, Integer offset);
}
