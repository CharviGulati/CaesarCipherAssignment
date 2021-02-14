package model;

import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCryptographyOperations {

    @Test
    public void testMutators() {
        CryptographyOperation cryptographyOperation = new CryptographyOperation("model.encryption", new Date(), "cipherText",
                "plainText", 0, 000);

        assertEquals("model.encryption", cryptographyOperation.getType());
        assertEquals("cipherText", cryptographyOperation.getCiphertext());
        assertEquals("plainText", cryptographyOperation.getPlaintext());
        assertEquals(0, cryptographyOperation.getKey());
        assertEquals(000, cryptographyOperation.getId());


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

    }


}
