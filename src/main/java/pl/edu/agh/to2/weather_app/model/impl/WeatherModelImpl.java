package pl.edu.agh.to2.weather_app.model.impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.exceptions.DataFetchException;
import pl.edu.agh.to2.weather_app.exceptions.GeocodingException;
import pl.edu.agh.to2.weather_app.logger.Logger;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

public class WeatherModelImpl implements IWeatherModel {
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
                if (geocoding == null) {
                    logger.log("Geocoding API error: " + city + " not found");
                    throw new GeocodingException(city + " not found");
                }

                ForecastData forecast = this.getForecast(geocoding.getLon(), geocoding.getLat());
                WeatherData weather = forecast.getWeatherList().get(0);
                AirPollutionData airPollution = this.getAirPollution(geocoding.getLon(), geocoding.getLat());

                weather.setGeocodingData(geocoding);
                weather.setAirPollutionData(airPollution);

                return weather;
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
                ForecastData forecast = this.getForecast(lon, lat);
                WeatherData weather = forecast.getWeatherList().get(0);
                forecast.setAirPollution(airPollution);
                weather.setAirPollutionData(airPollution);
                return weather;
            } catch (IOException e) {
                logger.log(String.format("Failed to fetch data from API for %s, %s", lon, lat));
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }

    @Override
    public CompletableFuture<WeatherData> getForecastDataByCity(String city, String time) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                GeocodingData geocoding = this.getCoords(city);
                if (geocoding == null) {
                    logger.log("Geocoding API error: " + city + " not found");
                    throw new GeocodingException(city + " not found");
                }

                ForecastData forecast = this.getForecast(geocoding.getLon(), geocoding.getLat());
                WeatherData weather = getProperForecast(forecast, time);
                AirPollutionData airPollution = this.getAirPollution(geocoding.getLon(), geocoding.getLat());
                weather.setGeocodingData(geocoding);
                weather.setAirPollutionData(airPollution);

                return weather;
            } catch (IOException e) {
                logger.log("Failed to fetch data from API for " + city);
                throw new DataFetchException("Error fetching weather data");
            }
        });
    }

    @Override
    public  CompletableFuture<WeatherData> getForecastDataByCoordinates(String lon, String lat, String time) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AirPollutionData airPollution = this.getAirPollution(lon, lat);
                ForecastData forecast = this.getForecast(lon, lat);
                WeatherData weather = getProperForecast(forecast, time);
                forecast.setAirPollution(airPollution);
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
        if (jsonResponse == null) {
            return null;
        }
        return converter.convertCoords(jsonResponse.
                substring(1, jsonResponse.length()-1));
    }

    private AirPollutionData getAirPollution(String lon, String lat) throws IOException{
        String jsonResponse = provider.getAirPollution(lon, lat);
        return converter.convertAirPollution(jsonResponse);
    }

    private ForecastData getForecast(String lon, String lat) throws IOException{
        String jsonResponse = provider.getForecast(lon, lat);
        return converter.convertForecast(jsonResponse);
    }

    private WeatherData getProperForecast(ForecastData forecastData, String time) {
        time += ":00";
        LocalTime userTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
        for (WeatherData weatherData : forecastData.getWeatherList()) {
            LocalTime forecastTime = LocalTime.parse(weatherData.getTime().split(" ")[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
            if (differenceOneAndHalfHour(userTime, forecastTime)) {
                return weatherData;
            }
        }
        return null;
    }

    private boolean differenceOneAndHalfHour(LocalTime userTime, LocalTime forecastTime) {
        if (userTime.isBefore(forecastTime)) {
            return userTime.plusHours(1).plusMinutes(31).isAfter(forecastTime);
        } else {
            return forecastTime.plusHours(1).plusMinutes(31).isAfter(userTime);
        }
    }
}