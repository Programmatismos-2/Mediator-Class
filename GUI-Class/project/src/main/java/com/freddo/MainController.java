package com.freddo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    List<String> conversation = new ArrayList<>();
    String fli;
    @FXML
    private TextField textUser;
    @FXML
    private TextField surnameS;
    @FXML
    private TextField flightS;
    @FXML
    private TextField surnameC;
    @FXML
    private TextField nameC;
    @FXML
    private TextField flightC;
    @FXML
    private TextField emailC;
    @FXML
    TextArea chatArea;
    
    @FXML
    void sumbitSignIn(ActionEvent event) {
        boolean cont = FlightChecker.checkFlightExistence(surnameS.getText(), flightS.getText());
        //Ελέγχει αν υπάρχει η πτήση
        if (cont) {
            //Αν η πτήση υπάρχει εκτελείται ο κωδικας του if
            fli = flightS.getText();
            try {
                root = FXMLLoader.load(getClass().getResource("src\\resources\\ChatbotScene.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                //Φόρτωση νέας σκηνής
                //Πραγματοποιήση της εναλλαγής στη νέα σκηνή
                textUser.setOnKeyPressed(event1 -> {
                    if (event1.getCode() == KeyCode.ENTER) {
                        try {
                            processUserInput();
                        } catch (IOException e) {
                            
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                        
                            e.printStackTrace();
                        }
                    }
                    //Ορίζεται listener για το πληκτρολόγιο
                    //Όταν το πλήκτρο πατηθεί καλείται η μέθοδος
                });
            } catch (Exception e) {
            }
        } else {
            //Αν η πτήση δεν υπάρχει εκτελείται ο κωδικας του else
            Alert alert = new Alert(AlertType.ERROR);
            //Δημιουργία αντικειμένου Alert με τύπο ERROR για εμφάνιση μηνύματος σφάλματος
            alert.setTitle("User Not Checked In");
            //Ορισμός του τίτλου παραθύρου ειδοποίησης
            alert.setContentText("You have not checked in. Please do so.");
            //Ορισμός του περιεχομένου παραθύρου ειδοποίησης
            Optional<ButtonType> res = alert.showAndWait();
            //Εμφάνιση παραθύρου ειδοποίησης 
            //Αναμονή μέχρι ο χρήστης να το κλείσει
            res.get();
            try {
                root = FXMLLoader.load(getClass().getResource("src\\resources\\CheckInScene.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                //Φόρτωση νέας σκηνής
                //Πραγματοποιήση της εναλλαγής στη νέα σκηνή
            } catch (Exception e) {
            }    
        }
    }
    @FXML
    void sumbitCheckIn(ActionEvent event) {
        boolean cont = FlightChecker.checkFlightExistence(surnameS.getText(), flightS.getText());
        //Έλεγχος αν ο χρήστης έχει ήδη κάνει check-in με βάση το επώνυμο και τον αριθμό πτήσης 
        if (cont) {
            //Αν ο χρήστης έχει ήδη κάνει check-in εκτελείται ο κωδικας του if
            Alert alert = new Alert(AlertType.INFORMATION);
            //Δημιουργία παραθύρου ειδοποίησης τύπου INFORMATION
            alert.setTitle("User Already Checked In");
            alert.setContentText("You are already signed in. Please continue.");
            Optional<ButtonType> res = alert.showAndWait();
            res.get();
            //Εμφάνιση μηνύματος που ενημερώνει τον χρήστη ότι έχει ήδη κάνει check-in

        } else {
            SaveUsersCredentials.SaveStringToCSV(nameC.getText(), surnameC.getText(), flightC.getText(), emailC.getText());
        //Κλήση της μεθόδου SaveStringToCSV της κλάσης SaveUsersCredentials
        //Αποθήκευση στοιχείων του χρήστη σε ένα αρχείο CSV
        }
        fli = flightC.getText();
        //Αποθήκευση κειμένου που εισήγαγε ο χρήστης
        try {    
            root = FXMLLoader.load(getClass().getResource("src\\resources\\ChatbotScene.fxml"));
            //Φόρτωση FXML αρχείου
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            //Δημιουργία νέου αντικειμένου σκηνής με τον root
            stage.setScene(scene);
            //Ορισμός νέας σκηνής στο παράθυρο
            stage.show();
            //Εμφάνιση παραθύρου με τη νέα σκηνή
            textUser.setOnKeyPressed(event1 -> {
                //Ορισμός KeyListener
                if (event1.getCode() == KeyCode.ENTER) {
                    try {
                        processUserInput();
                        //Αν το πλήκτρο πατήθηκε εκτελείται η μέθοδος 
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
        }
    }
    @FXML
    void signIn(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("src\\resources\\SignInScene.fxml"));
            //Φόρτωση FXML αρχείου που βρίσκεται στον κατάλογο src/resources/
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //Φόρτωση νέας σκηνής
            //Πραγματοποιήση της εναλλαγής στη νέα σκηνή
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void checkIn(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("src\\resources\\CheckInScene.fxml"));
            //Φόρτωση FXML αρχείου που βρίσκεται στον κατάλογο src/resources/
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //Φόρτωση νέας σκηνής
            //Πραγματοποιήση της εναλλαγής στη νέα σκηνή
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void processUserInput() throws IOException, InterruptedException {
        chatArea.setWrapText(true);
        //Το κείμενο αναδιπλώνεται σε νέες γραμμές αν υπερβαίνει το πλάτος της περιοχής
        String userMessage = textUser.getText();
        //Άντληση κειμένου που έχει εισαχθεί από τον χρήστη στο textUser και αποθήκευση στη μεταβλητή userMessage
        conversation.add("You: " + userMessage);
        //Προσθήκη μηνύματος χρήστη στη λίστα conversation με το πρόθεμα "You:"
        updateChatArea();
        //Ανανέωση εμφάνισης συνομιλίας
        String answer = SaveUsersHistory.SaveEmailAndQuestion(fli, userMessage);
        //Αποθήκευση απάντησης των αντίστοιχων μεταβλητών
        String botResponse = "Chatbot: Ναι φυσικά. " + answer + "Αν θέλεις, κάνε μια καινούργια ερώτηση.";
        //Δημιουργία αυτοματοποιημένου μηνύματος από το chatbot περιλαμβάνοντας την απάντηση που λήφθηκε από το ιστορικό
        conversation.add(botResponse);
        //Προσθήκη αυτοματοποιημένου μηνύματος του chatbot στη λίστα conversation
        updateChatArea();
        //Ανανεώνει ξανά την εμφάνιση της συνομιλίας
        textUser.clear();
        //Καθαριρσμός πεδίου κειμένου
    }

    private void updateChatArea() {
        chatArea.clear();
        //Καθαρίζει το περιεχόμενο του chatArea
        for (String message : conversation) {
            //Διατρέχει τη λίστα conversation που περιέχει όλα τα μηνύματα
            chatArea.appendText(message + "\n");
            //Κάθε μήνυμα προστίθεται στο chatArea
            //Τα μηνύματα διαχωρίζονται μέσω νέων γραμμών
        }
    }

}
