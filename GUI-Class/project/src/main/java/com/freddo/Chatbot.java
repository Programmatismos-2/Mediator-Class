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
        //Ορισμός βασικών χαρακτηριστικών παραθύρου(τίτλος, κλείσιμο κατά το κλικ στο "X", μέγεθος, ορατότητα)

        chatArea = new JTextArea(15, 20);
        //Δημιουργία νέου αντικειμένου JTextArea με διαστάσεις 15 γραμμές και 20 στήλες
        chatArea.setEditable(false);
        //Ορισμός ικανότητας επεξεργασίας
        JScrollPane scrollPane = new JScrollPane(chatArea);
        //Δημιουργία JScrollPane για υποστήριξη κύλισης

        String botResponse = "Chatbot: Γεια σου " + names;
        //Δημιουργία μηνύματος χαιρετισμού από το Chatbot
        conversation.add(botResponse);
        //Προσθήκη μηνύματος στη λίστα συνομιλίας
        updateChatArea();
        //Κλήση μεθόδου updateChatArea

        input = new JTextField(20);
        //Δημιουργία πεδίου κειμένου JTextField με μέγεθος 20 χαρακτήρες
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserInput();
            }
        });
        //Προσθήκη ακροατή συμβάντος ActionListener που καλεί τη μέθοδο processUserInput

        JButton okButton = new JButton("OK");
        //Δημιουργία κουμπιού JButton με το κείμενο "OK"
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processUserInput();
            }
        });
        //Προσθήκη ακροατή συμβάντος ActionListener που καλεί τη μέθοδο processUserInput

        setLayout(new BorderLayout());
        //Ορισμός τύπου διάταξης του παραθύρου ως BorderLayout

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        //Δημιουργία νέου JPanel με διάταξη BorderLayout
        inputPanel.add(input, BorderLayout.CENTER);
        //Το input τοποθετείται στο κέντρο του πάνελ.
        inputPanel.add(okButton, BorderLayout.EAST);
        //Το κουμπί okButton τοποθετείται στην ανατολική πλευρά του πάνελ

        add(scrollPane, BorderLayout.CENTER);
        //Το scrollPane τοποθετείται στο κέντρο του παραθύρου
        add(inputPanel, BorderLayout.SOUTH);
        //Το inputPanel τοποθετείται στο νότιο μέρος του παραθύρου

        input.addKeyListener(new KeyListener() {
            //Δημιουργία KeyListener για το πεδίο κειμένου εισόδου
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    input.setEditable(false);
                }
            }
            //Όταν πατηθεί το πλήκτρο Enter το πεδίο κειμένου καθίσταται μη επεξεργάσιμο

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    input.setEditable(true);
                }
            }
            //Όταν απελευθερωθεί το πλήκτρο Enter το πεδίο κειμένου καθίσταται πάλι επεξεργάσιμο
        });

        conversation = new ArrayList<>();
        //Δημιουργία νέας αρχικής λίστας για αποθήκευση της συνομιλίας
    }

    private void processUserInput() {
        String userMessage = input.getText();
        //Παίρνει το κείμενο από το πεδίο εισαγωγής και το αποθηκεύει σε μεταβλητή userMessage.
        conversation.add("You: " + userMessage);
        //Προσθήκη στη λίστα conversation μηνύματος που δείχνει το μήνυμα που εισήγαγε ο χρήστης, με το πρόθεμα "You: "
        updateChatArea();
        //Ενημέρωση εμφάνισης συνομιλίας

        answer = SaveUsersHistory.SaveEmailAndQuestion(fli, userMessage);
        //Αποθήκευση email του χρήστη και ερωτήματος
        String botResponse = "Chatbot: Ναι φυσικά. " + answer + " Αν θέλεις, κάνε μια καινούργια ερώτηση.";
        //Δημιουργία μηνύματος απάντησης
        conversation.add(botResponse);
        //Προσθήκη στη λίστα conversation
        updateChatArea();
        //Ενημέρωση εμφάνισης συνομιλίας

        input.setText("");
        //Καθαρισμός πεδίου εισαγωγής 
    }

    private void updateChatArea() {
        chatArea.setText("");
        //Καθαρισμός εμφάνισης συνομιλίας 
        for (String message : conversation) {
            chatArea.append(message + "\n");
        }
        //Προσθήκη κάθε μηνύματος από τη λίστα conversation σε νέα γραμμή
    }
}
