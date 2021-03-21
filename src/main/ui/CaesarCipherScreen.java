package ui;

import model.CaesarCipher;
import model.CryptographyOperation;
import model.CryptographyOperationsList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CaesarCipherScreen extends JFrame {
    private JTextField cryptographyInput;
    private JButton encryptionButton;
    private JButton saveOperationButton;
    private JButton removeFromFileButton;
    private JButton seePreviousEncryptionOperationsButton;
    private JButton seePreviousDecryptionOperationsButton;
    private JPanel panelMain;
    private JTextField keyInput;
    private JList cryptographyOperationsJList;
    private JButton decryptButton;
    private CryptographyOperationsList cryptographyOperationsList;
    private DefaultListModel defaultListCryptOps;


    public CaesarCipherScreen() {
        super("Caesar Cipher");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        cryptographyOperationsList = new CryptographyOperationsList();

        // link the screen list with cryptographyList
        defaultListCryptOps = new DefaultListModel();
        cryptographyOperationsJList.setModel(defaultListCryptOps);

        // setting buttons to be disabled at the start until cryptographyOps are saved
        // set them to be true in the operation performed
        removeFromFileButton.setEnabled(false);
        saveOperationButton.setEnabled(false);
        encryptionButton.setEnabled(true);
        decryptButton.setEnabled(true);

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

            }
        });

        removeFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("These are all your entries, with their specific ID number.\n");
//                printModifiedEncryptionOperations();
//                printModifiedDecryptionOperations();
//                System.out.println("Type the ID of the entry you would like to remove: \n");
//                int userIDInput = scanner.nextInt();
//                cryptographyOperationsList.removeOperation(userIDInput);
//                System.out.println("Entry with the ID " + userIDInput + " has been deleted from file\n\n");

            }
        });

        seePreviousEncryptionOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        seePreviousDecryptionOperationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cryptographyOperationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        cryptographyOperationsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }

    public void refreshCryptographyOperationsList() {
        defaultListCryptOps.removeAllElements();
        String operationDescription = null;

        for (CryptographyOperation cryptographyOperation : cryptographyOperationsList.getCryptographyOperations()) {
            if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Encryption")) {
                operationDescription = "The text you entered is " + cryptographyOperation.getPlaintext()
                        + " with the key " + cryptographyOperation.getKey()
                        + " your cipher text is >>> " + cryptographyOperation.getCiphertext();
                defaultListCryptOps.addElement(operationDescription);
            } else if (cryptographyOperation.getType().equalsIgnoreCase("Caesar Cipher Decryption")) {
                operationDescription = "The text you entered is " + cryptographyOperation.getCiphertext()
                        + " with the key " + cryptographyOperation.getKey()
                        + " your cipher text is >>> " + cryptographyOperation.getPlaintext();
                defaultListCryptOps.addElement(operationDescription);
            }
        }
    }

//    public void printModifiedEncryptionOperations() {
//        System.out.println("\nEncryption Operations currently recorded: ");
//        if (cryptographyOperationsList.getEncryptionOperations().size() == 0) {
//            System.out.println("\nNo Encryption operations recorded\n");
//            System.out.print("\n-----------------------------------------------------------------\n");
//        } else {
//            for (CryptographyOperation encryptionOperation : cryptographyOperationsList.getEncryptionOperations()) {
//                System.out.println(encryptionOperation.getType()
//                        + ": You entered the cipher text: "
//                        + encryptionOperation.getPlaintext()
//                        + " that was given the ID: "
//                        + encryptionOperation.getId());
//            }
//        }
//    }


// user types in value, button, get value

}
