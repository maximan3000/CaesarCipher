package com.grayen.encryption.cesar.keyword.algorithm.implementation;

import com.grayen.encryption.cesar.keyword.algorithm.init.EncryptionParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EncryptionKeywordTest {

    EncryptionKeyword encryptSystem;

    @Before
    public void setUp() throws Exception {
        encryptSystem = new EncryptionKeyword();
    }

    @Test
    public void shouldEncryptionWork() {
        String sourceText = "SEND MORE MONEY";
        String encryptedText = "HZBY TCGZ TCBZS";

        String str = encryptSystem.encrypt(sourceText);

        Assert.assertEquals(encryptedText, encryptSystem.encrypt(sourceText));
        Assert.assertEquals(sourceText, encryptSystem.decrypt(encryptedText));

        Assert.assertEquals(encryptedText, encryptSystem.encrypt(sourceText, EncryptionParameters.keywordDefault));
        Assert.assertEquals(sourceText, encryptSystem.decrypt(encryptedText, EncryptionParameters.keywordDefault));

        Assert.assertEquals(encryptedText, encryptSystem.encrypt(sourceText, EncryptionParameters.keywordDefault, EncryptionParameters.offsetDefault));
        Assert.assertEquals(sourceText, encryptSystem.decrypt(encryptedText, EncryptionParameters.keywordDefault, EncryptionParameters.offsetDefault));
    }

}