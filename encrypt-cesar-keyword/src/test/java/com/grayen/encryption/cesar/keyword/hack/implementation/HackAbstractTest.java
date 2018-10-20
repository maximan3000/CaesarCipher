package com.grayen.encryption.cesar.keyword.hack.implementation;

import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class HackAbstractTest extends HackAbstract {
    private static String encryptedText = "Ihts ts encrypied sirtng";
    private static String decryptedText = "This is encrypted string";

    public HackAbstractTest() {
        super(encryptedText);
    }

    @Override
    protected Map<String, String> getHackedEncryptionTable(String encryptedText) {
        Map<String, String> encryptionTable = new HashMap<>();
        encryptionTable.put("I", "T");
        encryptionTable.put("T", "I");

        return encryptionTable;
    }

    @Test
    public void shouldCorrectSetEncryptionTableCaseInsensitive() {
        this.encryptionTable.put("Ы", "Й ");
        setEncryptionTableCaseInsensitive();

        Assert.assertTrue( this.encryptionTable.containsKey("ы") );
    }

    @Test
    public void shouldCorrectReplaceSymbolInTable() {
        Map<String, String> source = new HashMap<>();
        source.put("a", "b");
        source.put("g", "c");

        Map<String, String> expected = new HashMap<>();
        expected.put("a", "c");
        expected.put("g", "b");

        Assert.assertEquals(expected, replaceSymbolInEncryptionTable(source, "b", "c"));
    }

    @Test
    public void shouldDecryptEncryptedText() {
        String realDecryptedText = hack();

        Assert.assertEquals(HackAbstractTest.decryptedText, realDecryptedText);
    }
}