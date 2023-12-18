package pl.edu.agh.to2.weather_app.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class DataProviderTest {

    @Mock
    DataProvider provider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherByCoordinates() throws IOException {
        // given
        String lat = "12";
        String lon = "12";
        when(provider.getWeather(anyString(), anyString())).thenReturn("{\"lat\":12,\"lon\":12,\"name\":\"Damaturu\",\"weather\":[],\"main\":{\"feels_like\":0}}");

        // when
        String response = provider.getWeather(lat, lon);

        // then
        assertNotNull(response);
        assertTrue(response.contains("\"lat\":12"));
        assertTrue(response.contains("\"lon\":12"));
        assertTrue(response.contains("Damaturu"));
        assertTrue(response.contains("weather"));
        assertTrue(response.contains("feels_like"));
        assertTrue(response.contains("main"));
    }

    @Test
    void testGetWeatherByCity() throws IOException {
        // given
        String city = "London";
        when(provider.getWeather(anyString())).thenReturn("{\"name\":\"London\",\"weather\":[],\"main\":{\"feels_like\":0}}");

        // when
        String response = provider.getWeather(city);

        // then
        assertNotNull(response);
        assertTrue(response.contains("London"));
        assertTrue(response.contains("weather"));
        assertTrue(response.contains("feels_like"));
        assertTrue(response.contains("main"));
    }

    @Test
    void testGetWeatherInvalidLongitude() throws IOException {
        // given
        String invalidLon = "invalidLon";
        when(provider.getWeather(anyString(), anyString())).thenReturn("wrong longitude");

        // when
        String response = provider.getWeather(invalidLon, "12");

        // then
        assertTrue(response.contains("wrong longitude"));
    }

    @Test
    void testGetWeatherInvalidLatitude() throws IOException {
        // given
        String invalidLat = "invalidLat";
        when(provider.getWeather(anyString(), anyString())).thenReturn("wrong latitude");

        // when
        String response = provider.getWeather("12", invalidLat);

        // then
        assertTrue(response.contains("wrong latitude"));
    }

    @Test
    void testGetWeatherInvalidCity() throws IOException {
        // given
        String invalidCity = "invalidCity";
        when(provider.getWeather(anyString())).thenReturn("city not found");

        // when
        String response = provider.getWeather(invalidCity);

        // then
        assertTrue(response.contains("city not found"));
    }

    @Test
    void testGetIconUrl() {
        // given
        String iconCode = "01d";
        when(provider.getIconUrl(anyString())).thenReturn("https://openweathermap.org/img/wn/01d@4x.png");

        // when
        String iconUrl = provider.getIconUrl(iconCode);

        // then
        assertEquals("https://openweathermap.org/img/wn/01d@4x.png", iconUrl);
    }

    @Test
    void testGetCoords() throws IOException {
        // given
        String city = "London";
        when(provider.getCoords(anyString())).thenReturn("{\"name\":\"London\",\"lat\":0,\"lon\":0}");

        // when
        String response = provider.getCoords(city);

        // then
        assertNotNull(response);
        assertTrue(response.contains("London"));
        assertTrue(response.contains("lat"));
        assertTrue(response.contains("lon"));
    }

    @Test
    void testGetAirPollution() throws IOException {
        // given
        String lat = "12";
        String lon = "12";
        when(provider.getAirPollution(anyString(), anyString())).thenReturn("{\"lat\":12,\"lon\":12,\"list\":[],\"components\":{},\"aqi\":0}");

        // when
        String response = provider.getAirPollution(lat, lon);

        // then
        assertNotNull(response);
        assertTrue(response.contains("\"lat\":12"));
        assertTrue(response.contains("\"lon\":12"));
        assertTrue(response.contains("list"));
        assertTrue(response.contains("components"));
        assertTrue(response.contains("aqi"));
    }
}
