package pl.edu.agh.to2.WeatherApp.api;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ApiCallerTest {

    // Testing the API call and the response
    @Test
    public void testGetWeatherByCoordinates() throws IOException {
        String response = ApiCaller.getWeather("12", "12");
        assertNotNull(response);
        assertTrue(response.contains("\"lat\":12")); // Checking if the response contains the coordinates
        assertTrue(response.contains("\"lon\":12"));
        assertTrue(response.contains("Damaturu"));
        assertTrue(response.contains("weather"));
        assertTrue(response.contains("feels_like"));
        assertTrue(response.contains("main"));
    }

    @Test
    public void testGetWeatherByCity() throws IOException {
        String response = ApiCaller.getWeather("London"); // Checking if the response contains the city name
        assertNotNull(response);
        assertTrue(response.contains("London"));
        assertTrue(response.contains("weather"));
        assertTrue(response.contains("feels_like"));
        assertTrue(response.contains("main"));
    }

    @Test
    public void testGetWeatherInvalidArguments() throws IOException {
        String response = ApiCaller.getWeather("invalidLon", "12");
        assertTrue(response.contains("wrong longitude")); // Checking if the response contains the error message

        response = ApiCaller.getWeather("12", "invalidLat");
        assertTrue(response.contains("wrong latitude"));

        response = ApiCaller.getWeather("invalidCity");
        assertTrue(response.contains("city not found"));
    }
}


