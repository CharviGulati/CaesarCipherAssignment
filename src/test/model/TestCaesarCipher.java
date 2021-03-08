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

        String plainTextEight = "AZ";
        assertEquals("BA", CaesarCipher.encryptCipher(plainTextEight, 1));
        assertEquals("AZ", CaesarCipher.encryptCipher(plainTextEight, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextEight, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextEight, 27));

        String plainTextNine = "az";
        assertEquals("ba", CaesarCipher.encryptCipher(plainTextNine, 1));
        assertEquals("az", CaesarCipher.encryptCipher(plainTextNine, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextNine, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextNine, 27));

        String plainTextTen = "a";
        assertEquals("b", CaesarCipher.encryptCipher(plainTextTen, 1));
        assertEquals("a", CaesarCipher.encryptCipher(plainTextTen, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextTen, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextTen, 27));

        String plainTextEleven = "z";
        assertEquals("a", CaesarCipher.encryptCipher(plainTextEleven, 1));
        assertEquals("z", CaesarCipher.encryptCipher(plainTextEleven, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextEleven, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextEleven, 27));

        String plainTextTwelve = "a!";
        assertEquals("b!", CaesarCipher.encryptCipher(plainTextTwelve, 1));
        assertEquals("a!", CaesarCipher.encryptCipher(plainTextTwelve, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextTwelve, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextTwelve, 27));

        String plainTextThirteen = "z!";
        assertEquals("a!", CaesarCipher.encryptCipher(plainTextThirteen, 1));
        assertEquals("z!", CaesarCipher.encryptCipher(plainTextThirteen, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextThirteen, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextThirteen, 27));

        String plainTextFourteen = "!";
        assertEquals("!", CaesarCipher.encryptCipher(plainTextFourteen, 10));
        assertEquals("!", CaesarCipher.encryptCipher(plainTextFourteen, 0));
        assertEquals("", CaesarCipher.encryptCipher(plainTextFourteen, -1));
        assertEquals("", CaesarCipher.encryptCipher(plainTextFourteen, 27));


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

        String plainTextEight = "AZ";
        assertEquals("ZY", CaesarCipher.decryptCipher(plainTextEight, 1));
        assertEquals("AZ", CaesarCipher.decryptCipher(plainTextEight, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextEight, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextEight, 27));

        String plainTextNine = "az";
        assertEquals("zy", CaesarCipher.decryptCipher(plainTextNine, 1));
        assertEquals("az", CaesarCipher.decryptCipher(plainTextNine, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextNine, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextNine, 27));

        String plainTextTen = "a";
        assertEquals("z", CaesarCipher.decryptCipher(plainTextTen, 1));
        assertEquals("a", CaesarCipher.decryptCipher(plainTextTen, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextTen, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextTen, 27));

        String plainTextEleven = "z";
        assertEquals("y", CaesarCipher.decryptCipher(plainTextEleven, 1));
        assertEquals("z", CaesarCipher.decryptCipher(plainTextEleven, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextEleven, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextEleven, 27));

        String plainTextTwelve = "a!";
        assertEquals("z!", CaesarCipher.decryptCipher(plainTextTwelve, 1));
        assertEquals("a!", CaesarCipher.decryptCipher(plainTextTwelve, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextTwelve, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextTwelve, 27));

        String plainTextThirteen = "z!";
        assertEquals("y!", CaesarCipher.decryptCipher(plainTextThirteen, 1));
        assertEquals("z!", CaesarCipher.decryptCipher(plainTextThirteen, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextThirteen, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextThirteen, 27));

        String plainTextFourteen = "!";
        assertEquals("!", CaesarCipher.decryptCipher(plainTextFourteen, 10));
        assertEquals("!", CaesarCipher.decryptCipher(plainTextFourteen, 0));
        assertEquals("", CaesarCipher.decryptCipher(plainTextFourteen, -1));
        assertEquals("", CaesarCipher.decryptCipher(plainTextFourteen, 27));

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