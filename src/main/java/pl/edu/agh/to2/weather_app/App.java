package pl.edu.agh.to2.weather_app;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to2.weather_app.utils.FXMLLoaderUtility;


import java.io.IOException;

public class App extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoaderUtility.loadMainView();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Weather App");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
