package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;

// CryptographyOperation class is a class of the object CryptographyOperation that contains all fields needed
// for the operations. This contains all the getters and setters for the program

public class CryptographyOperation implements Writable {

    private int id;                         // ID for each encryption/decryption entry
    private String type;                    // type of cipher
    private Date dateTime;                  // date and time of the user entry
    private String ciphertext;
    private String plaintext;
    private int key;


    // CryptographyOperation Constructor
    public CryptographyOperation(String type, Date dateTime, String ciphertext, String plaintext, int key, int id) {
        this.type = type;
        this.dateTime = dateTime;
        this.ciphertext = ciphertext;
        this.plaintext = plaintext;
        this.key = key;
        this.id = id;
    }


// All the required getters and setts
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    /**
     * @return JSON representation of the CryptographyOperation Object
     */
    @Override
    public JSONObject toJson() {
        JSONObject cryptographyOperation = new JSONObject();
        cryptographyOperation.put("id", this.id);
        cryptographyOperation.put("key", this.key);
        cryptographyOperation.put("plaintext", this.plaintext);
        cryptographyOperation.put("type", this.type);
        cryptographyOperation.put("dateTime", this.dateTime);
        cryptographyOperation.put("ciphertext", this.ciphertext);
        return cryptographyOperation;
    }
}
