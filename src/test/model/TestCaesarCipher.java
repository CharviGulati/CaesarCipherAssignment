package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// testCaesarCipherEncryption is the testing class for the Caesar Cipher class. Tests accurate
// string encryption and decryption and validity of user input of keys

class TestCaesarCipher {

    @Test
    public void testCaesarCipherEncryption() {
        String plainText = "abc";
        assertEquals("cde", CaesarCipher.encryptCipher(plainText, 2));
        assertEquals("abc", CaesarCipher.encryptCipher(plainText, 26));
        assertEquals("abc", CaesarCipher.encryptCipher(plainText, 0));
        assertEquals("bcd", CaesarCipher.encryptCipher(plainText, 1));
        assertEquals("", CaesarCipher.encryptCipher(plainText, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainText, 27));
        assertEquals("", CaesarCipher.encryptCipher(plainText, 40));

        String plainTextTwo = "ABC";
        assertEquals("CDE", CaesarCipher.encryptCipher(plainTextTwo, 2));
        assertEquals("ABC", CaesarCipher.encryptCipher(plainTextTwo, 26));
        assertEquals("ABC", CaesarCipher.encryptCipher(plainTextTwo, 0));
        assertEquals("BCD", CaesarCipher.encryptCipher(plainTextTwo, 1));

        String plainTextThree = "AbC";
        assertEquals("CdE", CaesarCipher.encryptCipher(plainTextThree, 2));
        assertEquals("AbC", CaesarCipher.encryptCipher(plainTextThree, 26));
        assertEquals("AbC", CaesarCipher.encryptCipher(plainTextThree, 0));
        assertEquals("BcD", CaesarCipher.encryptCipher(plainTextThree, 1));

        String plainTextFour = "Hello World";
        assertEquals("Jgnnq Yqtnf", CaesarCipher.encryptCipher(plainTextFour, 2));
        assertEquals("Hello World", CaesarCipher.encryptCipher(plainTextFour, 26));
        assertEquals("Hello World", CaesarCipher.encryptCipher(plainTextFour, 0));
        assertEquals("Ifmmp Xpsme", CaesarCipher.encryptCipher(plainTextFour, 1));

        String plainTextFive = "Hello World 123";
        assertEquals("Jgnnq Yqtnf 123", CaesarCipher.encryptCipher(plainTextFive, 2));
        assertEquals("Hello World 123", CaesarCipher.encryptCipher(plainTextFive, 26));
        assertEquals("Hello World 123", CaesarCipher.encryptCipher(plainTextFive, 0));
        assertEquals("Ifmmp Xpsme 123", CaesarCipher.encryptCipher(plainTextFive, 1));

        String plainTextSix = "Hello World 123!";
        assertEquals("Jgnnq Yqtnf 123!", CaesarCipher.encryptCipher(plainTextSix, 2));
        assertEquals("Hello World 123!", CaesarCipher.encryptCipher(plainTextSix, 26));
        assertEquals("Hello World 123!", CaesarCipher.encryptCipher(plainTextSix, 0));
        assertEquals("Ifmmp Xpsme 123!", CaesarCipher.encryptCipher(plainTextSix, 1));

        assertEquals("", CaesarCipher.encryptCipher(plainTextSix, 27));
        assertEquals("", CaesarCipher.encryptCipher(plainTextSix, -1));

        String plainTextSeven = "!@#$%!";
        assertEquals("!@#$%!", CaesarCipher.encryptCipher(plainTextSeven, 5));
        assertEquals("!@#$%!", CaesarCipher.encryptCipher(plainTextSeven, 17));
        assertEquals("", CaesarCipher.encryptCipher(plainTextSeven, -19));
    }

    @Test
    public void testCaesarCipherDecryption() {
        String plainText = "cde";
        assertEquals("abc", CaesarCipher.decryptCipher(plainText, 2));
        assertEquals("cde", CaesarCipher.decryptCipher(plainText, 26));
        assertEquals("cde", CaesarCipher.decryptCipher(plainText, 0));
        assertEquals("bcd", CaesarCipher.decryptCipher(plainText, 1));
        assertEquals("", CaesarCipher.decryptCipher(plainText, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainText, 27));
        assertEquals("", CaesarCipher.decryptCipher(plainText, 40));

        String plainTextTwo = "CDE";
        assertEquals("ABC", CaesarCipher.decryptCipher(plainTextTwo, 2));
        assertEquals("CDE", CaesarCipher.decryptCipher(plainTextTwo, 26));
        assertEquals("CDE", CaesarCipher.decryptCipher(plainTextTwo, 0));
        assertEquals("BCD", CaesarCipher.decryptCipher(plainTextTwo, 1));

        String plainTextThree = "Hello World";
        assertEquals("Czggj Rjmgy", CaesarCipher.decryptCipher(plainTextThree, 5));
        assertEquals("Hello World", CaesarCipher.decryptCipher(plainTextThree, 26));
        assertEquals("Hello World", CaesarCipher.decryptCipher(plainTextThree, 0));
        assertEquals("Gdkkn Vnqkc", CaesarCipher.decryptCipher(plainTextThree, 1));

        String plainTextFour = "Hello World 123";
        assertEquals("Czggj Rjmgy 123", CaesarCipher.decryptCipher(plainTextFour, 5));
        assertEquals("Hello World 123", CaesarCipher.decryptCipher(plainTextFour, 26));
        assertEquals("Hello World 123", CaesarCipher.decryptCipher(plainTextFour, 0));
        assertEquals("Gdkkn Vnqkc 123", CaesarCipher.decryptCipher(plainTextFour, 1));

        String plainTextFive = "CdE";
        assertEquals("AbC", CaesarCipher.decryptCipher(plainTextFive, 2));
        assertEquals("CdE", CaesarCipher.decryptCipher(plainTextFive, 26));
        assertEquals("CdE", CaesarCipher.decryptCipher(plainTextFive, 0));
        assertEquals("BcD", CaesarCipher.decryptCipher(plainTextFive, 1));

        String plainTextSix = "Hello World 123!";
        assertEquals("Czggj Rjmgy 123!", CaesarCipher.decryptCipher(plainTextSix, 5));
        assertEquals("Hello World 123!", CaesarCipher.decryptCipher(plainTextSix, 26));
        assertEquals("Hello World 123!", CaesarCipher.decryptCipher(plainTextSix, 0));
        assertEquals("Gdkkn Vnqkc 123!", CaesarCipher.decryptCipher(plainTextSix, 1));

        assertEquals("", CaesarCipher.decryptCipher(plainTextSix, 27));
        assertEquals("", CaesarCipher.decryptCipher(plainTextSix, -1));

        String plainTextSeven = "!@#$%!";
        assertEquals("!@#$%!", CaesarCipher.decryptCipher(plainTextSeven, 12));
        assertEquals("!@#$%!", CaesarCipher.decryptCipher(plainTextSeven, 22));

    }

    @Test
    public void testValidKey() {
        assertFalse(CaesarCipher.validKey(Integer.MAX_VALUE));
        assertFalse(CaesarCipher.validKey(Integer.MIN_VALUE));
        assertFalse(CaesarCipher.validKey(-3));
        assertFalse(CaesarCipher.validKey(27));
        assertFalse(CaesarCipher.validKey(100));
        assertTrue(CaesarCipher.validKey(0));
        assertTrue(CaesarCipher.validKey(1));
        assertTrue(CaesarCipher.validKey(20));
        assertTrue(CaesarCipher.validKey(26));
    }


}