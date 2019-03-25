package com.grayen.encryption.caesar.hack.implementation;

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
        source.put("e", "e");
        source.put("h", "h");

        Map<String, String> expected1 = new HashMap<>();
        expected1.put("a", "c");
        expected1.put("g", "b");
        expected1.put("e", "e");
        expected1.put("h", "h");


        Map<String, String> expected2 = new HashMap<>();
        expected2.put("a", "c");
        expected2.put("g", "b");
        expected2.put("e", "h");
        expected2.put("h", "e");

        Assert.assertEquals(expected1, replaceSymbolInEncryptionTable(source, "b", "c"));
        Assert.assertEquals(expected2, replaceSymbolInEncryptionTable(source, "e", "h"));
    }

    @Test
    public void shouldDecryptEncryptedText() {
        String realDecryptedText = hack();

        Assert.assertEquals(HackAbstractTest.decryptedText, realDecryptedText);
    }
}