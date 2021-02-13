package encryption;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class CryptographyOperationsList {

    private ArrayList<CryptographyOperation> cryptographyOperations;

    // Constructor
    public CryptographyOperationsList() {
        this.cryptographyOperations = new ArrayList<CryptographyOperation>();
    }

    // add encryptions to the list
    public void addOperation(CryptographyOperation encryptionOperation) {
        this.cryptographyOperations.add(encryptionOperation);
    }

    // change to iterators
    // removing enteries from the list
    public void removeOperation(int id) {
        this.cryptographyOperations.removeIf(cryptographyOperation -> cryptographyOperation.getId() == id);
    }

    public ArrayList<CryptographyOperation> getOperations() {
        return this.cryptographyOperations;
    }

    public ArrayList<CryptographyOperation> getEncryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().contains("encrypt")).collect(Collectors.toList());
    }

    public ArrayList<CryptographyOperation> getDecryptionOperations() {
        return (ArrayList<CryptographyOperation>) cryptographyOperations.stream()
                .filter(encOp -> encOp.getType().contains("decrypt")).collect(Collectors.toList());
    }


}

// some fields to make distinct