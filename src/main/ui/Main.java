package ui;

import model.CaesarCipher;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("1. Caesar Cipher");
        System.out.println("2. Rail Fence Cipher");
        System.out.println("3. Vigenere Cipher");
        System.out.println("");
        System.out.println("Type the number for which cipher would you like to choose for encryption/decryption?");

        Scanner scanner = new Scanner(System.in);
        int methodOfEncryption = scanner.nextInt();

        if (methodOfEncryption == 1) {
            System.out.println("Would you like to encrypt or decrypt a message using the Caesar Cipher?");
            String chooseEncryptOrDecrypt = scanner.nextLine();

            chooseEncryptOrDecrypt.toLowerCase();

            if (chooseEncryptOrDecrypt.equals("encrypt")) {

                System.out.println("Type a string: ");
                String encryptString = scanner.nextLine();

                System.out.println("Type the key: ");
                int inputKey = scanner.nextInt();

                String encryptedMsg = CaesarCipher.encryptCipher(encryptString, inputKey);

                System.out.println(encryptedMsg);

            } else {

                System.out.println("Type a string: ");
                String decryptString = scanner.nextLine();

                scanner.nextLine();

                System.out.println("Type the key: ");
                int decriptionKey = scanner.nextInt();

                String dencryptedMsg = CaesarCipher.decryptCipher(decryptString, decriptionKey);

                System.out.println(dencryptedMsg);
            }
        }

    }
}
