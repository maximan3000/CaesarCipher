package com.grayen.encryption.caesar.algorithm.init;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The class with the library's default parameters
 * @author GraYen
 * @version 1.0.0
 * @since 1.0.0
 */
public class EncryptionParameters {
    /**
     * A default keyword for the Caesar Cipher
     * @since 1.0.0
     */
    public static String keywordDefault = "DIPLOMAT";

    /**
     * A default offset of the keyword for the Caesar Cipher
     * @since 1.0.0
     */
    public static Integer offsetDefault = 5;

    /**
     * A list of letters that are need to be encrypted
     * @since 1.0.0
     */
    public static List<String> alphabetAscending = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    );

    /**
     * A pattern for the letters that are need to be encrypted
     * @since 1.0.0
     */
    public static Pattern characterPattern = Pattern.compile("[A-Za-z]");
}
