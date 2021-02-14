package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CryptographyOperationsList {

    private ArrayList<CryptographyOperation> cryptographyOperations;

    // Constructor
    public CryptographyOperationsList() {
        this.cryptographyOperations = new ArrayList<CryptographyOperation>();
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // add encryptions to the list
    public void addOperation(CryptographyOperation encryptionOperation) {
        this.cryptographyOperations.add(encryptionOperation);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    // removing enteries from the list
    public void removeOperation(int id) {
        this.cryptographyOperations.removeIf(cryptographyOperation -> cryptographyOperation.getId() == id);
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ArrayList<CryptographyOperation> getOperations() {
        return this.cryptographyOperations;
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ArrayList<CryptographyOperation> getEncryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Encryption")).collect(Collectors.toList());
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ArrayList<CryptographyOperation> getDecryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().equals("Caesar Cipher Decryption")).collect(Collectors.toList());
    }
}
