//Karolien Koorts


import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

public class GUI extends JFrame {
    String key_value, message;
    Encrypt encryption;
    boolean encrypt;

    Container cp = getContentPane();
    
	public GUI() {
        setTitle("Vigenère Cipher");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final JTextArea processed = new JTextArea();
        processed.setEditable(false);
        processed.setLineWrap(true);
        final JTextArea unprocessed = new JTextArea();
        unprocessed.setLineWrap(true);
        
        final JTextField key = new JTextField();
        final JButton encryptButton = new JButton("Encrypt");
        encryptButton.setToolTipText("Click to encrypt message");
        final JButton decryptButton = new JButton("Decrypt");
        decryptButton.setToolTipText("Click to decrypt message");


        JScrollPane scrollPane = new JScrollPane(processed);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        cp.add(scrollPane, BorderLayout.EAST);

        JScrollPane scrollPane2 = new JScrollPane(unprocessed);
        scrollPane2.setPreferredSize(new Dimension(600, 600));
        cp.add(scrollPane2, BorderLayout.WEST);
        
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new JLabel("Key: ", JLabel.RIGHT));
        topPanel.add(key);
        

        //~~~~~~~~~~~~~~~~~~~~~~ENCRYPT BUTTON~~~~~~~~~~~~~~
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	encrypt = true;
            	processed.setText(null);
            	key_value = key.getText();
            	message = unprocessed.getText();
                encryption = new Encrypt(processed, key_value, message, encrypt);
                encryption.execute();
            }
        });
        bottomPanel.add(encryptButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        
        
        //~~~~~~~~~~~~~~~~~~~~~~DECRYPT BUTTON~~~~~~~~~~~~~~
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encrypt = false;
                processed.setText(null);
            	key_value = key.getText();
            	message = unprocessed.getText();
                encryption = new Encrypt(processed, key_value, message, encrypt);
                encryption.execute();
            }
        });
        bottomPanel.add(decryptButton);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        
        cp.add(topPanel, BorderLayout.NORTH);
        cp.add(bottomPanel, BorderLayout.SOUTH);

        pack();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
	
}
