package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHack;
import com.grayen.encryption.cesar.keyword.hack.CesarEncryptionHackFactory;
import com.grayen.encryption.cesar.keyword.implementation.CesarEncryptionKeywordImplementation;
import com.grayen.files.WorkWithFiles;
import com.grayen.files.WorkWithFilesFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CesarEncryptionHackFrequencyCharactersTest {

    CesarEncryptionHack hackEncryption;

    @Before
    public void setUp() {
        hackEncryption = CesarEncryptionHackFactory.getCesarEncryptionHack();
    }

    @Test
    public void shouldMakeCorrectEncryptionTable() throws Exception {
        Assert.assertEquals(getExpectedEncryptionTable(), getRealEncryptionTable( getEncryptedText() ) );
    }

    private Map<String,String> getExpectedEncryptionTable() {
        String needToParse =
                "Z=E;J=T;C=A;V=O;B=I;G=N;L=S;H=H;A=R;P=D;Y=L;X=C;K=U;D=M;E=W;T=F;Q=G;W=Y;I=P;S=B;N=V;M=K;R=X;O=J;U=Q;F=Z;"+
                "z=e;j=t;c=a;v=o;b=i;g=n;l=s;h=h;a=r;p=d;y=l;x=c;k=u;d=m;e=w;t=f;q=g;w=y;i=p;s=b;n=v;m=k;r=x;o=j;u=q;f=z";
        String[] tableEntries = needToParse.split(";");
        Map<String,String> encryptionTable = new HashMap<>();

        for(String entry : tableEntries) {
            String[] parsedEntry = entry.split("=");
            encryptionTable.put(parsedEntry[0], parsedEntry[1]);
        }

        return encryptionTable;
    }

    private Map<String,String> getRealEncryptionTable(String encrypedText) throws Exception {
        Method method;
        try {
            method = CesarEncryptionHackFrequencyCharacters.class.getDeclaredMethod("getHackedEncryptionTable", String.class);
        }
        catch (NoSuchMethodException exception) {
            throw new Exception("Can't find method to create encryptionTable");
        }
        method.setAccessible(true);

        return (Map<String, String>)method.invoke(hackEncryption, encrypedText);
    }

    public String getEncryptedText() throws Exception {
        WorkWithFiles encrypted = WorkWithFilesFactory.getWorkWithFiles("src/test/resources/encrypted/");
        return encrypted.getFileContent("wikipediaRockfeller.txt");
    }
}