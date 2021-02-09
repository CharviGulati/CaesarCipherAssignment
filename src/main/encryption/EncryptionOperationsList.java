package encryption;

import model.CaesarCipher;

import java.util.ArrayList;

public class EncryptionOperationsList {

    private ArrayList<EncryptionOperation> encryptionOps;

    public EncryptionOperationsList() {
        this.encryptionOps = new ArrayList<EncryptionOperation>();
    }

    public void addOperation(EncryptionOperation encryptionOperation) {
        this.encryptionOps.add(encryptionOperation);
    }

    // change to iterators
    public void removeOperation(int id) {
        for (int i = 0; i < encryptionOps.size(); i++) {
            if (id == encryptionOps.get(i).getId()) {
                this.encryptionOps.remove(i);
            }
        }
    }

    public ArrayList<EncryptionOperation> getOperations() {
        return this.encryptionOps;
    }
}