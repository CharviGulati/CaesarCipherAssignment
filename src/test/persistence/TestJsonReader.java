package persistence;

import model.CryptographyOperation;
import model.CryptographyOperationsList;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        JsonReader reader = new JsonReader("./data/TestingData.txt");
        try {
            CryptographyOperationsList cryptographyOperationsList = reader.read();
            assertEquals(0, cryptographyOperationsList.getCryptographyOperations().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/CaesarCipher.json");
        try {
            CryptographyOperationsList cryptographyOperationsList = reader.read();
            assertEquals("encryption", cryptographyOperationsList.getCryptographyOperations().get(0).getType());
            assertEquals("Sat Mar 06 22:53:05 PST 2021", cryptographyOperationsList.
                    getCryptographyOperations().get(0).getDateTime().toString());
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
        JsonReader reader = new JsonReader("./data/TestingData.txt");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("key", 1);
        jsonObject.put("dateTime", "invalid date time here!");
        jsonObject.put("plaintext", "Hello");
        jsonObject.put("ciphertext", "HIIIII");
        jsonObject.put("type", "encryption");
        CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();
        reader.addCryptographyOperation(cryptographyOperationsList, jsonObject);
//        Throwable exception = assertThrows(ParseException.class, () -> reader.addCryptographyOperation(cryptographyOperationsList, jsonObject));
//        assertEquals("expected messages", exception.getMessage());

        fail("Format difference");
    }


}