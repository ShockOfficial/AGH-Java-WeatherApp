package pl.edu.agh.to2.WeatherApp.model;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

import java.io.IOException;

public interface WeatherModel {
    WeatherData getWeatherDataByCity(String city) throws IOException;
    WeatherData getWeatherDataByCoordinates(String lon, String lat) throws IOException;
}