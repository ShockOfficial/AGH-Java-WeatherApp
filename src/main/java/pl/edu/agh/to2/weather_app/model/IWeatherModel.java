package pl.edu.agh.to2.weather_app.model;

import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import java.util.concurrent.CompletableFuture;

public interface IWeatherModel {
    CompletableFuture<WeatherData> getWeatherDataByCity(String city);
    CompletableFuture<WeatherData> getWeatherDataByCoordinates(String lon, String lat);
    CompletableFuture<WeatherData> getForecastDataByCity(String city, String time);
    CompletableFuture<WeatherData> getForecastDataByCoordinates(String lon, String lat, String time);
}