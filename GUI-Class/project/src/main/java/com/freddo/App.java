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
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chatbot App");
        //Image icon = new Image("chatbot1.jpg");
        //stage.getIcons().add(icon);
        

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void logout(Stage stage) {
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
        alert1.setTitle("Logout");
        alert1.setHeaderText("You're about to logout");
        alert1.setContentText("I hope that we could be of help!");

        if (alert1.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }   
}
