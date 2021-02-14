package model;

public class CaesarCipher {


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static String encryptCipher(String plainText, int key) {

        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = plainText.length();

        if (validKey(key)) {
            for (int j = 0; j < lengthMsg; j++) {
                char msgToChar = plainText.charAt(j);

                if (msgToChar >= 'a' && msgToChar <= 'z') {
                    if (Character.isLetter(msgToChar)) {
                        msgToChar = (char) (plainText.charAt(j) + key);
                        if (msgToChar > 'z') {
                            msgToChar = (char) (plainText.charAt(j) - (26 - key));
                        }
                    }
                }
                if (msgToChar >= 'A' && msgToChar <= 'Z') {
                    if (Character.isLetter(msgToChar)) {
                        msgToChar = (char) (plainText.charAt(j) + key);

                        if (msgToChar > 'Z') {
                            msgToChar = (char) (plainText.charAt(j) - (26 - key));
                        }
                    }
                }
                buildEncryptedMessage.append(msgToChar);
            }
            return buildEncryptedMessage.toString();
        } else {
            System.out.println("invalid key. Please enter a key between 1 and 26");
            return "";
        }
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static String decryptCipher(String cipherText, int key) {
        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = cipherText.length();
        if (validKey(key)) {
            for (int j = 0; j < lengthMsg; j++) {
                char msgToChar = cipherText.charAt(j);

                int i = cipherText.charAt(j) + (26 - key);
                if (msgToChar >= 'a' && msgToChar <= 'z') {
                    if (Character.isLetter(msgToChar)) {
                        msgToChar = (char) (cipherText.charAt(j) - key);
                        if (msgToChar < 'a') {
                            msgToChar = (char) i;
                        }
                    }
                }
                if (msgToChar >= 'A' && msgToChar <= 'Z') {
                    if (Character.isLetter(msgToChar)) {
                        msgToChar = (char) (cipherText.charAt(j) - key);
                        if (msgToChar < 'A') {
                            msgToChar = (char) i;
                        }
                    }
                }
                buildEncryptedMessage.append(msgToChar);
            }
            return buildEncryptedMessage.toString();
        } else {
            System.out.println("invalid key. Please enter a key between 1 and 26");
            return "";
        }
    }


    public static boolean validKey(int key) {
        return (key <= 26 && key >= 0);
    }
}