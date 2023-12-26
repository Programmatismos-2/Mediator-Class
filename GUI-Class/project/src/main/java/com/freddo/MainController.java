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
        if (cont) {
            fli = flightS.getText();
            try {
                root = FXMLLoader.load(getClass().getResource("src\\resources\\ChatbotScene.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
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
                });
            } catch (Exception e) {
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("User Not Checked In");
            alert.setContentText("You have not checked in. Please do so.");
            Optional<ButtonType> res = alert.showAndWait();
            res.get();
            try {
                root = FXMLLoader.load(getClass().getResource("src\\resources\\CheckInScene.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
            }    
        }
    }
    @FXML
    void sumbitCheckIn(ActionEvent event) {
        boolean cont = FlightChecker.checkFlightExistence(surnameS.getText(), flightS.getText());
        if (cont) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User Already Checked In");
            alert.setContentText("You are already signed in. Please continue.");
            Optional<ButtonType> res = alert.showAndWait();
            res.get();

        } else {
            SaveUsersCredentials.SaveStringToCSV(nameC.getText(), surnameC.getText(), flightC.getText(), emailC.getText());
        }
        fli = flightC.getText();
        try {    
            root = FXMLLoader.load(getClass().getResource("src\\resources\\ChatbotScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
            });
        } catch (IOException e) {
        }
    }
    @FXML
    void signIn(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("src\\resources\\SignInScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void checkIn(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("src\\resources\\CheckInScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void processUserInput() throws IOException, InterruptedException {
        chatArea.setWrapText(true);
        String userMessage = textUser.getText();
        conversation.add("You: " + userMessage);
        updateChatArea();
        String answer = SaveUsersHistory.SaveEmailAndQuestion(fli, userMessage);
        String botResponse = "Chatbot: Ναι φυσικά. " + answer + "Αν θέλεις, κάνε μια καινούργια ερώτηση.";
        conversation.add(botResponse);
        updateChatArea();
        textUser.clear();
    }

    private void updateChatArea() {
        chatArea.clear();
        for (String message : conversation) {
            chatArea.appendText(message + "\n");
        }
    }

}
