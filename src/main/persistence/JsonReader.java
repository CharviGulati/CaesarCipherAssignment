package persistence;


import model.CryptographyOperation;
import model.CryptographyOperationsList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

// Code obtained from https://github.com/stleary/JSON-java as per CPSC 210 instructions
// modifications were made to the code

// this class reads JSON formatted data and writes it back into Java Objects

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private static Scanner scanner = new Scanner(System.in);

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CryptographyOperationsList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CryptographyOperationsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCryptographyOperationsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses CryptographyOperationsList from JSON object format and returns it
    private CryptographyOperationsList parseCryptographyOperationsList(JSONObject jsonObject) {
        CryptographyOperationsList list = new CryptographyOperationsList();
        addCryptographyOperationsList(list, jsonObject);
        return list;
    }

    // MODIFIES: CryptographyOperationsList
    // EFFECTS: parses CryptographyOperations from JSON object and adds them to CryptographyOperationsList
    private void addCryptographyOperationsList(CryptographyOperationsList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cryptographyOperations");
        for (Object json : jsonArray) {
            JSONObject nextCryptographyOperation = (JSONObject) json;
            //if (!json.toString().equals("{}")) {
            addCryptographyOperation(list, nextCryptographyOperation);
            //}
        }
    }

    // MODIFIES: CryptographyOperationsList
    // EFFECTS: parses CryptographyOperations from JSON object and adds it to CryptographyOperationsList
    public void addCryptographyOperation(CryptographyOperationsList list, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        int key = jsonObject.getInt("key");
        String dateTime = jsonObject.getString("dateTime");
        String plainText = jsonObject.getString("plaintext");
        String cipherText = jsonObject.getString("ciphertext");
        String type = jsonObject.getString("type");
        Date newDateTime = null;

        DateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            newDateTime = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CryptographyOperation co = new CryptographyOperation(type, newDateTime, cipherText, plainText, key, id);
        list.addOperation(co);
    }

}
