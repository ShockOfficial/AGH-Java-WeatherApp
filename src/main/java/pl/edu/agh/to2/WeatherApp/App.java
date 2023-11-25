package pl.edu.agh.to2.WeatherApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.edu.agh.to2.WeatherApp.model.Impl.WeatherModelImpl;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;

import java.io.IOException;

public class App extends Application  {
    private WeatherModel weatherModel;

    @Override
    public void init() {
        weatherModel = new WeatherModelImpl();
    }
    @Override
    public void start(Stage primaryStage) {
        WeatherData weather;
        String response;
        try {
            weather = weatherModel.getWeatherDataByCity("Cracow");
            response = weather.toString();
            System.out.println(weather);
        } catch (IOException e) {
            response = "Error fetching weather data";
            e.printStackTrace();
        }
        Scene scene = new Scene(new Label(response));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
