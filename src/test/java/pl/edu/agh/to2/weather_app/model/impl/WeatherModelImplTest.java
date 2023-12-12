package pl.edu.agh.to2.weather_app.model.impl;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.airPollutionData.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.responseConverter.IResponseToModelConverter;
import pl.edu.agh.to2.weather_app.model.geocodingData.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class WeatherModelImplTest {

    private static class MockConverter implements IResponseToModelConverter {
        @Override
        public WeatherData convertWeather(String jsonResponse) {
            return new WeatherData();
        }
        @Override
        public GeocodingData convertCoords(String jsonResponse) {
            return null;
        }
        @Override
        public AirPollutionData convertAirPollution(String jsonResponse) {
            return null;
        }
    }

//    @Test
//    void testGetWeatherDataByCity() {
//        // given
//        WeatherModelImpl weatherModel = new WeatherModelImpl(new MockConverter());
//        String city = "TestCity";
//
//        // when
//        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);
//
//        // then
//        assertDoesNotThrow(() -> {
//            WeatherData weatherData = result.join();
//            assertNotNull(weatherData);
//        });
//    }

    @Test
    void testGetWeatherDataByCoordinates() {
        // given
        WeatherModelImpl weatherModel = new WeatherModelImpl(new MockConverter());
        String lon = "10.0";
        String lat = "20.0";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCoordinates(lon, lat);

        // then
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }
}
