package com.grayen.encryption.cesar.keyword.hack.implementation;

import com.grayen.files.WorkWithFiles;
import com.grayen.files.WorkWithFilesFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCharactersForHackingTest {

    @Test
    public void shouldMakeCorrectEncryptionTable() throws Exception {
        String encryptedText = getEncryptedText();

        Map<String, String> expectedEncryptionTable = getExpectedEncryptionTable();

        Map<String, String> realEncryptionTable = FrequencyCharactersForHacking.getHackedEncryptionTable(encryptedText);

        Assert.assertEquals(expectedEncryptionTable, realEncryptionTable);
    }

    private Map<String,String> getExpectedEncryptionTable() {
        String needToParse =
                "Z=E;J=T;C=A;V=O;B=I;G=N;L=S;H=H;A=R;P=D;Y=L;X=C;K=U;D=M;E=W;T=F;Q=G;W=Y;I=P;S=B;N=V;M=K;R=X;O=J;U=Q;F=Z";
        String[] tableEntries = needToParse.split(";");

        Map<String,String> encryptionTable = new HashMap<>();
        for(String entry : tableEntries) {
            String[] parsedEntry = entry.split("=");
            encryptionTable.put(parsedEntry[0], parsedEntry[1]);
        }

        return encryptionTable;
    }

    public String getEncryptedText() throws Exception {
        WorkWithFiles encrypted = WorkWithFilesFactory.getWorkWithFiles("src/test/resources/encrypted/");
        return encrypted.getFileContent("wikipediaRockfeller.txt");
    }
}