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
        //Δημιουργία παραθύρου με τίτλο "Check-In"
        checkFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Κλείστε αυτό το παράθυρο όταν κλείσετε την επιλογή κρατήσεων
        checkFrame.setSize(600, 400);
        //Ορισμός τοy μeγέθους του παραθύρου
        checkFrame.setVisible(true);
        //Κάνει ορατό το νέο παράθυρο
        checkFrame.setLayout(null);
        //Χρήση null layout -τα στοιχεία τοποθετούνται με συγκεκριμένες συντεταγμένες
        checkFrame.setIconImage(logo.getImage());


        JTextField nameF1 = new JTextField();
        JTextField surnameF1 = new JTextField();
        JTextField flightF1 = new JTextField();
        JTextField emailF1 = new JTextField();
        //Δημιουργία τεσσάρων πεδίων κειμένου
        
        JLabel nameL1 = new JLabel("Name"); 
        JLabel surnameL1 = new JLabel("Surname");
        JLabel flightL1 = new JLabel("Flight Number");
        JLabel emailL1 = new JLabel("Email");
        //Δημιουργία τεσσάρων ετικετών 

        nameL1.setBounds(50, 50, 100, 30);
        surnameL1.setBounds(50, 110, 100, 30);
        flightL1.setBounds(50, 170, 100, 30);
        emailL1.setBounds(50, 230, 100, 30);

        nameF1.setBounds(200, 50, 200, 30);
        surnameF1.setBounds(200, 110, 200, 30);
        flightF1.setBounds(200, 170, 200, 30);
        emailF1.setBounds(200, 230, 200, 30);
        //Κάθε ετικέτα και πεδίο κειμένου τοποθετούνται σε συγκεκριμένες θέσεις στο παράθυρο
        
        checkFrame.add(nameL1);
        checkFrame.add(surnameL1);
        checkFrame.add(flightL1);
        checkFrame.add(emailL1);

        checkFrame.add(nameF1);
        checkFrame.add(surnameF1);
        checkFrame.add(flightF1);
        checkFrame.add(emailF1);
        //Προσθήκη τεσσάρων ετικετών και πεδίων κειμένου

        JButton submitButton = new JButton("Submit");
        //Προσθήκη κουμπιού JButton με το κείμενο Submit
        submitButton.setBounds(450,300, 100, 30);
        //Ρύθμιση θέσης και διαστάσεων κουμπιού
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        //Ρύθμιση περιγράμματος κουμπιού
        checkFrame.add(submitButton);
        //Προσθήκη κουμπιού στο παράθυρο

        submitButton.addActionListener(new ActionListener() {
            //Ορισμός ActionListener
            @Override
            public void actionPerformed(ActionEvent e) {
                //Όταν το κουμπί πατηθεί, ο κώδικας που βρίσκεται μέσα στη μέθοδο actionPerformed θα εκτελεστεί
                if (FlightChecker.checkFlightExistence(surnameF1.getText(), flightF1.getText()) == false) {
                    SaveUsersCredentials.SaveStringToCSV(nameF1.getText(), surnameF1.getText(), flightF1.getText(), emailF1.getText());    //Καλεί την μέθοδο του Mediator για εισαγωγή δεδομένων
                //Αν η πτήση δεν υπάρχει, τότε καλείται η μέθοδος SaveStringToCSV για να αποθηκευθούν τα δεδομένα του χρήστη σε ένα αρχείο CSV
                } else {
                    JLabel alr = new JLabel("Είστε ήδη εγκεγραμμένος");
                    alr.setBounds(200, 250, 200, 30);
                    checkFrame.add(alr);
                }
                //Αν η πτήση υπάρχει, προστίθεται μήνυμα που ενημερώνει τον χρήστη ότι είναι ήδη εγγεγραμμένος
                checkFrame.dispose();
                //Το παράθυρο checkFrame κλείνει
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Chatbot(surnameF1.getText(), flightF1.getText()).setVisible(true);
                    }
                    //Δημιουργία ένα νέου παραθύρου Chatbot που εμφανίζει τον διάλογο με τον chatbot
                });
            }

            
        });
    }
}
