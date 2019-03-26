[![Maven Central](https://img.shields.io/maven-central/v/com.github.maximan3000/caesar-cipher.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.maximan3000%22%20AND%20a:%22caesar-cipher%22)
# Caesar Cipher (Keyed Caesar) java library

Java library for Ceasar Cipher (Keyed Caesar) that can:

1. Encrypt & decrypt english text using keyword and keyword offset (shift)
2. Hack encrypted english text using frequency of letters and dictionary of words

## Requirements

* Java JDK 1.8 or later

## Installation

#### Maven users
```
<dependency>
  <groupId>com.github.maximan3000</groupId>
  <artifactId>caesar-cipher</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Gradle Groovy DSL
```
implementation 'com.github.maximan3000:caesar-cipher:1.0.0'
```

## Algorithm

* [Read it on wikipedia.org](https://en.wikipedia.org/wiki/Caesar_cipher)

## Usage

#### Encrypt/Decrypt text

Create an encryption system instance
```java
import com.grayen.encryption.caesar.algorithm.Caesar;
import com.grayen.encryption.caesar.algorithm.implementation.CaesarFabric;

Caesar encryption = CaesarFabric.getEncryptionSystem();
```

Encrypt text using your keywords and offset or it's default values
```java
String textToEncrypt = "Text need to encrypt";

String encryptedText = encryption.encrypt(textToEncrypt, "keyword", 5);
```

Decrypt with the same way
```java
String textToDecrypt = "Xcyx pccb xq cpauwsx";

String decryptedText = encryption.decrypt(textToDecrypt, "keyword", 5);
```

#### Hack text

Hack system uses dictioanry with english words to improve hacking quality.
So you need to create string array with many words.
To do that, download large book with .txt extention and parse it to string[].


```java
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;

Path sourceFile = FileSystems.getDefault().getPath("BigBook.txt");
List<String> fileLines = Files.readAllLines(sourceFile, StandardCharsets.UTF_8);
String[] dictionary = fileLines.toArray(new String[0]);
```

Create hack system instance. 
* Note 1: successful hack depends on encrypted text size
* Note 2: text for dictionary should be large
```java
import com.grayen.encryption.caesar.hack.Hack;
import com.grayen.encryption.caesar.hack.implementation.HackFactory;

Hack hack = HackFactory.getCaesarEncryptionHack(encryptedText, dictionary);
```

Hack encrypted text
```java
String hackedText = hack.hack();
```

If it's need, correct hacked text with hands and hack the text again
```java
hack.correctEncryptionTableWithHand("a", "x");
String hackedText = hack.hack();
```
