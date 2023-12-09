package pl.edu.agh.to2.WeatherApp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to2.WeatherApp.model.Impl.WeatherModelImpl;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.model.WeatherModule;
import pl.edu.agh.to2.WeatherApp.utils.FXMLLoaderUtility;

import java.io.IOException;

public class App extends Application {
    private WeatherModel weatherModel;

    @Override
    public void init() {
        Injector injector = Guice.createInjector(new WeatherModule());
        weatherModel = injector.getInstance(WeatherModelImpl.class);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoaderUtility.loadMainView(weatherModel);
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