package model;

// CryptographyOperationsList is the class that adds, removes, and filters CryptographyOperations
// depending on user request in UI.GetUserInput


import java.util.ArrayList;
import java.util.stream.Collectors;

public class CryptographyOperationsList {

    private ArrayList<CryptographyOperation> cryptographyOperations;

    // Constructor
    public CryptographyOperationsList() {
        this.cryptographyOperations = new ArrayList<CryptographyOperation>();
    }


    // REQUIRES:
    // MODIFIES: this and ArrayList<CryptographyOperation>
    // EFFECTS: adds cryptographyOperation to the array list
    public void addOperation(CryptographyOperation cryptographyOperation) {
        this.cryptographyOperations.add(cryptographyOperation);
    }


    // REQUIRES:
    // MODIFIES: this and ArrayList<CryptographyOperation>
    // EFFECTS: removes entries from the array list
    public void removeOperation(int id) {
        this.cryptographyOperations.removeIf(cryptographyOperation -> cryptographyOperation.getId() == id);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: filters the array list to find only EncryptionOperations
    public ArrayList<CryptographyOperation> getEncryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Encryption")).collect(Collectors.toList());
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: filters the array list to find only DecryptionOperations
    public ArrayList<CryptographyOperation> getDecryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Decryption")).collect(Collectors.toList());
    }
}
