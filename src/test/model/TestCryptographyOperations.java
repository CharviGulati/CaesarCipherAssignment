package model;

// TestCryptographyOperations is the test class for the CryptographyOperations class in the model package
// Properly tests all the getters and setters in that class


import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCryptographyOperations {

    @Test
    public void testMutators() {

        Date testDate = new Date();
        CryptographyOperation cryptographyOperation = new CryptographyOperation("encryption", testDate, "cipherText",
                "plainText", 0, 000);

        assertEquals("encryption", cryptographyOperation.getType());
        assertEquals("cipherText", cryptographyOperation.getCiphertext());
        assertEquals("plainText", cryptographyOperation.getPlaintext());
        assertEquals(0, cryptographyOperation.getKey());
        assertEquals(000, cryptographyOperation.getId());
        assertEquals(testDate.getTime(), cryptographyOperation.getDateTime().getTime());


        cryptographyOperation.setType("decryption");
        assertEquals("decryption", cryptographyOperation.getType());

        cryptographyOperation.setCiphertext("newCipherText");
        assertEquals("newCipherText", cryptographyOperation.getCiphertext());

        cryptographyOperation.setPlaintext("newPlainText");
        assertEquals("newPlainText", cryptographyOperation.getPlaintext());

        cryptographyOperation.setKey(5);
        assertEquals(5, cryptographyOperation.getKey());

        cryptographyOperation.setId(7);
        assertEquals(7, cryptographyOperation.getId());

        Date newDate = new Date();
        cryptographyOperation.setDateTime(newDate);
        assertEquals(newDate.getTime(), cryptographyOperation.getDateTime().getTime());
    }
}
