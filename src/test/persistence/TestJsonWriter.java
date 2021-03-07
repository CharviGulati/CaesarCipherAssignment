package persistence;

import model.CryptographyOperation;
import model.CryptographyOperationsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code obtained from https://github.com/stleary/JSON-java as per CPSC 210 instructions
// modifications were made tot he code


public class TestJsonWriter {

    @Test
    void testInvalidFile() {
        try {
            CryptographyOperationsList list = new CryptographyOperationsList();
            JsonWriter writer = new JsonWriter("./data\0doesNotExist.json");
            writer.open();
            fail("IOException should have been thrown");
        } catch (IOException e) {
            // should pass
        }
    }

    @Test
    void testWriterEmptyCryptographyOperationsList() {
        try {
            CryptographyOperationsList list = new CryptographyOperationsList();
            JsonWriter writer = new JsonWriter("./data/TestingData.txt");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestingData.txt");
            assertEquals(0, reader.read().getCryptographyOperations().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCryptographyOperationsList() {
        try {
            CryptographyOperationsList list = new CryptographyOperationsList();
            Date testDate = new Date();
            CryptographyOperation co = new CryptographyOperation("encryption",
                    testDate, "jk", "hi", 2, 5);
            list.addOperation(co);

            JsonWriter writer = new JsonWriter("./data/CaesarCipher.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/CaesarCipher.json");
            list = reader.read();
            assertEquals("encryption", list.getCryptographyOperations().get(0).getType());
            assertEquals(testDate.toString(), list.getCryptographyOperations().get(0).getDateTime().toString());
            assertEquals("jk", list.getCryptographyOperations().get(0).getCiphertext());
            assertEquals("hi", list.getCryptographyOperations().get(0).getPlaintext());
            assertEquals(2, list.getCryptographyOperations().get(0).getKey());
            assertEquals(5, list.getCryptographyOperations().get(0).getId());

            assertEquals(1, list.getCryptographyOperations().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    // add another co to this list
    // rempove entry from text file
}
