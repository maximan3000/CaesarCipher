package com.grayen.encryption.cesar.keyword.hack.init;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class HackParameters {
    /**
     * Максимальное количество букв, на которое 2 слова могут отличаться,
     * чтобы их можно было считать одним и тем же словом
     * (например, слова algorithm и algorithq - отличаются на 1 букву и их
     * можно считать отдним и тем же словом с учетом того, что второе слово
     * имеет ошибку вхлома - букву q вместо m)
     */
    public static final Integer maxWordsDistance = 1;

    /**
     * Содержит параметр, на который 2 буквы могут максимально отставать друг от друга,
     * чтобы их можно было считать близкими по частоте и имело бы смысл корректировать словарь
     * для взлома (например, символы "e" и "x" по частоте в люьом случае сильно отстают друг от друга,
     * поэтому менять часто появляющуюся "e" на "x" не целесообразно)
     */
    public static final Integer maxSymbolDistance = 5;

    /**
     * Список букв англтийского алфавита, расположенный в порядке уменьшения частоты использования.
     * Взят из википедии:
     * https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D0%B8%D1%82
     */
    public static final List<String> charactersWithFrequencyDescending = Arrays.asList(
        "E", "T", "A", "O", "I", "N", "S", "H", "R", "D", "L", "C", "U", "M", "W", "F", "G", "Y", "P", "B", "V", "K", "X", "J", "Q", "Z"
    );

    /**
     * Минимальное число букв во взламываемом слове. В зависимости от этого параметра определяется,
     * будет ли данное слово искаться в словаре и, соответственно, будет ли по этому слову правиться словарь дешифрации.
     * Также слова, короче данной длины в словарь вноситься не будут
     */
    public static final Integer minWordLength = 8;

    /**
     * Шаблон, используемый системой для определения того,
     * будет ли данная последовательность считаться словом или нет
     */
    public static final Pattern wordPattern = Pattern.compile("[a-zA-Z]{" + minWordLength + ",}");

    /**
     * Алфавит символов, которые по предположению, кодируются. Используется
     * для проверки принадлежности символа к числу закодированных
     */
    public static final String encryptionAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
}
