package pl.edu.agh.to2.weather_app.model.forecast_data;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.exceptions.TimeNotFoundException;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.weather_data.json.SysDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ForecastDataTest {

    @Test
    void testGetAirPollution() {
        ForecastData forecastData = new ForecastData();
        AirPollutionData airPollutionData = mock(AirPollutionData.class);
        forecastData.setAirPollution(airPollutionData);
        assertEquals(airPollutionData, forecastData.getAirPollution());
    }

    @Test
    void testSetAirPollution() {
        ForecastData forecastData = new ForecastData();
        AirPollutionData airPollutionData = mock(AirPollutionData.class);
        forecastData.setAirPollution(airPollutionData);
        assertEquals(airPollutionData, forecastData.getAirPollution());
    }

    @Test
    void testGetSys() {
        ForecastData forecastData = new ForecastData();
        WeatherData weatherData = mock(WeatherData.class);
        SysDTO sysDTO = mock(SysDTO.class);
        when(weatherData.getSys()).thenReturn(sysDTO);
        List<WeatherData> weatherList = new ArrayList<>();
        weatherList.add(weatherData);
        forecastData.setWeatherList(weatherList);
        assertEquals(sysDTO, forecastData.getSys());
    }

    @Test
    void testGetWeatherList() {
        ForecastData forecastData = new ForecastData();
        List<WeatherData> weatherList = new ArrayList<>();
        forecastData.setWeatherList(weatherList);
        assertEquals(weatherList, forecastData.getWeatherList());
    }

    @Test
    void testSetWeatherList() {
        ForecastData forecastData = new ForecastData();
        List<WeatherData> weatherList = new ArrayList<>();
        forecastData.setWeatherList(weatherList);
        assertEquals(weatherList, forecastData.getWeatherList());
    }

    @Test
    void testAddWeatherDataList() {
        ForecastData forecastData = new ForecastData();
        WeatherData weatherData = mock(WeatherData.class);
        forecastData.addWeatherDataList(weatherData);
        assertTrue(forecastData.getWeatherList().contains(weatherData));
    }

    @Test
    void testGetWeatherFromTime() {
        ForecastData forecastData = new ForecastData();
        WeatherData weatherData = mock(WeatherData.class);
        when(weatherData.getTime()).thenReturn("2022-01-01 12:00:00");
        List<WeatherData> weatherList = new ArrayList<>();
        weatherList.add(weatherData);
        forecastData.setWeatherList(weatherList);

        try {
            WeatherData result = forecastData.getWeatherFromTime("2022-01-01 12:00:00");
            assertEquals(weatherData, result);
        } catch (TimeNotFoundException e) {
            fail("Unexpected TimeNotFoundException: " + e.getMessage());
        }
    }

    @Test
    void testGetWeatherFromTimeNotFound() {
        ForecastData forecastData = new ForecastData();
        WeatherData weatherData = mock(WeatherData.class);
        when(weatherData.getTime()).thenReturn("2022-01-01 12:00:00");
        List<WeatherData> weatherList = new ArrayList<>();
        weatherList.add(weatherData);
        forecastData.setWeatherList(weatherList);

        assertThrows(TimeNotFoundException.class, () -> forecastData.getWeatherFromTime("2022-01-01 13:00:00"));
    }
}
