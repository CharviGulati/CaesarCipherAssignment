package ui;

import model.CryptographyOperation;
import model.CryptographyOperationsList;
import model.CaesarCipher;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// GetUserInput gets user input on various questions needed for the program to run successfully

public class GetUserInput {

    private static Scanner scanner = new Scanner(System.in);
    private static CryptographyOperationsList cryptographyOperationsList = new CryptographyOperationsList();
    private static JsonWriter writer = new JsonWriter("./data/CaesarCipher.txt");  // changed
    private static JsonReader jsonReader = new JsonReader("./data/CaesarCipher.txt");


    // EFFECTS: prints out the initial program greeting
    public static void introductionToProgram() {
        System.out.println("Welcome to the program!\n"
                + "This program can help you encrypt/decrypt messages\n");
    }


    // EFFECTS: prints out the menu options and gets user inout for their choice form the options
    public static int getMenuInput() {

        System.out.println("You can now select from the following:\n"
                + "\t1. Encrypt a message\n"
                + "\t2. Decrypt a message\n"
                + "\t3. See previous Encryption entries\n"
                + "\t4. See previous Decryption entries\n"
                + "\t5. Delete a previous entry\n"
                + "\t6. Save cryptography operation to file\n"
                + "\t7. Load previous text file\n"
                + "\t8. Remove an entry permanently from text file\n"
                + "\t9. Exit the program\n"
                + "Type in the number of the entry you would like to continue with:");

        return scanner.nextInt();
    }

    // EFFECTS: directs user input to the proper method or asks for more information from user and gets input
    public static void menuOperations() {
        int choice = getMenuInput();
        scanner.nextLine(); // clears newline character from input buffer
        if (choice == 1) {
            handleUserEncryptionRequest();
        } else if (choice == 2) {
            handleUserDecryptionRequest();
        } else if (choice == 3) {
            printEncryptionOperations();
        } else if (choice == 4) {
            printDecryptionOperations();
        } else if (choice == 5) {
            System.out.println("Type the ID of the entry you would like to remove: \n");
            int userIDInput = scanner.nextInt();
            cryptographyOperationsList.removeOperation(userIDInput);
            System.out.println("Entry with the ID " + userIDInput + " has been deleted from file\n\n");
        } else if (choice == 6) {
            try {
                writeCryptographyOperationsToFile();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
            System.out.println("\nSaved operations to file!");
            System.out.println("\n---------------------------------------------------------------\n");
        } else if (choice == 7) {
            try {
                printEncryptionOperations();
                printDecryptionOperations();
                cryptographyOperationsList = jsonReader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nThese are all the saved operations");
            System.out.println("\n----------------------------------------------------------------\n");
        } else if (choice == 8) {
            removeEntryFromTextFile();
            System.out.println("Entry has been deleted.");
            System.out.println("\n----------------------------------------------------------------\n");
        } else if (choice == 9) {
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

            CryptographyOperation cryptographyOperation = new CryptographyOperation(
                    "Caesar Cipher Encryption",
                    new Date(),
                    ciphertext,
                    plaintext,
                    inputKey,
                    randomId);
            cryptographyOperationsList.addOperation(cryptographyOperation);

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
                        + " that was given the ID: "
                        + encryptionOperation.getId()
                        + " at "
                        + dateFormat.format(encryptionOperation.getDateTime()));
            }
            //System.out.print("\n-----------------------------------------------------------------\n");
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
                        + " that was given the ID: "
                        + encryptionOperation.getId()
                        + " at "
                        + dateFormat.format(encryptionOperation.getDateTime()));
            }
            //System.out.print("\n-----------------------------------------------------------------\n");
        }
    }

    public static void writeCryptographyOperationsToFile() throws FileNotFoundException {
        writer.open();
        writer.write(cryptographyOperationsList);
        writer.close();
    }

    public static void removeEntryFromTextFile() {
        System.out.println("What is the ID of the entry you would like to delete?");
        int userIdInput = scanner.nextInt();

        try {
            cryptographyOperationsList = jsonReader.read();
            cryptographyOperationsList.removeOperation(userIdInput);
            writeCryptographyOperationsToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
