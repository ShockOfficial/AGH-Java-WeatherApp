package pl.edu.agh.to2.WeatherApp.model.Impl;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.model.Converter.IResponseToModelConverter;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class WeatherModelImplTest {

    private IResponseToModelConverter converter;

    private static class MockConverter implements IResponseToModelConverter {
        @Override
        public WeatherData convert(String jsonResponse) {
            return new WeatherData();
        }
    }

    @Test
    void testGetWeatherDataByCity() {
        WeatherModelImpl weatherModel = new WeatherModelImpl(new MockConverter());
        String city = "TestCity";
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }

    @Test
    void testGetWeatherDataByCoordinates() {
        WeatherModelImpl weatherModel = new WeatherModelImpl(new MockConverter());
        String lon = "10.0";
        String lat = "20.0";
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCoordinates(lon, lat);
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }
}
