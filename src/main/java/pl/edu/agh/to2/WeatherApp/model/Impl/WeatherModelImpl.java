package pl.edu.agh.to2.WeatherApp.model.Impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.api.GeocodingProvider;
import pl.edu.agh.to2.WeatherApp.api.WeatherDataProvider;
import pl.edu.agh.to2.WeatherApp.exceptions.DataFetchException;
import pl.edu.agh.to2.WeatherApp.logger.Logger;
import pl.edu.agh.to2.WeatherApp.model.converter.IResponseToModelConverter;
import pl.edu.agh.to2.WeatherApp.model.geocodingData.GeocodingData;
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
                String jsonResponse = GeocodingProvider.getGeocodingInfo(city);
                GeocodingData geocoding = converter.convertCoords(
                        jsonResponse.substring(1, jsonResponse.length()-1));
                return this.getWeather(geocoding.getLon(), geocoding.getLat());
            } catch (IOException e) {
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }

    @Override
    public  CompletableFuture<WeatherData> getWeatherDataByCoordinates(String lon, String lat) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.getWeather(lon, lat);
            } catch (IOException e) {
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }
    private WeatherData getWeather(String lon, String lat) throws IOException{
        String jsonResponse = WeatherDataProvider.getWeather(lon, lat);
        return converter.convertWeather(jsonResponse);
    }
}