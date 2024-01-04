import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {


    public static void main(String[] args) {
        //Μέθοδος main για εκκίνση προγράμμτος
        launch(args);
        //Καλεί την launch που εκκινεί την εφαρμογή
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chatbot App");
        //Image icon = new Image("chatbot1.jpg");
        //stage.getIcons().add(icon);
        //Ορισμός τίτλου παραθύρου σε "Chatbot App"
        

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //Δημιουργία νέου Scene με το φορτωμένο layout και ορισμός για το παράθυρο
            stage.show();
            //Εμφανίζει το παράθυρο
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
                //Διαχείριση αιτήματος κλεισίματος παραθύρου καλώντας την "logout"
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void logout(Stage stage) {
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
        //Δημιουργία παραθύρου επιβεβαίωσης μέσω της κλάσης alert
        alert1.setTitle("Logout");
        //Ορισμός τίτλου παραθύρου σε "Logout"
        alert1.setHeaderText("You're about to logout");
        alert1.setContentText("I hope that we could be of help!");
        //Ενσωμάτωση επικεφαλίδας και περιεχομένου

        if (alert1.showAndWait().get() == ButtonType.OK) {
            stage.close();
            //Αν ο χρήστης πατήσει ΟΚ η μέθοδος κλείνει το stage
        }
    }   
}
