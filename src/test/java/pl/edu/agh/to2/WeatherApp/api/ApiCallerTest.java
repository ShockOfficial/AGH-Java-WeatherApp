package pl.edu.agh.to2.WeatherApp.api;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ApiCallerTest {

    // Testing the API call and the response
    @Test
    void testGetWeatherByCoordinates() throws IOException {
        // given
        String lat = "12";
        String lon = "12";

        // when
        String response = ApiCaller.getWeather(lat, lon);

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

        // when
        String response = ApiCaller.getWeather(city);

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

        // when
        String response = ApiCaller.getWeather(invalidLon, "12");

        // then
        assertTrue(response.contains("wrong longitude"));
    }

    @Test
    void testGetWeatherInvalidLatitude() throws IOException {
        // given
        String invalidLat = "invalidLat";

        // when
        String response = ApiCaller.getWeather("12", invalidLat);

        // then
        assertTrue(response.contains("wrong latitude"));
    }

    @Test
    void testGetWeatherInvalidCity() throws IOException {
        // given
        String invalidCity = "invalidCity";

        // when
        String response = ApiCaller.getWeather(invalidCity);

        // then
        assertTrue(response.contains("city not found"));
    }
}
