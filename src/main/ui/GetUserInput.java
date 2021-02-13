package ui;

import encryption.CryptographyOperation;
import encryption.CryptographyOperationsList;
import model.CaesarCipher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GetUserInput {

    private static Scanner scanner = new Scanner(System.in);
    private static CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void introductionToProgram() {
        System.out.println("Welcome to the program!\n"
                + "This program can help you encrypt/decrypt messages\n"
                + "Let's start off by encrypting a typed message\n");
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static int getMenuInput() {
        System.out.println("You can now select from the following:\n"
                + "\t1. See previous Encryption entries\n"
                + "\t2. See previous Decryption entries\n"
                + "\t3. Delete a previous entry\n"
                + "\t4. Encrypt a message\n"
                + "\t5. Decrypt a message\n"
                + "Type in the number of the entry you would like to continue with:");
        return scanner.nextInt();
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void menuOperations() {
        int choice = getMenuInput();
        if (choice == 1) {
            ArrayList<CryptographyOperation> encryptionOperations = cryptographyOperationsList
                    .getEncryptionOperations();
            for (CryptographyOperation encryptionOperation : encryptionOperations) {
                System.out.println(encryptionOperation.getType());
            }
        } else if (choice == 2) {
            ArrayList<CryptographyOperation> encryptionOperations = cryptographyOperationsList
                    .getDecryptionOperations();
            for (CryptographyOperation encryptionOperation : encryptionOperations) {
                System.out.println(encryptionOperation.getType());
            }
        } else if (choice == 3) {
            System.out.println("type the ID of the entry you would like to remove: \n");
            int userIDInput = scanner.nextInt();
            cryptographyOperationsList.removeOperation(userIDInput);
        } else if (choice == 4) {
            handleUserEncryptionRequest();
        } else if (choice == 5) {
            handleUserDecryptionRequest();
        } else {
            System.out.println("Unknown option entered. Exiting program.");
            System.exit(0);
        }
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void handleUserEncryptionRequest() {

        System.out.println("Type a string you want to encrypt: ");
        String plaintext = scanner.next();

        System.out.println("Type the key: ");
        int inputKey = scanner.nextInt();

        String ciphertext = CaesarCipher.encryptCipher(plaintext, inputKey);
        int randomId = (int) (Math.random() * 10000000 + 1);

        cryptographyOperationsList.addOperation(new CryptographyOperation(
                "Caesar Cipher Encryption",
                new Date(),
                ciphertext,
                plaintext,
                inputKey,
                randomId));

        System.out.println("You have successfully encrypted a message!\n");
        System.out.println("\t Your encrypted message is: " + ciphertext + "\n\n");
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static void handleUserDecryptionRequest() {

        System.out.println("Type a string you want to decrypt (encrypted with a caesar cipher): ");
        String ciphertext = scanner.nextLine();

        scanner.nextLine();

        System.out.println("Type the key: ");
        int decryptionKey = scanner.nextInt();

        String plaintext = CaesarCipher.decryptCipher(ciphertext, decryptionKey);

        int randomId = (int) (Math.random() * 10000000 + 1);

        cryptographyOperationsList.addOperation(new CryptographyOperation(
                "Caesar Cipher Decryption",
                new Date(),
                ciphertext,
                plaintext,
                decryptionKey,
                randomId));

        System.out.println(plaintext);
    }

}
