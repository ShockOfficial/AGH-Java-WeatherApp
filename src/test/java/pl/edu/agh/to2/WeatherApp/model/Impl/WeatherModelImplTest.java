package pl.edu.agh.to2.WeatherApp.model.Impl;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.model.converter.IResponseToModelConverter;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class WeatherModelImplTest {

    private static class MockConverter implements IResponseToModelConverter {
        @Override
        public WeatherData convert(String jsonResponse) {
            return new WeatherData();
        }
    }

    @Test
    void testGetWeatherDataByCity() {
        // given
        WeatherModelImpl weatherModel = new WeatherModelImpl(new MockConverter());
        String city = "TestCity";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);

        // then
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }

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
