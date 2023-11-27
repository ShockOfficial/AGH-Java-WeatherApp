package pl.edu.agh.to2.WeatherApp.model.Converter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

import static org.junit.jupiter.api.Assertions.*;

public class GsonConverterTest {

    @Test
    public void testConvertValidResponse() {
        GsonConverter gsonConverter = new GsonConverter();

        String jsonResponse = "{ \"visibility\": 100, \"name\": \"Krakow\", weather: [ { \"id\": 1, \"main\": \"Rain\", \"description\": \"light rain\", \"icon\": \"10d\" } ]}";

        WeatherData weatherData = gsonConverter.convert(jsonResponse);

        assertNotNull(weatherData);
        assertEquals(100, weatherData.getVisibility());
        assertEquals("Krakow", weatherData.getName());
        assertEquals(1, weatherData.getWeather().get(0).getId());
        assertEquals("Rain", weatherData.getWeather().get(0).getMain());
        assertEquals("light rain", weatherData.getWeather().get(0).getDescription());
        assertEquals("10d", weatherData.getWeather().get(0).getIcon());
    }

    @Test
    public void testConvertInvalidResponse(){
        GsonConverter gsonConverter = new GsonConverter();

        String invalidJsonResponse = "Invalid JSON Response";

        assertThrows(Exception.class, () -> {
            gsonConverter.convert(invalidJsonResponse);
        });
    }
}
