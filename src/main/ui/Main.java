package ui;

import encryption.EncryptionOperation;
import encryption.EncryptionOperationsList;
import model.CaesarCipher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static EncryptionOperationsList encryptionOperationsList;

    public static void main(String[] args) {
        encryptionOperationsList = new EncryptionOperationsList();

        while (true) {
            scanner = new Scanner(System.in);
            introduction();

            int methodOfEncryption = scanner.nextInt();

            if (methodOfEncryption == 1) {
                System.out.println("Would you like to encrypt or decrypt a message using the Caesar Cipher?");
                String chooseEncryptOrDecrypt = scanner.next();

                // convert all string inputs to lowercase
                String chosenMethod = chooseEncryptOrDecrypt.toLowerCase();

                if (chosenMethod.equals("encrypt")) {
                    encryptCaesar();
                } else {
                    decryptCaesar();
                }
            }

            moreInfo();
            int moreInfo = scanner.nextInt();

            if (moreInfo == 1) {
                // do
            } else if (moreInfo == 2) {
                // do
            } else if (moreInfo == 3) {
                // do
            } else {
                // do
            }
        }


    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void introduction() {

        System.out.println("1. Caesar Cipher");
        // System.out.println("2. Rail Fence Cipher");
        System.out.println("Type the number for which cipher would you like to choose for encryption/decryption?");

    }

    public static int moreInfo() {

        System.out.println("You can select the following or continue encrypting/decrypting:"
                + "1. See previous Encryption entries"
                + "2. See previous Decryption entries"
                + "3. Delete a previous entry"
                + "4. Continue encrypting/decrypting\n"
                + "Type in the number of the entry you would like to continue with");

        int moreInfo = scanner.nextInt();

        return moreInfo;
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void encryptCaesar() {

        ArrayList<EncryptionOperation> operations = encryptionOperationsList.getOperations();
        for (int i = 0; i < operations.size(); i++) {
            System.out.println(operations.get(i).getType());

            System.out.println("Type a string you want to encrypt: ");
            String plaintext = scanner.next();

            System.out.println("Type the key: ");
            int inputKey = scanner.nextInt();

            String ciphertext = CaesarCipher.encryptCipher(plaintext, inputKey);
            int randomId = (int) (Math.random() * 10000000 + 1);

            encryptionOperationsList.addOperation(new EncryptionOperation(
                    "Caesar Cipher Encryption",
                    new Date(),
                    ciphertext,
                    plaintext,
                    inputKey,
                    randomId));

            System.out.println(ciphertext);
        }
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void decryptCaesar() {

        System.out.println("Type a string: ");
        String ciphertext = scanner.nextLine();

        scanner.nextLine();

        System.out.println("Type the key: ");
        int decryptionKey = scanner.nextInt();

        String plaintext = CaesarCipher.decryptCipher(ciphertext, decryptionKey);

        int randomId = (int) (Math.random() * 10000000 + 1);

        encryptionOperationsList.addOperation(new EncryptionOperation(
                "Caesar Cipher Decryption",
                new Date(),
                ciphertext,
                plaintext,
                decryptionKey,
                randomId));

        System.out.println(plaintext);
    }

    public static String printOperations() {
        return "";  // stub

    }



}
