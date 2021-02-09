package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCaesarCipher {

    @Test
    public void testCaesarCipherEncryption() {

        String plainText = "abc";
        String encryptedMsg = CaesarCipher.encryptCipher(plainText, 2);
        assertEquals("cde", encryptedMsg);

//        String encryptedTextTwo = CaesarCipher.encryptCipher(plainText, 27);
//        assertEquals("bcd", encryptedTextTwo);

        String encryptedTextThree = CaesarCipher.encryptCipher(plainText, 0);
        assertEquals("abc", encryptedTextThree);

    }

    @Test
    public void testCaesarCipherDecryption() {
        String plainText = "cde";
        String dencryptedMsg = CaesarCipher.decryptCipher(plainText, 2);
        assertEquals("abc", dencryptedMsg);

//        String dencryptedMsgTwo = CaesarCipher.decryptCipher("bcd", 27);
//        assertEquals("abc", dencryptedMsgTwo);

        String dencryptedMsgThree = CaesarCipher.decryptCipher(plainText, 0);
        assertEquals("cde", dencryptedMsgThree);

    }


}