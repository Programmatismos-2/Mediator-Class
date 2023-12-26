package com.freddo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Chatbot extends JFrame {
    JTextArea chatArea;
    JTextField input;
    List<String> conversation;
    //Φτιάχνω αντικείμενο της κλάσης που φέρνει μηνύματα από το AI

    public Chatbot(String names, String fli) {
        setTitle("Chatbot App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setIconImage(logo.getImage());

        chatArea = new JTextArea(15, 20);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        String botResponse = "Chatbot: Γεια σου " + names;
        conversation.add(botResponse);
        updateChatArea();

        input = new JTextField(20);
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserInput();
            }
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserInput();
            }
        });

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(input, BorderLayout.CENTER);
        inputPanel.add(okButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    input.setEditable(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    input.setEditable(true);
                }
            }
        });

        conversation = new ArrayList<>();
    }

    private void processUserInput() {
        String userMessage = input.getText();
        conversation.add("You: " + userMessage);
        updateChatArea();

        answer = SaveUsersHistory.SaveEmailAndQuestion(fli, userMessage);
        String botResponse = "Chatbot: Ναι φυσικά. " + answer + " Αν θέλεις, κάνε μια καινούργια ερώτηση.";
        conversation.add(botResponse);
        updateChatArea();

        input.setText("");
    }

    private void updateChatArea() {
        chatArea.setText("");
        for (String message : conversation) {
            chatArea.append(message + "\n");
        }
    }
}
