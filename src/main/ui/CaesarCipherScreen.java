package ui;

import model.CaesarCipher;
import model.CryptographyOperation;
import model.CryptographyOperationsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class CaesarCipherScreen extends JFrame {
    private JButton encryptionButton;
    private JButton saveOperationButton;
    private JButton removeFromFileButton;
    private JButton decryptButton;
    private JButton loadPreviousCryptographyOps;
    private JTextField cryptographyInput;
    private JPanel panelMain;
    private JTextField keyInput;
    private JList cryptographyOperationsJList;
    private CryptographyOperationsList cryptographyOperationsList;
    private DefaultListModel defaultListCryptOps;
    private static JsonWriter writer = new JsonWriter("./data/CaesarCipher.txt");
    private static JsonReader jsonReader = new JsonReader("./data/CaesarCipher.txt");
    private int selectedCryptographyOperationIndex;


    public CaesarCipherScreen() {
        super("Caesar Cipher");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        cryptographyOperationsList = new CryptographyOperationsList();

        // links the screen list with cryptographyList
        defaultListCryptOps = new DefaultListModel();
        cryptographyOperationsJList.setModel(defaultListCryptOps);

        removeFromFileButton.setEnabled(false);
        saveOperationButton.setEnabled(false);
        encryptionButton.setEnabled(true);
        decryptButton.setEnabled(true);
        loadPreviousCryptographyOps.setEnabled(true);

        setVisible(true);

        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int encryptionKey = Integer.parseInt(keyInput.getText());
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
// KEY VALIDITY
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int decryptionKey = Integer.parseInt(keyInput.getText());
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
        });

        saveOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                writer.write(cryptographyOperationsList);
                writer.close();

                removeFromFileButton.setEnabled(true);
            }
        });

        removeFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        loadPreviousCryptographyOps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cryptographyOperationsList = jsonReader.read();
                    refreshCryptographyOperationsList();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cryptographyOperationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = cryptographyOperationsJList.getSelectedIndex();
                if (index != -1) {
                    selectedCryptographyOperationIndex = index;
                }
            }
        });

    }

    public void refreshCryptographyOperationsList() {
        defaultListCryptOps.removeAllElements();
        String operationDescription = null;

        for (CryptographyOperation cryptographyOperation : cryptographyOperationsList.getCryptographyOperations()) {
            if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Encryption")) {
                operationDescription = "NEW ENCRYPTION OPERATION: " + "'" + cryptographyOperation.getPlaintext() + "'"
                        + " with the key " + "'" + cryptographyOperation.getKey() + "'"
                        + " your cipher text is >>> " + cryptographyOperation.getCiphertext()
                        + " with the specific ID of: " + cryptographyOperation.getId();
                defaultListCryptOps.addElement(operationDescription);
            } else if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Decryption")) {
                operationDescription = "NEW DECRYPTION OPERATION: " + "'" + cryptographyOperation.getCiphertext() + "'"
                        + " with the key " + "'" + cryptographyOperation.getKey() + "'"
                        + " your plain text is >>> " + cryptographyOperation.getPlaintext()
                        + " with the specific ID of: " + cryptographyOperation.getId();
                defaultListCryptOps.addElement(operationDescription);
            }
        }
        if (cryptographyOperationsList.getCryptographyOperations().size() >= 1) {
            saveOperationButton.setEnabled(true);
        }
    }

}
