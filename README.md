# Caesar Cipher (Keyed Caesar) java library

Java library for Ceasar Cipher (Keyed Caesar) that allows:
1. Encrypt & decrypt english text
2. Hack decrypted english text

## Requirements

* Java JDK 1.8 or later

## Algorithm

* [Read it on wikipedia.org](https://en.wikipedia.org/wiki/Caesar_cipher)

## Usage

### Encrypt/Decrypt text

Create an encryption system instance
```java
import com.grayen.encryption.caesar.algorithm.Caesar;
import com.grayen.encryption.caesar.algorithm.implementation.CaesarFabric;

Caesar encryption = CaesarFabric.getEncryptionSystem();
```

Encrypt text using your keywords and offset or it's default values
```java
String textToEncrypt = "Text need to encrypt";

//String encryptedText1 = encryption.encrypt(textToEncrypt);
//String encryptedText2 = encryption.encrypt(textToEncrypt, "keyword");
//String encryptedText3 = encryption.encrypt(textToEncrypt, 5);
String encryptedText4 = encryption.encrypt(textToEncrypt, "keyword", 5);
```

Decrypt with the same way
```java
String textToDecrypt = "Xcyx pccb xq cpauwsx";

//String encryptedText1 = encryption.decrypt(sourceText);
//String encryptedText2 = encryption.decrypt(sourceText, "keyword");
//String encryptedText3 = encryption.decrypt(sourceText, 5);
String encryptedText4 = encryption.decrypt(textToDecrypt, "keyword", 5);
```

### Hack text

Hack system uses dictioanry with english words to improve hacking quality.
So you need to create string array with many words.
To do that, download large book with .txt extention and parse it to string[].


```java
import java.nio.file.FileSystems;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;

String encryptedText = "Xcyx pccb xq cpauwsx";

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
hack.correctEncryptionTableWithHand(from, to);
String hackedText = hack.hack();
```