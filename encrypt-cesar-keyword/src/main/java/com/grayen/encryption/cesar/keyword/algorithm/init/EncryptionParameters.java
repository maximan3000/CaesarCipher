package com.grayen.encryption.cesar.keyword.algorithm.init;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class EncryptionParameters {
    /**
     * Ключевое слово в алгоритме шифрования
     */
    public static final String keywordDefault = "DIPLOMAT";

    /**
     * Смещение в алгоритме шифрования
     */
    public static final Integer offsetDefault = 5;

    /**
     * Используемый при шифровании набор английских букв, расположенные в алфавитном порядке
     */
    public static final List<String> alphabetAscending = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );

    /**
     * Шаблон, используемый для того, чтобы определить - используется ли буква в шифровании или нет
     */
    public static Pattern characterPattern = Pattern.compile("[A-Za-z]");
}
