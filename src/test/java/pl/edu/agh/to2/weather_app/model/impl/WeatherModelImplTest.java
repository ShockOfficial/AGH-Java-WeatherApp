package pl.edu.agh.to2.weather_app.model.impl;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.exceptions.GeocodingException;
import pl.edu.agh.to2.weather_app.logger.Logger;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherModelImplTest {

    private static class MockConverter implements IResponseToModelConverter {
        @Override
        public WeatherData convertWeather(String jsonResponse) {
            return new WeatherData();
        }

        @Override
        public GeocodingData convertCoords(String jsonResponse) {
            return new GeocodingData();
        }

        @Override
        public AirPollutionData convertAirPollution(String jsonResponse) {
            return new AirPollutionData();
        }
    }

    private static class MockDataProvider extends DataProvider {
        private static final String MOCK_WEATHER_DATA = "{\"coord\":{\"lon\":21,\"lat\":52},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":5.92,\"feels_like\":2,\"temp_min\":5.51,\"temp_max\":6.23,\"pressure\":1022,\"humidity\":93},\"visibility\":4500,\"wind\":{\"speed\":6.17,\"deg\":260},\"clouds\":{\"all\":75},\"dt\":1702906777,\"sys\":{\"type\":2,\"id\":2035775,\"country\":\"PL\",\"sunrise\":1702881658,\"sunset\":1702909427},\"timezone\":3600,\"id\":756135,\"name\":\"Warsaw\",\"cod\":200}";

        private static final String MOCK_COORDS_DATA = "[{\"name\":\"Warsaw\",\"local_names\":{\"pl\":\"Warszawa\"}]";

        private static final String MOCK_AIR_POLLUTION_DATA = "{\"coord\":{\"lon\":21,\"lat\":52},\"list\":[{\"main\":{\"aqi\":2},\"components\":{\"co\":283.72,\"no\":0.01,\"no2\":9.34,\"o3\":42.92,\"so2\":4.23,\"pm2_5\":10.22,\"pm10\":12.41,\"nh3\":0.38},\"dt\":1702907189}]}";

        public String getWeather(String lon, String lat) throws IOException {
            return MOCK_WEATHER_DATA;
        }

        public String getCoords(String city) throws IOException {
            return MOCK_COORDS_DATA;
        }

        public String getAirPollution(String lon, String lat) throws IOException {
            return MOCK_AIR_POLLUTION_DATA;
        }
    }

    @Test
    void testGetWeatherDataByCoordinates() {
        // given
        IResponseToModelConverter mockConverter = mock(MockConverter.class);
        when(mockConverter.convertWeather(any())).thenReturn(new WeatherData());

        DataProvider mockDataProvider = mock(MockDataProvider.class);
        Logger mockLogger = mock(Logger.class);

        WeatherModelImpl weatherModel = new WeatherModelImpl(mockConverter, mockLogger, mockDataProvider);

        String lon = "21";
        String lat = "52";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCoordinates(lon, lat);

        // then
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }


    @Test
    void testGetWeatherDataByCity() {
        // given
        IResponseToModelConverter mockConverter = new MockConverter();
        DataProvider mockDataProvider = new MockDataProvider();
        Logger mockLogger = mock(Logger.class);

        WeatherModelImpl weatherModel = new WeatherModelImpl(mockConverter, mockLogger, mockDataProvider);
        String city = "Warsaw";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);

        // then
        assertDoesNotThrow(() -> {
            WeatherData weatherData = result.join();
            assertNotNull(weatherData);
        });
    }

    @Test
    void testGetWeatherDataByCityGeocodingException() {
        // given
        IResponseToModelConverter mockConverter = mock(MockConverter.class);
        when(mockConverter.convertCoords(any())).thenThrow(new GeocodingException("Geocoding error"));

        DataProvider mockDataProvider = mock(MockDataProvider.class);
        Logger mockLogger = mock(Logger.class);

        WeatherModelImpl weatherModel = new WeatherModelImpl(mockConverter, mockLogger, mockDataProvider);
        String city = "InvalidCity";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);

        // then
        assertThrows(GeocodingException.class, () -> {
            try {
                result.join();
            } catch (CompletionException e) {
                throw e.getCause();
            }
        });
    }

    @Test
    void testGetWeatherDataByCityDataFetchException() throws IOException {
        // given
        IResponseToModelConverter mockConverter = mock(MockConverter.class);
        when(mockConverter.convertCoords(any())).thenReturn(new GeocodingData());

        DataProvider mockDataProvider = mock(MockDataProvider.class);
        when(mockDataProvider.getWeather(any(), any())).thenThrow(new IOException("Data fetch error"));

        Logger mockLogger = mock(Logger.class);

        WeatherModelImpl weatherModel = new WeatherModelImpl(mockConverter, mockLogger, mockDataProvider);
        String city = "ValidCity";

        // when
        CompletableFuture<WeatherData> result = weatherModel.getWeatherDataByCity(city);

        // then
        assertThrows(GeocodingException.class, () -> {
            try {
                result.join();
            } catch (CompletionException e) {
                throw e.getCause();
            }
        });
    }
}
