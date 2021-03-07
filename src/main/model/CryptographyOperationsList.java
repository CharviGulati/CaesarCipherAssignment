package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.stream.Collectors;

// CryptographyOperationsList is the class that adds, removes, and filters CryptographyOperations
// depending on user request in UI.GetUserInput

public class CryptographyOperationsList implements Writable {

    private ArrayList<CryptographyOperation> cryptographyOperations;

    // Constructor
    public CryptographyOperationsList() {
        this.cryptographyOperations = new ArrayList<CryptographyOperation>();
    }


    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: adds cryptographyOperation to the array list
    public void addOperation(CryptographyOperation cryptographyOperation) {
        this.cryptographyOperations.add(cryptographyOperation);
    }


    // REQUIRES: ArrayList<CryptographyOperation> to not be empty
    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: removes entries from the array list
    public void removeOperation(int id) {
        this.cryptographyOperations.removeIf(cryptographyOperation -> cryptographyOperation.getId() == id);
    }


    // REQUIRES: ArrayList<CryptographyOperation> cryptographyOperations to not be empty
    // MODIFIES:
    // EFFECTS: filters the array list to find only EncryptionOperations
    public ArrayList<CryptographyOperation> getEncryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Encryption")).collect(Collectors.toList());
    }


    // REQUIRES: ArrayList<CryptographyOperation> cryptographyOperations to not be empty
    // MODIFIES:
    // EFFECTS: filters the array list to find only DecryptionOperations
    public ArrayList<CryptographyOperation> getDecryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Decryption")).collect(Collectors.toList());
    }

    public ArrayList<CryptographyOperation> getCryptographyOperations() {
        return this.cryptographyOperations;
    }

    private JSONArray cryptographyOperationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CryptographyOperation cryptographyOperation : cryptographyOperations) {
            jsonArray.put(cryptographyOperation.toJson());
        }

        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cryptographyOperations", cryptographyOperationsToJson());
        return json;
    }
}
