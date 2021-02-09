package model;

public class CaesarCipher {


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static String encryptCipher(String msg, int key) {

        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = msg.length();

        for (int j = 0; j < lengthMsg; j++) {

            // checks to see if the msg character is a letter
            // and only then adds it to the appended string
            char msgToChar = msg.charAt(j);

            // if it is a letter then it moves the character at index j using the key
            if (Character.isLetter(msgToChar)) {
                msgToChar = (char) (msg.charAt(j) + key);

//                // if the message character is Z then it loops back around the alphabet to A again
//                if (msgToChar > 'z') {
//                    msgToChar = (char) (msg.charAt(j) - (26 - key));
//                }
            }
            // builds a new list of the appended characters which is now the encrypted message
            buildEncryptedMessage.append(msgToChar);
        }
        // returns the encrypted character
        return buildEncryptedMessage.toString();
    }



    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public static String decryptCipher(String encrypted, int key) {

        StringBuilder buildEncryptedMessage = new StringBuilder();
        int lengthMsg = encrypted.length();

        for (int j = 0; j < lengthMsg; j++) {

            // checks to see if the msg character is a letter
            // and only then adds it to the appended string
            char msgToChar = encrypted.charAt(j);

            if (Character.isLetter(msgToChar)) {
                msgToChar = (char) (encrypted.charAt(j) - key);

//                if (msgToChar > 'z') {
//                    msgToChar = (char) (encrypted.charAt(j) + (26 + key));
//                }
            }
            buildEncryptedMessage.append(msgToChar);
        }
        return buildEncryptedMessage.toString();

    }
}