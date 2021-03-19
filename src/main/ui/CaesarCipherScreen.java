package ui;

import model.CryptographyOperation;
import model.CryptographyOperationsList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class CaesarCipherScreen extends JFrame {
    private JTextField textField1;
    private JButton goButton;
    private JButton saveOperationButton;
    private JButton removeFromFileButton;
    private JButton seePreviousEncryptionOperationsButton;
    private JButton seePreviousDecryptionOperationsButton;
    private JPanel panelMain;
    private JTextField textField2;
    private JList cryptographyOperationsJList;
    private ArrayList<CryptographyOperation> cryptographyOperations = new ArrayList<>();
    private DefaultListModel listCrypOps;


    public CaesarCipherScreen() {
        super("Caesar Cipher");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        cryptographyOperations.add(new CryptographyOperation("1", new Date(),
                "2",
                "3",
                5,
                7));
        listCrypOps = new DefaultListModel();
        cryptographyOperationsJList.setModel(listCrypOps);
        listCrypOps.addElement(cryptographyOperations.get(0).getCiphertext());

        setVisible(true);

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
    }




}
