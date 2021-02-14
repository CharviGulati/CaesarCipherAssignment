package model;

import java.util.Date;

public class CryptographyOperation {

    private int id;
    private String type;
    private Date dateTime;
    private String ciphertext;
    private String plaintext;
    private int key;

    public CryptographyOperation(String type, Date dateTime, String ciphertext, String plaintext, int key, int id) {
        this.type = type;
        this.dateTime = dateTime;
        this.ciphertext = ciphertext;
        this.plaintext = plaintext;
        this.key = key;
        this.id = id;
    }


    // all getters and setters
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

}
