package persistence;

import model.CryptographyOperationsList;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {

    @Test
    public void testReadingFromNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CryptographyOperationsList cryptographyOperationsList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    public void testReaderEmptyCryptographyOperationsList() {
        JsonReader reader = new JsonReader("./data/TestEmpty.txt");
        try {
            CryptographyOperationsList cryptographyOperationsList = reader.read();
            assertEquals(0, cryptographyOperationsList.getCryptographyOperations().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/TestCaesarCipher.json");
        try {
            CryptographyOperationsList cryptographyOperationsList = reader.read();
            assertEquals("encryption", cryptographyOperationsList.getCryptographyOperations().get(0).getType());
            assertNotNull(cryptographyOperationsList.getCryptographyOperations().get(0).getDateTime());
            assertEquals("jk", cryptographyOperationsList.getCryptographyOperations().get(0).getCiphertext());
            assertEquals("hi", cryptographyOperationsList.getCryptographyOperations().get(0).getPlaintext());
            assertEquals(5, cryptographyOperationsList.getCryptographyOperations().get(0).getId());
            assertEquals(2, cryptographyOperationsList.getCryptographyOperations().get(0).getKey());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testDateTimeParseException() {
        JsonReader reader = new JsonReader("./data/TestEmpty.txt");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("key", 1);
        jsonObject.put("dateTime", "invalid date time here!");
        jsonObject.put("plaintext", "Hello");
        jsonObject.put("ciphertext", "HIIIII");
        jsonObject.put("type", "encryption");
        CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();
        reader.addCryptographyOperation(cryptographyOperationsList, jsonObject);
        assertNull(cryptographyOperationsList.getCryptographyOperations().get(0).getDateTime());
    }

}
