package model;

// Caesar Cipher class checks validity of key input, and either encrypts or decrypts the message
// depending on the user request

public class CaesarCipher {

    // private - this class should not be instantiated
    private CaesarCipher() {}

    // REQUIRES: key to be >= 0 and <= 26
    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: encrypts user input with given key
    public static String encryptCipher(String plainText, int key) {
        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = plainText.length();
        if (validKey(key)) {
            for (int j = 0; j < lengthMsg; j++) {
                char msgToChar = plainText.charAt(j);
                if ((msgToChar >= 'a' && msgToChar <= 'z')) {
                    msgToChar = (char) (plainText.charAt(j) + key);
                    if (msgToChar > 'z') {
                        msgToChar = (char) (plainText.charAt(j) - (26 - key));
                    }
                }
                if ((msgToChar >= 'A' && msgToChar <= 'Z')) {
                    msgToChar = (char) (plainText.charAt(j) + key);
                    if (msgToChar > 'Z') {
                        msgToChar = (char) (plainText.charAt(j) - (26 - key));
                    }
                }
                buildEncryptedMessage.append(msgToChar);
            }
            return buildEncryptedMessage.toString();
        } else {
            return "";
        }
    }


    // REQUIRES: key to be >= 0 and <= 26
    // MODIFIES: ArrayList<CryptographyOperation> cryptographyOperations
    // EFFECTS: decrypts user input with given key
    public static String decryptCipher(String cipherText, int key) {
        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = cipherText.length();
        if (validKey(key)) {
            for (int j = 0; j < lengthMsg; j++) {
                char msgToChar = cipherText.charAt(j);
                if (msgToChar >= 'a' && msgToChar <= 'z') {
                    msgToChar = (char) (cipherText.charAt(j) - key);
                    if (msgToChar < 'a') {
                        msgToChar = (char) (cipherText.charAt(j) + (26 - key));
                    }
                }
                if (msgToChar >= 'A' && msgToChar <= 'Z') {
                    msgToChar = (char) (cipherText.charAt(j) - key);
                    if (msgToChar < 'A') {
                        msgToChar = (char) (cipherText.charAt(j) + (26 - key));
                    }
                }
                buildEncryptedMessage.append(msgToChar);
            }
            return buildEncryptedMessage.toString();
        } else {
            return "";
        }
    }


    // REQUIRES: key to be >= 0 and <= 26
    // EFFECTS: checks valid key input from user
    public static boolean validKey(int key) {
        return (key <= 26 && key >= 0);
    }
}