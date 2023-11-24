package pl.edu.agh.to2.WeatherApp;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pl.edu.agh.to2.WeatherApp.WeatherData.WeatherData;

import java.io.IOException;

public class App extends Application  {
    @Override
    public void start(Stage primaryStage) {
        WeatherData weather;
        String response = "";
        try{
            response = ApiCaller.getWeather("Cracow");
            weather = new Gson().fromJson(response, WeatherData.class);
            System.out.println(response);
        } catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(new Label(response));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
