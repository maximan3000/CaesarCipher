package com.grayen.encryption.caesar.hack.implementation;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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
        Path sourceFile = FileSystems.getDefault().getPath("src/test/resources/encrypted/wikipediaRockfeller.txt");

        String fileContent = Files
                .lines(sourceFile, StandardCharsets.UTF_8)
                .collect(Collectors.joining("\n"));

        return fileContent;
    }
}