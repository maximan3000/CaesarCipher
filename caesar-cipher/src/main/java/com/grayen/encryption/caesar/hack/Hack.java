package com.grayen.encryption.caesar.hack;

/**
 * An object that hacks text encrypted with Caesar Cipher.
 * There is several default parameters for hacking system in {@link com.grayen.encryption.caesar.hack.init.HackParameters}
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Hack {
    /**
     * Hack encrypted text with creating encryption table same with real encryption table.
     * For this purpose using next methods:
     * 1) letter frequency in english language
     * 2) dictionary of english words
     * @return Hacked (decrypted) text
     */
    String hack();

    /**
     * If several letters are not hacked as expected, the method allows
     * to replace wrong letter with more suitable letter in the encryption table
     * @param fromSymbol Wrong hacked letter
     * @param toSymbol Letter to replace wrong letter
     */
    void correctEncryptionTableWithHand(String fromSymbol, String toSymbol);
}