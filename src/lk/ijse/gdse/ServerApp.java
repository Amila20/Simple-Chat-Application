package lk.ijse.gdse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerApp extends Application {
    public static void main(String[] args) {
launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/ServerApp.fxml"))));
        stage.setTitle("Server");
        stage.centerOnScreen();
        stage.show();

    }
}
