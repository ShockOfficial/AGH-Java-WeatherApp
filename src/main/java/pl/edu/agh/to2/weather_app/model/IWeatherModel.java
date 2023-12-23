package pl.edu.agh.to2.weather_app.model;

import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import java.util.concurrent.CompletableFuture;

public interface IWeatherModel {
    CompletableFuture<ForecastData> getWeatherDataByCity(String city);
    CompletableFuture<ForecastData> getWeatherDataByCoordinates(String lon, String lat);
}