package com.grayen.encryption.cesar.keyword.implementation;

import com.grayen.encryption.cesar.keyword.parameters.DefaultEncryptionParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CesarEncryptionKeywordImplementationTest {

    CesarEncryptionKeywordImplementation encryptSystem;

    @Before
    public void setUp() throws Exception {
        encryptSystem = new CesarEncryptionKeywordImplementation();
    }

    @Test
    public void shouldMakeCorrectEncryptionTable() throws Exception {
        Assert.assertEquals(getExpectedEncryptionTable(), getRealEncryptionTable(DefaultEncryptionParameters.keyword, DefaultEncryptionParameters.offset));
    }

    private Map<String,String> getExpectedEncryptionTable() {
        String needToParse = "A=V;B=W;C=X;D=Y;E=Z;F=D;G=I;H=P;I=L;J=O;K=M;L=A;M=T;N=B;O=C;P=E;Q=F;R=G;S=H;T=J;U=K;V=N;W=Q;X=R;Y=S;Z=U";
        String[] tableEntries = needToParse.split(";");
        Map<String,String> encryptionTable = new HashMap<>();

        for(String entry : tableEntries) {
            String[] parsedEntry = entry.split("=");
            encryptionTable.put(parsedEntry[0], parsedEntry[1]);
        }

        return encryptionTable;
    }

    private Map<String,String> getRealEncryptionTable(String keyWord, Integer offset) throws Exception {
        Method method;
        try {
            method = CesarEncryptionKeywordImplementation.class.getDeclaredMethod("getEncryptionTable", String.class, Integer.class);
        }
        catch (NoSuchMethodException exception) {
            throw new Exception("Can't find method to create encryptionTable");
        }
        method.setAccessible(true);

        return (Map<String, String>)method.invoke(encryptSystem, keyWord, offset);
    }

    @Test
    public void shouldEncryptionWork() {
        String sourceText = "SEND MORE MONEY";
        String encryptedText = "HZBY TCGZ TCBZS";
        String keyWord = "DIPLOMAT";

        Assert.assertEquals(encryptedText, encryptSystem.encrypt(sourceText, keyWord));
        Assert.assertEquals(sourceText, encryptSystem.decrypt(encryptedText, keyWord));
    }

}