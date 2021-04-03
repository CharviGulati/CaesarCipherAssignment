package ui;

import exceptions.InvalidKeyException;
import model.CaesarCipher;
import model.CryptographyOperation;
import model.CryptographyOperationsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;


// CaesarCipherScreen class creates the GUI

public class CaesarCipherScreen extends JFrame {
    private JButton encryptionButton;
    private JButton saveOperationButton;
    private JButton removeFromFileButton;
    private JButton decryptButton;
    private JButton loadPreviousCryptographyOpsButton;
    private JButton aboutButton;
    private JTextField cryptographyInput;
    private JPanel panelMain;
    private JTextField keyInput;
    private JList cryptographyOperationsJList;
    private JScrollPane scrollPane;
    private CryptographyOperationsList cryptographyOperationsList;
    private DefaultListModel defaultListCryptOps;
    private static JsonWriter writer = new JsonWriter("./data/CaesarCipher.txt");
    private static JsonReader jsonReader = new JsonReader("./data/CaesarCipher.txt");
    private int selectedCryptographyOperationIndex;


    // EFFECTS: constructs main GUI for ceasar Cipher
    public CaesarCipherScreen() {
        super("Caesar Cipher");
        setCaesarCipherDefaultUiOptions();

        encryptionButton.addActionListener(e -> {
            try {
                encryptionButtonClick(e);
            } catch (InvalidKeyException invalidKeyException) {
                invalidKeyException.getMessage();
            }
        });

        decryptButton.addActionListener(e -> {
            try {
                decryptionButtonClick(e);
            } catch (InvalidKeyException invalidKeyException) {
                invalidKeyException.getMessage();
            }
        });

        saveOperationButton.addActionListener(e -> setSaveOperationButtonClick(e));

        removeFromFileButton.addActionListener(e -> removeButtonClick(e));

        loadPreviousCryptographyOpsButton.addActionListener(e -> loadPreviousButtonClick(e));

        cryptographyOperationsJList.addListSelectionListener(e -> handleListSelectionEvent());

        aboutButton.addActionListener(e -> aboutMessage());
    }

    // MODIFIES: this
    // EFFECTS: sets Caesar Cipher GUI default options
    private void setCaesarCipherDefaultUiOptions() {
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        cryptographyOperationsList = new CryptographyOperationsList();
        defaultListCryptOps = new DefaultListModel();
        cryptographyOperationsJList.setModel(defaultListCryptOps);
        removeFromFileButton.setEnabled(false);
        saveOperationButton.setEnabled(false);
        encryptionButton.setEnabled(true);
        decryptButton.setEnabled(true);
        loadPreviousCryptographyOpsButton.setEnabled(true);
        setVisible(true);
        setCaesarCipherUiDimensions();
    }

    // MODIFIES: this
    // EFFECTS: sets Caesar Cipher GUI default dimensions for buttons
    private void setCaesarCipherUiDimensions() {
        Dimension dimension = cryptographyOperationsJList.getPreferredSize();
        dimension.width = 200;
        dimension.height = 200;
        scrollPane.setPreferredSize(dimension);
        cryptographyOperationsJList.setVisibleRowCount(-1);

        Dimension unifiedDimension = new Dimension(20, 20);
        Dimension scrollPaneDimension = new Dimension(100, 300);

        encryptionButton.setPreferredSize(unifiedDimension);
        decryptButton.setPreferredSize(unifiedDimension);
        saveOperationButton.setPreferredSize(unifiedDimension);
        removeFromFileButton.setPreferredSize(unifiedDimension);
        loadPreviousCryptographyOpsButton.setPreferredSize(unifiedDimension);
        aboutButton.setPreferredSize(unifiedDimension);

        scrollPane.setPreferredSize(scrollPaneDimension);
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI list selection for remove option
    private void handleListSelectionEvent() {
        int index = cryptographyOperationsJList.getSelectedIndex();
        if (index != -1) {
            selectedCryptographyOperationIndex = index;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI encryption option
    private void encryptionButtonClick(ActionEvent e) throws InvalidKeyException {
        int encryptionKey = Integer.parseInt(keyInput.getText());
        try {
            validKey(encryptionKey);
        } catch (InvalidKeyException exception) {
            infoBox("Invalid key. Please enter a key between [0-26]", "Invalid Key!");
        }
        String plaintext = cryptographyInput.getText();

        if (CaesarCipher.validKey(encryptionKey)) {
            String ciphertext = CaesarCipher.encryptCipher(plaintext, encryptionKey);
            int randomId = (int) (Math.random() * 10000000 + 1);

            CryptographyOperation cryptographyOperation = new CryptographyOperation(
                    "Caesar Cipher Encryption",
                    new Date(),
                    ciphertext,
                    plaintext,
                    encryptionKey,
                    randomId);
            cryptographyOperationsList.addOperation(cryptographyOperation);
            refreshCryptographyOperationsList();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI decryption option
    private void decryptionButtonClick(ActionEvent e) throws InvalidKeyException {
        int decryptionKey = Integer.parseInt(keyInput.getText());

        try {
            validKey(decryptionKey);
        } catch (InvalidKeyException exception) {
            infoBox("Invalid key. Please enter a key between [0-26]", "Invalid Key!");
        }

        String ciphertext = cryptographyInput.getText();

        if (CaesarCipher.validKey(decryptionKey)) {
            String plaintext = CaesarCipher.decryptCipher(ciphertext, decryptionKey);

            int randomId = (int) (Math.random() * 10000000 + 1);

            CryptographyOperation cryptographyOperation = new CryptographyOperation(
                    "Caesar Cipher Decryption",
                    new Date(),
                    ciphertext,
                    plaintext,
                    decryptionKey,
                    randomId);
            cryptographyOperationsList.addOperation(cryptographyOperation);
            refreshCryptographyOperationsList();

        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI save option
    private void setSaveOperationButtonClick(ActionEvent e) {
        if (sureYouWantToSavePopUp() == 0) {
            try {
                writer.open();
            } catch (FileNotFoundException fileNotFoundException) {
                infoBox("Please enter a key between [0 -26]", "Invalid Key!");
            }
            writer.write(cryptographyOperationsList);
            writer.close();
            removeFromFileButton.setEnabled(true);
            popIpBoxForSavedOps();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI remove option
    private void removeButtonClick(ActionEvent e) {
        if (selectedCryptographyOperationIndex >= 0) {
            cryptographyOperationsList.getCryptographyOperations().remove(selectedCryptographyOperationIndex);
            try {
                cryptographyOperationsList = jsonReader.read();
                cryptographyOperationsList.removeOperationByIndex(selectedCryptographyOperationIndex);
                refreshCryptographyOperationsList();

                writer.open();
                writer.write(cryptographyOperationsList);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            refreshCryptographyOperationsList();
            if (cryptographyOperationsList.getCryptographyOperations().size() == 0) {
                removeFromFileButton.setEnabled(false);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI loading previous cryptography operations option
    private void loadPreviousButtonClick(ActionEvent e) {
        try {
            cryptographyOperationsList = jsonReader.read();
            refreshCryptographyOperationsList();
            removeFromFileButton.setEnabled(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles Caesar Cipher GUI about button option
    private void aboutMessage() {

        ImageIcon icon = new ImageIcon("res/CaesarCipher.png");
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        JOptionPane.showMessageDialog(this, "<html><body><p style='width: 200px;'>"
                        + "In cryptography, a Caesar cipher, also known "
                        + "the shift cipher. "
                        + "It is a very simple and widely known encryption technique. "
                        + "In this cipher each letter in plaintext is replaced by another letter depending "
                        + "on the key inout. The key determines the number of positions to move down in the "
                        + "alphabet. For example, with a key of 3, if you are encrypting a message, an A would be "
                        + "replaced by a D. If you are decrypting the letter A with the key 5 "
                        + "then the shift would be to the right and the encrypted letter would be V. "
                        + "The method is named after Julius Caesar, who used it in "
                        + "his private correspondence <br/><br/>-Source Wikipedia</p></body></html>",
                "What is the Caesar Cipher?",
                JOptionPane.INFORMATION_MESSAGE, icon);

    }

    // MODIFIES: this
    // EFFECTS: refreshes CryptographyOperationsList for the purpose of reloading data on screen
    private void refreshCryptographyOperationsList() {
        defaultListCryptOps.removeAllElements();
        String operationDescription = null;

        for (CryptographyOperation cryptographyOperation : cryptographyOperationsList.getCryptographyOperations()) {
            if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Encryption")) {
                operationDescription = "NEW ENCRYPTION OPERATION: You entered: " + "'"
                        + cryptographyOperation.getPlaintext() + "'"
                        + " with the key " + "'" + cryptographyOperation.getKey() + "'"
                        + " your cipher text is >>> " + " '" + cryptographyOperation.getCiphertext() + "' "
                        + " with the specific ID of: " + cryptographyOperation.getId();


                defaultListCryptOps.addElement(operationDescription);
            } else if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Decryption")) {
                operationDescription = "NEW DECRYPTION OPERATION: You entered: " + " '"
                        + cryptographyOperation.getCiphertext() + "' "
                        + " with the key " + "'" + cryptographyOperation.getKey() + "'"
                        + " your plain text is >>> " + " '" + cryptographyOperation.getPlaintext() + "' "
                        + " with the specific ID of: " + cryptographyOperation.getId();
                defaultListCryptOps.addElement(operationDescription);
            }
        }
        if (cryptographyOperationsList.getCryptographyOperations().size() >= 1) {
            saveOperationButton.setEnabled(true);
        }
    }

    // EFFECTS: validates user input key
    private static boolean validKey(int key) throws InvalidKeyException {
        if (key <= 26 && key >= 0) {
            return true;
        } else {
            throw new InvalidKeyException("Invalid Key. Please enter a key value between [0-26]");
        }
    }

    // EFFECTS: shows error message for invalid key input
    private void infoBox(String infoMessage, String titleBar) {
        ImageIcon icon = new ImageIcon("res/JuliusCaesar.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(130, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        JOptionPane.showMessageDialog(this, infoMessage,
                "" + titleBar, JOptionPane.INFORMATION_MESSAGE, icon);
    }


    // EFFECTS: shows message to user for saving their cryptography operations
    private void popIpBoxForSavedOps() {
        ImageIcon icon = new ImageIcon("res/JuliusCaesar.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(130, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        JOptionPane.showMessageDialog(this, "Your operations have been saved!",
                "" + "SAVED!", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    // EFFECTS: shows message to user for to make sure they want to save their cryptography operation
    private Integer sureYouWantToSavePopUp() {
        getContentPane().setLayout(null);

        ImageIcon icon = new ImageIcon("res/JuliusCaesar.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(130, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        int input = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to overwrite the previously saved file with the "
                        + "new operations?", "Save Option",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        // 0=ok, 2=cancel
        return input;

    }
}


