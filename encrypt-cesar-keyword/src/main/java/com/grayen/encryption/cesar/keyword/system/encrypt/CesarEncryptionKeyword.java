package com.grayen.encryption.cesar.keyword.system.encrypt;

import com.sun.istack.internal.NotNull;

public interface CesarEncryptionKeyword {
    public String encrypt(@NotNull String sourceText);
    public String encrypt(@NotNull String sourceText, @NotNull String keyWord);
    public String encrypt(@NotNull String sourceText, @NotNull String keyWord, Integer offset);
}
