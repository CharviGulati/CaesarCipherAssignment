package model;

// The TestCryptographyOperationsList class tests the CryptographyOperationList class and all the methods in it including
// addOperation to the array, removeOperations form the array, and filter encryption and decryption operations


import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class TestCryptographyOperationsList {

    @Test
    public void testAddOperation() {
        CryptographyOperation cryptographyOperation = new CryptographyOperation("Caesar Cipher Encryption", new Date(),
                "jkjkjk", "Hello!", 0, 000);

        CryptographyOperation cryptographyOperationTwo = new CryptographyOperation("Caesar Cipher Encryption", new Date(),
                "hihihi", "Hello World", 5, 111);

        CryptographyOperation cryptographyOperationThree = new CryptographyOperation("Caesar Cipher Decryption", new Date(),
                "jksuieb", "How are you!", 26, 8364);

        // this object has an array list in it
        CryptographyOperationsList newList = new CryptographyOperationsList();

        newList.addOperation(cryptographyOperation);
        newList.addOperation(cryptographyOperationTwo);
        newList.addOperation(cryptographyOperationThree);
        assertEquals("Caesar Cipher Encryption", cryptographyOperation.getType());
        assertEquals("Caesar Cipher Encryption", cryptographyOperationTwo.getType());
        assertEquals("Caesar Cipher Decryption", cryptographyOperationThree.getType());
    }


    @Test
    public void testRemoveOperation(){
        CryptographyOperation cryptographyOperation = new CryptographyOperation("Caesar Cipher Encryption", new Date(),
                "jkjkjk", "Hello!", 0, 000);

        CryptographyOperation cryptographyOperationTwo = new CryptographyOperation("Caesar Cipher Encryption", new Date(),
                "hihihi", "Hello World", 5, 111);

        CryptographyOperation cryptographyOperationThree = new CryptographyOperation("Caesar Cipher Decryption", new Date(),
                "jksuieb", "How are you!", 26, 8364);

        // this object has an array list in it
        CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();

        cryptographyOperationsList.addOperation(cryptographyOperation);
        cryptographyOperationsList.addOperation(cryptographyOperationTwo);
        cryptographyOperationsList.addOperation(cryptographyOperationThree);


        assertEquals(1, cryptographyOperationsList.getDecryptionOperations().size());

        assertEquals("Caesar Cipher Decryption", cryptographyOperationsList.getDecryptionOperations().get(0).getType());
        assertEquals("jksuieb", cryptographyOperationsList.getDecryptionOperations().get(0).getCiphertext());
        assertEquals("How are you!", cryptographyOperationsList.getDecryptionOperations().get(0).getPlaintext());
        assertEquals(26, cryptographyOperationsList.getDecryptionOperations().get(0).getKey());
        assertEquals(8364, cryptographyOperationsList.getDecryptionOperations().get(0).getId());

        assertEquals(2, cryptographyOperationsList.getEncryptionOperations().size());

        assertEquals("Caesar Cipher Encryption", cryptographyOperationsList.getEncryptionOperations().get(0).getType());
        assertEquals("jkjkjk", cryptographyOperationsList.getEncryptionOperations().get(0).getCiphertext());
        assertEquals("Hello!", cryptographyOperationsList.getEncryptionOperations().get(0).getPlaintext());
        assertEquals(0, cryptographyOperationsList.getEncryptionOperations().get(0).getKey());
        assertEquals(000, cryptographyOperationsList.getEncryptionOperations().get(0).getId());

        assertEquals("Caesar Cipher Encryption", cryptographyOperationsList.getEncryptionOperations().get(1).getType());
        assertEquals("hihihi", cryptographyOperationsList.getEncryptionOperations().get(1).getCiphertext());
        assertEquals("Hello World", cryptographyOperationsList.getEncryptionOperations().get(1).getPlaintext());
        assertEquals(5, cryptographyOperationsList.getEncryptionOperations().get(1).getKey());
        assertEquals(111, cryptographyOperationsList.getEncryptionOperations().get(1).getId());


        cryptographyOperationsList.removeOperation(000);
        assertEquals("Caesar Cipher Encryption", cryptographyOperationsList.getEncryptionOperations().get(0).getType());
        assertEquals("hihihi", cryptographyOperationsList.getEncryptionOperations().get(0).getCiphertext());
        assertEquals("Hello World", cryptographyOperationsList.getEncryptionOperations().get(0).getPlaintext());
        assertEquals(5, cryptographyOperationsList.getEncryptionOperations().get(0).getKey());
        assertEquals(111, cryptographyOperationsList.getEncryptionOperations().get(0).getId());

        cryptographyOperationsList.removeOperation(111);
        assertTrue(cryptographyOperationsList.getEncryptionOperations().isEmpty());

        cryptographyOperationsList.removeOperation(8364);
        assertTrue(cryptographyOperationsList.getDecryptionOperations().isEmpty());

    }

}