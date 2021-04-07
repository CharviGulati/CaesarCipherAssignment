package model;

import exceptions.InvalidKeyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// testCaesarCipherEncryption is the testing class for the Caesar Cipher class. Tests accurate
// string encryption and decryption and validity of user input of keys

class TestCaesarCipher {

    private String plainText = "abc";
    private String plainTextTwo = "ABC";
    private String plainTextThree = "AbC";
    private String plainTextFour = "Hello World";
    private String plainTextFive = "Hello World 123";
    private String plainTextSix = "Hello World 123!";
    private String plainTextSeven = "!@#$%!";
    private String plainTextEight = "AZ";
    private String plainTextNine = "az";
    private String plainTextTen = "a";
    private String plainTextEleven = "z";
    private String plainTextFourteen = "!";
    private String plainTextThirteen = "z!";
    private String plainTextTwelve = "a!";

    private String cipherText = "cde";
    private String cipherTextTwo = "CDE";
    private String cipherTextThree = "Hello World";
    private String cipherTextFour = "Hello World 123";
    private String cipherTextFive = "CdE";
    private String cipherTextSix = "Hello World 123!";
    private String cipherTextSeven = "!@#$%!";
    private String cipherTextEight = "AZ";
    private String cipherTextNine = "az";
    private String cipherTextTen = "a";
    private String cipherTextEleven = "z";
    private String cipherTextTwelve = "a!";
    private String cipherTextThirteen = "z!";
    private String cipherTextFourteen = "!";

    @Test
    public void testCaesarCipherEncryptionValidKey() throws InvalidKeyException {
        assertEquals("cde", CaesarCipher.encryptCipher(plainText, 2));
        assertEquals("abc", CaesarCipher.encryptCipher(plainText, 26));
        assertEquals("abc", CaesarCipher.encryptCipher(plainText, 0));
        assertEquals("bcd", CaesarCipher.encryptCipher(plainText, 1));

        assertEquals("CDE", CaesarCipher.encryptCipher(plainTextTwo, 2));
        assertEquals("ABC", CaesarCipher.encryptCipher(plainTextTwo, 26));
        assertEquals("ABC", CaesarCipher.encryptCipher(plainTextTwo, 0));
        assertEquals("BCD", CaesarCipher.encryptCipher(plainTextTwo, 1));

        assertEquals("CdE", CaesarCipher.encryptCipher(plainTextThree, 2));
        assertEquals("AbC", CaesarCipher.encryptCipher(plainTextThree, 26));
        assertEquals("AbC", CaesarCipher.encryptCipher(plainTextThree, 0));
        assertEquals("BcD", CaesarCipher.encryptCipher(plainTextThree, 1));

        assertEquals("Jgnnq Yqtnf", CaesarCipher.encryptCipher(plainTextFour, 2));
        assertEquals("Hello World", CaesarCipher.encryptCipher(plainTextFour, 26));
        assertEquals("Hello World", CaesarCipher.encryptCipher(plainTextFour, 0));
        assertEquals("Ifmmp Xpsme", CaesarCipher.encryptCipher(plainTextFour, 1));

        assertEquals("Jgnnq Yqtnf 123", CaesarCipher.encryptCipher(plainTextFive, 2));
        assertEquals("Hello World 123", CaesarCipher.encryptCipher(plainTextFive, 26));
        assertEquals("Hello World 123", CaesarCipher.encryptCipher(plainTextFive, 0));
        assertEquals("Ifmmp Xpsme 123", CaesarCipher.encryptCipher(plainTextFive, 1));

        assertEquals("Jgnnq Yqtnf 123!", CaesarCipher.encryptCipher(plainTextSix, 2));
        assertEquals("Hello World 123!", CaesarCipher.encryptCipher(plainTextSix, 26));
        assertEquals("Hello World 123!", CaesarCipher.encryptCipher(plainTextSix, 0));
        assertEquals("Ifmmp Xpsme 123!", CaesarCipher.encryptCipher(plainTextSix, 1));

        assertEquals("!@#$%!", CaesarCipher.encryptCipher(plainTextSeven, 5));
        assertEquals("!@#$%!", CaesarCipher.encryptCipher(plainTextSeven, 17));

        assertEquals("BA", CaesarCipher.encryptCipher(plainTextEight, 1));
        assertEquals("AZ", CaesarCipher.encryptCipher(plainTextEight, 0));

        assertEquals("!", CaesarCipher.encryptCipher(plainTextFourteen, 10));
        assertEquals("!", CaesarCipher.encryptCipher(plainTextFourteen, 0));

        assertEquals("a!", CaesarCipher.encryptCipher(plainTextThirteen, 1));
        assertEquals("z!", CaesarCipher.encryptCipher(plainTextThirteen, 0));

        assertEquals("b!", CaesarCipher.encryptCipher(plainTextTwelve, 1));
        assertEquals("a!", CaesarCipher.encryptCipher(plainTextTwelve, 0));

        assertEquals("a", CaesarCipher.encryptCipher(plainTextEleven, 1));
        assertEquals("z", CaesarCipher.encryptCipher(plainTextEleven, 0));
        assertEquals("b", CaesarCipher.encryptCipher(plainTextTen, 1));
        assertEquals("a", CaesarCipher.encryptCipher(plainTextTen, 0));

        assertEquals("ba", CaesarCipher.encryptCipher(plainTextNine, 1));
        assertEquals("az", CaesarCipher.encryptCipher(plainTextNine, 0));


    }

    @Test
    public void testCaesarCipherEncryptionInvalidKey() {
        try {
            assertEquals("", CaesarCipher.encryptCipher(plainText, -1));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("", CaesarCipher.encryptCipher(plainText, 27));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("", CaesarCipher.encryptCipher(plainText, 40));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("", CaesarCipher.encryptCipher(plainTextSeven, -19));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("", CaesarCipher.encryptCipher(plainTextSeven, -190));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCaesarCipherDecryptionValidKey() {
        try {
            assertEquals("ABC", CaesarCipher.decryptCipher(cipherTextTwo, 2));
            assertEquals("CDE", CaesarCipher.decryptCipher(cipherTextTwo, 26));
            assertEquals("CDE", CaesarCipher.decryptCipher(cipherTextTwo, 0));
            assertEquals("BCD", CaesarCipher.decryptCipher(cipherTextTwo, 1));
            assertEquals("abc", CaesarCipher.decryptCipher(cipherText, 2));
            assertEquals("cde", CaesarCipher.decryptCipher(cipherText, 26));
            assertEquals("cde", CaesarCipher.decryptCipher(cipherText, 0));
            assertEquals("bcd", CaesarCipher.decryptCipher(cipherText, 1));

            assertEquals("Czggj Rjmgy", CaesarCipher.decryptCipher(cipherTextThree, 5));
            assertEquals("Hello World", CaesarCipher.decryptCipher(cipherTextThree, 26));
            assertEquals("Hello World", CaesarCipher.decryptCipher(cipherTextThree, 0));
            assertEquals("Gdkkn Vnqkc", CaesarCipher.decryptCipher(cipherTextThree, 1));

            assertEquals("Czggj Rjmgy 123", CaesarCipher.decryptCipher(cipherTextFour, 5));
            assertEquals("Hello World 123", CaesarCipher.decryptCipher(cipherTextFour, 26));
            assertEquals("Hello World 123", CaesarCipher.decryptCipher(cipherTextFour, 0));
            assertEquals("Gdkkn Vnqkc 123", CaesarCipher.decryptCipher(cipherTextFour, 1));

            assertEquals("AbC", CaesarCipher.decryptCipher(cipherTextFive, 2));
            assertEquals("CdE", CaesarCipher.decryptCipher(cipherTextFive, 26));
            assertEquals("CdE", CaesarCipher.decryptCipher(cipherTextFive, 0));
            assertEquals("BcD", CaesarCipher.decryptCipher(cipherTextFive, 1));

            assertEquals("Czggj Rjmgy 123!", CaesarCipher.decryptCipher(cipherTextSix, 5));
            assertEquals("Hello World 123!", CaesarCipher.decryptCipher(cipherTextSix, 26));
            assertEquals("Hello World 123!", CaesarCipher.decryptCipher(cipherTextSix, 0));
            assertEquals("Gdkkn Vnqkc 123!", CaesarCipher.decryptCipher(cipherTextSix, 1));

            assertEquals("!", CaesarCipher.decryptCipher(cipherTextFourteen, 10));
            assertEquals("!", CaesarCipher.decryptCipher(cipherTextFourteen, 0));

            assertEquals("y!", CaesarCipher.decryptCipher(cipherTextThirteen, 1));
            assertEquals("z!", CaesarCipher.decryptCipher(cipherTextThirteen, 0));

            assertEquals("z!", CaesarCipher.decryptCipher(cipherTextTwelve, 1));
            assertEquals("a!", CaesarCipher.decryptCipher(cipherTextTwelve, 0));
            assertEquals("y", CaesarCipher.decryptCipher(cipherTextEleven, 1));
            assertEquals("z", CaesarCipher.decryptCipher(cipherTextEleven, 0));
            assertEquals("z", CaesarCipher.decryptCipher(cipherTextTen, 1));
            assertEquals("a", CaesarCipher.decryptCipher(cipherTextTen, 0));

            assertEquals("zy", CaesarCipher.decryptCipher(cipherTextNine, 1));
            assertEquals("az", CaesarCipher.decryptCipher(cipherTextNine, 0));
            assertEquals("ZY", CaesarCipher.decryptCipher(cipherTextEight, 1));
            assertEquals("AZ", CaesarCipher.decryptCipher(cipherTextEight, 0));

            assertEquals("!@#$%!", CaesarCipher.decryptCipher(cipherTextSeven, 12));
            assertEquals("!@#$%!", CaesarCipher.decryptCipher(cipherTextSeven, 22));
        } catch (InvalidKeyException e) {
            fail("should not have reached this line of code");
            e.getMessage();
        }
    }


    @Test
    public void testCaesarCipherDecryptionInvalidKey() {

        try {
            assertEquals("", CaesarCipher.decryptCipher(cipherText, -1));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            assertEquals("", CaesarCipher.decryptCipher(cipherText, 40));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("", CaesarCipher.decryptCipher(cipherTextSix, 27));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("", CaesarCipher.decryptCipher(cipherTextEight, -180));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            assertEquals("", CaesarCipher.decryptCipher(cipherTextNine, 180));
            fail("should not have reached this line of code");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testValidKey() throws InvalidKeyException {
        assertThrows(InvalidKeyException.class, () -> CaesarCipher.validKey(Integer.MAX_VALUE));
        assertThrows(InvalidKeyException.class, () -> CaesarCipher.validKey(Integer.MIN_VALUE));
        assertThrows(InvalidKeyException.class, () -> CaesarCipher.validKey(-3));
        assertThrows(InvalidKeyException.class, () -> CaesarCipher.validKey(27));
        assertThrows(InvalidKeyException.class, () -> CaesarCipher.validKey(100));
        assertTrue(CaesarCipher.validKey(0));
        assertTrue(CaesarCipher.validKey(1));
        assertTrue(CaesarCipher.validKey(20));
        assertTrue(CaesarCipher.validKey(26));
    }


}