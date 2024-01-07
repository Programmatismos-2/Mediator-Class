package com.freddo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SignInPage {
    public String openSignInWindow() {
        JFrame signinFrame = new JFrame("Sign In - Σύνδεση");
        //Δημιουργία ενός νέου παραθύρου με τον τίτλο "Sign In - Σύνδεση"
        signinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);     // Κλείστε αυτό το παράθυρο όταν κλείσετε το chatbot
        signinFrame.setSize(600, 400);
        //Ορισμός του μεγέθους του παραθύρου
        signinFrame.setVisible(true);  //Κάνει ορατό το νέο παράθυρο
        signinFrame.setIconImage(logo.getImage());
        //Ορισμός του εικονιδίου του παραθύρου χρησιμοποιώντας την εικόνα από το αντικείμενο logo
        signinFrame.setLayout(null);
        //Ορισμός του layout manager του παραθύρου σε null
        
        JTextField surnameF2 = new JTextField();
        JTextField flightF2 = new JTextField();
        //Δημιουργία πεδίου κειμένου για επώνυμο και αριθμό πτήσης
        JLabel surnameL2 = new JLabel("Surname");
        JLabel flightL2 = new JLabel("Flight Number");
        //Δημιουργία ετικέτας για το επώνυμο και αριθμό πτήσης

        surnameF2.setBounds(200, 140, 200, 30);
        flightF2.setBounds(200, 200, 200, 30);
        //Ορίζει τη θέση και τις διαστάσεις των πεδίων κειμένου στο παράθυρο
        surnameL2.setBounds(50, 140, 200, 30);
        flightL2.setBounds(50, 200, 200, 30);
        // Ορίζει τη θέση και τις διαστάσεις των ετικετών

        signinFrame.add(surnameF2);
        signinFrame.add(flightF2);
        signinFrame.add(surnameL2);
        signinFrame.add(flightL2);
        // Προσθέτει τα πεδία κειμένου και τις ετικέτες στο παράθυρο

        JButton submit1Button = new JButton("Submit");
        //Δημιουργία κουμπιού με το κείμενο "Submit"
        submit1Button.setBounds(450,300, 100, 30);
        //Ορίζει τη θέση και τις διαστάσεις του κουμπιού
        submit1Button.setBorder(BorderFactory.createEtchedBorder());
        signinFrame.add(submit1Button);
        //Προσθέτει το κουμπί στο παράθυρο

        submit1Button.addActionListener(new ActionListener() {
            //Ορίζεται ένας ActionListener για το κουμπί submit1Button
            //Ο ActionListener ενεργοποιείται όταν ο χρήστης πατήσει το κουμπί
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cont = FlightChecker.checkFlightExistence(surnameF2.getText(), flightF2.getText());    //Καλεί την μέθοδο του Mediator για αναζήτηση δεδομένων
                //Το αποτέλεσμα αποθηκεύεται στη μεταβλητή cont
                if (cont) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Chatbot(surnameF2.getText(), flightF2.getText()).setVisible(true);
                        //Δημιουργία νέου παραθύρου Chatbot με τις παραμέτρους surnameF2.getText() και flightF2.getText() και το καθιστά ορατό
                        }
                    });
                    //Αν υπάρχουν δεδομένα πτήσης εκτελεί τον κώδικα μέσα στο if
                } else {
                    int ans = JOptionPane.showConfirmDialog(null, "Δεν είστε εγγεγραμμένος. Παρακαλώ εγγραφείτε", "Πρόβλημα Σύνδεσης", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    //Αν δεν υπάρχουν δεδομένα πτήσης εμφανίζει ένα παράθυρο με μήνυμα λάθους
                    if (ans == 0 || ans == -1) {
                        CheckInPage chpage = new CheckInPage();
                        //Δημιουργία νέου παραθύρου
                        chpage.opencheckWindow();
                    }
                }
                signinFrame.dispose();
                //Κλείσιμο του παραθύρου signinFrame
            }
        });
    }
}
