package ui;

import model.CaesarCipher;
import model.CryptographyOperation;
import model.CryptographyOperationsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
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
        loadPreviousCryptographyOpsButton.setEnabled(true);

        setVisible(true);
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

        cryptographyInput.setPreferredSize(unifiedDimension);
        keyInput.setPreferredSize(unifiedDimension);
        scrollPane.setPreferredSize(scrollPaneDimension);


        encryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        });

        saveOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                } catch (FileNotFoundException fileNotFoundException) {
                    infoBox("Please enter a key between [1 -26]", "Invalid Key!");
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

        loadPreviousCryptographyOpsButton.addActionListener(new ActionListener() {
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


        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init();
            }
        });
    }

    public void init() {

        ImageIcon icon = new ImageIcon("res/CaesarCipher.png");
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(300, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back

//        JLabel.set  //setHorizontalAlignment(JLabel.LEFT);
//        JLabel.setVerticalAlignment(JLabel.CENTER);
        JOptionPane.showMessageDialog(this, "<html><body><p style='width: 200px;'>"
                        + "In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift cipher, "
                        + "Caesar's code or Caesar shift, is one of the simplest and most widely known encryption techniques. "
                        + "It is a type of substitution cipher in which each letter in the plaintext is replaced by a letter "
                        + "some fixed number of positions down the alphabet. For example, with a left shift of 3, D would be "
                        + "replaced by A, E would become B, and so on. The method is named after Julius Caesar, who used it in "
                        + "his private correspondence" + "</p></body></html>", "What is the Caesar Cipher?",
                JOptionPane.INFORMATION_MESSAGE, icon);

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

    public static boolean validKey(int key) throws Exception {
        if (key <= 26 && key >= 0) {
            return true;
        } else {
            throw new Exception();
        }
    }

    public void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(this, infoMessage,
                "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

