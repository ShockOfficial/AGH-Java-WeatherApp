package pl.edu.agh.to2.WeatherApp.model.Impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.api.WeatherDataProvider;
import pl.edu.agh.to2.WeatherApp.exceptions.DataFetchException;
import pl.edu.agh.to2.WeatherApp.logger.Logger;
import pl.edu.agh.to2.WeatherApp.model.converter.IResponseToModelConverter;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WeatherModelImpl implements WeatherModel {

    @Inject
    private Logger logger;
    private final IResponseToModelConverter converter;

    @Inject
    public WeatherModelImpl(IResponseToModelConverter converter) {
        this.converter = converter;
    }

    @Override
    public CompletableFuture<WeatherData> getWeatherDataByCity(String city) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String jsonResponse = WeatherDataProvider.getWeather(city);
                return converter.convert(jsonResponse);
            } catch (IOException e) {
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }

    @Override
    public  CompletableFuture<WeatherData> getWeatherDataByCoordinates(String lon, String lat) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String jsonResponse = WeatherDataProvider.getWeather(lon, lat);
                return converter.convert(jsonResponse);
            } catch (IOException e) {
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }
}