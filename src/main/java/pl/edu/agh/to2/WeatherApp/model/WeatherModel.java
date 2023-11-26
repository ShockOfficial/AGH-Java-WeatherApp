package pl.edu.agh.to2.WeatherApp.model;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface WeatherModel {
    CompletableFuture<WeatherData> getWeatherDataByCity(String city);
    CompletableFuture<WeatherData> getWeatherDataByCoordinates(String lon, String lat);
}