package pl.edu.agh.to2.WeatherApp.model.Impl;

import com.google.gson.Gson;
import pl.edu.agh.to2.WeatherApp.api.ApiCaller;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;

import java.io.IOException;

public class WeatherModelImpl implements WeatherModel {

    private final Gson gson;

    public WeatherModelImpl() {
        this.gson = new Gson();
    }

    @Override
    public WeatherData getWeatherDataByCity(String city) throws IOException {
        String jsonResponse = ApiCaller.getWeather(city);
        return gson.fromJson(jsonResponse, WeatherData.class);
    }

    @Override
    public WeatherData getWeatherDataByCoordinates(String lon, String lat) throws IOException {
        String jsonResponse = ApiCaller.getWeather(lon, lat);
        return gson.fromJson(jsonResponse, WeatherData.class);
    }
}