package pl.edu.agh.to2.weather_app.model.impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.exceptions.DataFetchException;
import pl.edu.agh.to2.weather_app.exceptions.GeocodingException;
import pl.edu.agh.to2.weather_app.logger.Logger;
import pl.edu.agh.to2.weather_app.model.WeatherModel;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WeatherModelImpl implements WeatherModel {

    private Logger logger;

    private final IResponseToModelConverter converter;
    private final DataProvider provider;

    @Inject
    public WeatherModelImpl(IResponseToModelConverter converter, Logger log, DataProvider provider) {
        this.converter = converter;
        this.logger = log;
        this.provider = provider;
    }
    public WeatherModelImpl(IResponseToModelConverter converter, DataProvider provider) {

        this.converter = converter;
        this.provider = provider;
    }

    @Override
    public CompletableFuture<WeatherData> getWeatherDataByCity(String city) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                GeocodingData geocoding = this.getCoords(city);
                WeatherData weather = this.getWeather(geocoding.getLon(), geocoding.getLat());
                AirPollutionData airPollution = this.getAirPollution(geocoding.getLon(), geocoding.getLat());

                weather.setGeocodingData(geocoding);
                weather.setAirPollutionData(airPollution);

                return weather;
            } catch (NullPointerException e){
                logger.log("Geocoding API error:" + city + "not found");
                throw new GeocodingException(city + " not found");
            } catch (IOException e) {
                logger.log("Failed to fetch data from API for " + city);
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }

    @Override
    public  CompletableFuture<WeatherData> getWeatherDataByCoordinates(String lon, String lat) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AirPollutionData airPollution = this.getAirPollution(lon, lat);
                WeatherData weather = this.getWeather(lon, lat);
                weather.setAirPollutionData(airPollution);
                return weather;
            } catch (IOException e) {
                logger.log(String.format("Failed to fetch data from API for %s, %s", lon, lat));
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }
    private WeatherData getWeather(String lon, String lat) throws IOException{
        String jsonResponse = provider.getWeather(lon, lat);
        return converter.convertWeather(jsonResponse);
    }

    private GeocodingData getCoords(String city) throws IOException{
        String jsonResponse = provider.getCoords(city);
        return converter.convertCoords(jsonResponse.
                substring(1, jsonResponse.length()-1));
    }

    private AirPollutionData getAirPollution(String lon, String lat) throws IOException{
        String jsonResponse = provider.getAirPollution(lon, lat);
        return converter.convertAirPollution(jsonResponse);
    }
}