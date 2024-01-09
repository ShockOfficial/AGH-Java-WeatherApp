package pl.edu.agh.to2.weather_app.model.impl;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.exceptions.GeocodingException;
import pl.edu.agh.to2.weather_app.logger.Logger;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
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
    @InjectMocks
    OkHttpClient client = new OkHttpClient();
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

        @Override
        public ForecastData convertForecast(String response) {
            return new ForecastData();
        }
    }

    private static class MockDataProvider extends DataProvider {
        private static final String MOCK_WEATHER_DATA = "{\"coord\":{\"lon\":21,\"lat\":52},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":5.92,\"feels_like\":2,\"temp_min\":5.51,\"temp_max\":6.23,\"pressure\":1022,\"humidity\":93},\"visibility\":4500,\"wind\":{\"speed\":6.17,\"deg\":260},\"clouds\":{\"all\":75},\"dt\":1702906777,\"sys\":{\"type\":2,\"id\":2035775,\"country\":\"PL\",\"sunrise\":1702881658,\"sunset\":1702909427},\"timezone\":3600,\"id\":756135,\"name\":\"Warsaw\",\"cod\":200}";

        private static final String MOCK_COORDS_DATA = "[{\"name\":\"Warsaw\",\"local_names\":{\"pl\":\"Warszawa\"}]";

        private static final String MOCK_AIR_POLLUTION_DATA = "{\"coord\":{\"lon\":21,\"lat\":52},\"list\":[{\"main\":{\"aqi\":2},\"components\":{\"co\":283.72,\"no\":0.01,\"no2\":9.34,\"o3\":42.92,\"so2\":4.23,\"pm2_5\":10.22,\"pm10\":12.41,\"nh3\":0.38},\"dt\":1702907189}]}";

        private static final String MOCK_FORECAST_DATA = "{\"cod\":\"200\",\"message\":0,\"cnt\":8,\"list\":[{\"dt\":1704758400,\"main\":{\"temp\":-11.99,\"feels_like\":-15.1,\"temp_min\":-11.99,\"temp_max\":-8.77,\"pressure\":1035,\"sea_level\":1035,\"grnd_level\":1021,\"humidity\":69,\"temp_kf\":-3.22},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.36,\"deg\":50,\"gust\":3.06},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 00:00:00\"},{\"dt\":1704769200,\"main\":{\"temp\":-10.86,\"feels_like\":-10.86,\"temp_min\":-10.86,\"temp_max\":-8.6,\"pressure\":1036,\"sea_level\":1036,\"grnd_level\":1021,\"humidity\":72,\"temp_kf\":-2.26},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.03,\"deg\":13,\"gust\":1.66},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 03:00:00\"},{\"dt\":1704780000,\"main\":{\"temp\":-9.64,\"feels_like\":-9.64,\"temp_min\":-9.64,\"temp_max\":-8.47,\"pressure\":1036,\"sea_level\":1036,\"grnd_level\":1021,\"humidity\":76,\"temp_kf\":-1.17},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":1},\"wind\":{\"speed\":1.01,\"deg\":292,\"gust\":1.38},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 06:00:00\"},{\"dt\":1704790800,\"main\":{\"temp\":-6.82,\"feels_like\":-6.82,\"temp_min\":-6.82,\"temp_max\":-6.82,\"pressure\":1038,\"sea_level\":1038,\"grnd_level\":1022,\"humidity\":67,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":6},\"wind\":{\"speed\":1.32,\"deg\":267,\"gust\":1.68},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-09 09:00:00\"},{\"dt\":1704801600,\"main\":{\"temp\":-4.81,\"feels_like\":-8.27,\"temp_min\":-4.81,\"temp_max\":-4.81,\"pressure\":1036,\"sea_level\":1036,\"grnd_level\":1021,\"humidity\":60,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":5},\"wind\":{\"speed\":2.15,\"deg\":264,\"gust\":2.96},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2024-01-09 12:00:00\"},{\"dt\":1704812400,\"main\":{\"temp\":-5.9,\"feels_like\":-9.3,\"temp_min\":-5.9,\"temp_max\":-5.9,\"pressure\":1035,\"sea_level\":1035,\"grnd_level\":1020,\"humidity\":74,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":1.98,\"deg\":264,\"gust\":4.25},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 15:00:00\"},{\"dt\":1704823200,\"main\":{\"temp\":-6.32,\"feels_like\":-9.77,\"temp_min\":-6.32,\"temp_max\":-6.32,\"pressure\":1034,\"sea_level\":1034,\"grnd_level\":1019,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":1},\"wind\":{\"speed\":1.97,\"deg\":267,\"gust\":4.63},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 18:00:00\"},{\"dt\":1704834000,\"main\":{\"temp\":-6.6,\"feels_like\":-11.11,\"temp_min\":-6.6,\"temp_max\":-6.6,\"pressure\":1034,\"sea_level\":1034,\"grnd_level\":1019,\"humidity\":74,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":2},\"wind\":{\"speed\":2.72,\"deg\":255,\"gust\":6},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2024-01-09 21:00:00\"}],\"city\":{\"id\":756135,\"name\":\"Warsaw\",\"coord\":{\"lat\":52,\"lon\":21},\"country\":\"PL\",\"population\":1000000,\"timezone\":3600,\"sunrise\":1704696199,\"sunset\":1704724860}}";
        public MockDataProvider(OkHttpClient client) {
            super(client);
        }

        public String getWeather(String lon, String lat) throws IOException {
            return MOCK_WEATHER_DATA;
        }

        public String getCoords(String city) throws IOException {
            return MOCK_COORDS_DATA;
        }

        public String getAirPollution(String lon, String lat) throws IOException {
            return MOCK_AIR_POLLUTION_DATA;
        }
        public String getForecast(String lon, String lat) throws IOException {
            return MOCK_FORECAST_DATA;
        }
    }

    @Test
    void testGetWeatherDataByCoordinates() throws IOException {
        // given
        IResponseToModelConverter mockConverter = mock(MockConverter.class);
        when(mockConverter.convertWeather(any())).thenReturn(new WeatherData());

        MockDataProvider mockDataProvider = mock(MockDataProvider.class);
        when(mockDataProvider.getForecast(any(), any())).thenReturn(MockDataProvider.MOCK_FORECAST_DATA);

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
        DataProvider mockDataProvider = new MockDataProvider(client);
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
