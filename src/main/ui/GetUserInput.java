package ui;

import model.CryptographyOperation;
import model.CryptographyOperationsList;
import model.CaesarCipher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// GetUserInput gets user input on various questions needed for the program to run successfully

public class GetUserInput {

    private static Scanner scanner = new Scanner(System.in);
    private static CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();



    // EFFECTS: prints out the initial program greeting
    public static void introductionToProgram() {
        System.out.println("Welcome to the program!\n"
                + "This program can help you encrypt/decrypt messages\n"
                + "Let's start off by encrypting a typed message\n");
    }


    // EFFECTS: prints out the menu options and gets user inout for their choice form the options
    public static int getMenuInput() {
        System.out.println("You can now select from the following:\n"
                + "\t1. See previous Encryption entries\n"
                + "\t2. See previous Decryption entries\n"
                + "\t3. Delete a previous entry\n"
                + "\t4. Encrypt a message\n"
                + "\t5. Decrypt a message\n"
                + "\t6. Exit the program\n"
                + "Type in the number of the entry you would like to continue with:");
        return scanner.nextInt();
    }



    // EFFECTS: directs user input to the proper method or asks for more information from user and gets input
    public static void menuOperations() {
        int choice = getMenuInput();
        scanner.nextLine(); // clears newline character from input buffer
        if (choice == 1) {
            printEncryptionOperations();
        } else if (choice == 2) {
            printDecryptionOperations();
        } else if (choice == 3) {
            System.out.println("Type the ID of the entry you would like to remove: \n");
            int userIDInput = scanner.nextInt();
            cryptographyOperationsList.removeOperation(userIDInput);
            System.out.println("Entry with the ID " + userIDInput + " has been deleted from file\n\n");
        } else if (choice == 4) {
            handleUserEncryptionRequest();
        } else if (choice == 5) {
            handleUserDecryptionRequest();
        } else if (choice == 6) {
            System.out.println("\nThank you for using the Caesar Cipher. Exiting program now.");
            System.exit(0);
        }
    }



    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: gets user input for encryption details and adds the operation to the array list
    public static void handleUserEncryptionRequest() {
        System.out.println("Type a string you want to encrypt: ");
        String plaintext = scanner.nextLine();

        System.out.println("Enter the key (number between 0-26): ");
        int inputKey = scanner.nextInt();
        scanner.nextLine();

        if (CaesarCipher.validKey(inputKey)) {
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
        } else {
            System.out.println("Invalid Key entered. Exiting program");
            System.exit(0);
        }
    }



    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: gets user input for decryption details and adds the operation to the array list
    public static void handleUserDecryptionRequest() {

        System.out.println("Type a string you want to decrypt: ");
        String ciphertext = scanner.nextLine();

        System.out.println("Enter the key (number between 0-26): ");
        int decryptionKey = scanner.nextInt();

        if (CaesarCipher.validKey(decryptionKey)) {
            String plaintext = CaesarCipher.decryptCipher(ciphertext, decryptionKey);

            int randomId = (int) (Math.random() * 10000000 + 1);

            cryptographyOperationsList.addOperation(new CryptographyOperation(
                    "Caesar Cipher Decryption",
                    new Date(),
                    ciphertext,
                    plaintext,
                    decryptionKey,
                    randomId));

            System.out.println("You have successfully decrypted a message!\n");
            System.out.println("\t Your decrypted message is: " + plaintext + "\n\n");
        } else {
            System.out.println("invalid Key entered. Exiting program");
            System.exit(0);
        }
    }



    // EFFECTS: prints previous user encryption operations
    private static void printEncryptionOperations() {
        System.out.println("\nEncryption Operations currently recorded: ");
        if (cryptographyOperationsList.getEncryptionOperations().size() == 0) {
            System.out.println("\nNo Encryption operations recorded\n");
        } else {
            for (CryptographyOperation encryptionOperation : cryptographyOperationsList.getEncryptionOperations()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                System.out.println(encryptionOperation.getType()
                        + ": You entered the cipher text: "
                        + encryptionOperation.getPlaintext()
                        + ", with key: "
                        + encryptionOperation.getKey()
                        + " with ID: "
                        + encryptionOperation.getId()
                        + " at "
                        + dateFormat.format(encryptionOperation.getDateTime()));
            }
            System.out.print("\n----------------------------------------\n");
        }
    }




    // EFFECTS: prints previous user decryption operations
    private static void printDecryptionOperations() {
        System.out.println("\nDecryption Operations currently recorded: ");
        if (cryptographyOperationsList.getDecryptionOperations().size() == 0) {
            System.out.println("\nNo decryption operations recorded\n");
        } else {
            for (CryptographyOperation encryptionOperation : cryptographyOperationsList.getDecryptionOperations()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                System.out.println(encryptionOperation.getType()
                        + ": You entered the cipher text: "
                        + encryptionOperation.getCiphertext()
                        + ", with key: "
                        + encryptionOperation.getKey()
                        + " with ID: "
                        + encryptionOperation.getId()
                        + " at "
                        + dateFormat.format(encryptionOperation.getDateTime()));
            }
            System.out.print("\n----------------------------------------\n");
        }
    }
}
