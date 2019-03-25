package com.grayen.encryption.caesar.algorithm.implementation;

import com.grayen.encryption.caesar.algorithm.Caesar;

/**
 * Use this class to create an object that implements
 * {@link Caesar} interface
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
public class CaesarFabric {
    /**
     * @return And object that implements Caesar Cipher with keyword and offset
     * @since 1.0.0
     */
    public static Caesar getEncryptionSystem() {
        return new EncryptionKeyword();
    }
}
