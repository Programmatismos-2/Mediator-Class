package com.freddo;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CheckInPage {
    
    public void opencheckWindow() {
        JFrame checkFrame = new JFrame("Check-In");
        checkFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // Κλείστε αυτό το παράθυρο όταν κλείσετε την επιλογή κρατήσεων
        checkFrame.setSize(600, 400);
        checkFrame.setVisible(true);    //Κάνει ορατό το νέο παράθυρο
        checkFrame.setLayout(null);
        checkFrame.setIconImage(logo.getImage());


        JTextField nameF1 = new JTextField();
        JTextField surnameF1 = new JTextField();
        JTextField flightF1 = new JTextField();
        JTextField emailF1 = new JTextField();
        
        JLabel nameL1 = new JLabel("Name"); 
        JLabel surnameL1 = new JLabel("Surname");
        JLabel flightL1 = new JLabel("Flight Number");
        JLabel emailL1 = new JLabel("Email");

        nameL1.setBounds(50, 50, 100, 30);
        surnameL1.setBounds(50, 110, 100, 30);
        flightL1.setBounds(50, 170, 100, 30);
        emailL1.setBounds(50, 230, 100, 30);

        nameF1.setBounds(200, 50, 200, 30);
        surnameF1.setBounds(200, 110, 200, 30);
        flightF1.setBounds(200, 170, 200, 30);
        emailF1.setBounds(200, 230, 200, 30);
        
        checkFrame.add(nameL1);
        checkFrame.add(surnameL1);
        checkFrame.add(flightL1);
        checkFrame.add(emailL1);

        checkFrame.add(nameF1);
        checkFrame.add(surnameF1);
        checkFrame.add(flightF1);
        checkFrame.add(emailF1);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(450,300, 100, 30);
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        checkFrame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FlightChecker.checkFlightExistence(surnameF1.getText(), flightF1.getText()) == false) {
                    SaveUsersCredentials.SaveStringToCSV(nameF1.getText(), surnameF1.getText(), flightF1.getText(), emailF1.getText());    //Καλεί την μέθοδο του Mediator για εισαγωγή δεδομένων
                } else {
                    JLabel alr = new JLabel("Είστε ήδη εγκεγραμμένος");
                    alr.setBounds(200, 250, 200, 30);
                    checkFrame.add(alr);
                }
                checkFrame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Chatbot(surnameF1.getText(), flightF1.getText()).setVisible(true);
                    }
                });
            }

            
        });
    }
}
