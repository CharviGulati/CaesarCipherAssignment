package ui;

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


    public CaesarCipherScreen() {
        super("Caesar Cipher");
        setCaesarCipherDefaultUiOptions();

        encryptionButton.addActionListener(e -> encryptionButtonClick(e));

        decryptButton.addActionListener(e -> decryptionButtonClick(e));

        saveOperationButton.addActionListener(e -> setSaveOperationButtonClick(e));

        removeFromFileButton.addActionListener(e -> removeButtonClick(e));

        loadPreviousCryptographyOpsButton.addActionListener(e -> loadPreviousButtonClick(e));

        cryptographyOperationsJList.addListSelectionListener(e -> handleListSelectionEvent());

        aboutButton.addActionListener(e -> aboutMessage());
    }

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

    private void handleListSelectionEvent() {
        int index = cryptographyOperationsJList.getSelectedIndex();
        if (index != -1) {
            selectedCryptographyOperationIndex = index;
        }
    }

    private void encryptionButtonClick(ActionEvent e) {
        int encryptionKey = Integer.parseInt(keyInput.getText());
        try {
            validKey(encryptionKey);
        } catch (Exception exception) {
            infoBox("Please enter a key between [1 -26]", "Invalid Key!");
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

    private void decryptionButtonClick(ActionEvent e) {
        int decryptionKey = Integer.parseInt(keyInput.getText());

        try {
            validKey(decryptionKey);
        } catch (Exception exception) {
            infoBox("Please enter a key between [1 -26]", "Invalid Key!");
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

    private void setSaveOperationButtonClick(ActionEvent e) {
        if (sureYouWantToSavePopUp() == 0) {
            try {
                writer.open();
            } catch (FileNotFoundException fileNotFoundException) {
                infoBox("Please enter a key between [1 -26]", "Invalid Key!");
            }
            writer.write(cryptographyOperationsList);
            writer.close();
            removeFromFileButton.setEnabled(true);
            popIpBoxForSavedOps();
        }
    }

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

    private void loadPreviousButtonClick(ActionEvent e) {
        try {
            cryptographyOperationsList = jsonReader.read();
            refreshCryptographyOperationsList();
            removeFromFileButton.setEnabled(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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

    private static boolean validKey(int key) throws Exception {
        if (key <= 26 && key >= 0) {
            return true;
        } else {
            throw new Exception();
        }
    }

    private void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(this, infoMessage,
                "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private void popIpBoxForSavedOps() {
        JOptionPane.showMessageDialog(this, "Your operations have been saved!",
                "" + "SAVED!", JOptionPane.INFORMATION_MESSAGE);
    }

    private Integer sureYouWantToSavePopUp() {
        getContentPane().setLayout(null);

        ImageIcon icon = new ImageIcon("res/CaesarCipher.png");
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        int input = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to overwrite the previously saved file with the "
                        + "new operations?", "Save Option",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        // 0=ok, 2=cancel
        return input;

    }
}


