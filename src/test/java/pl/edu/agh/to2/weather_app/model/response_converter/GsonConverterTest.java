package pl.edu.agh.to2.weather_app.model.response_converter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

import static org.junit.jupiter.api.Assertions.*;

class GsonConverterTest {

    @Test
    void testConvertValidResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String jsonResponse = "{ \"visibility\": 100 , \"weather\": [ { \"id\": 1, \"main\": \"Rain\", \"description\": \"light rain\", \"icon\": \"10d\" } ]}";

        // when
        WeatherData weatherData = gsonConverter.convertWeather(jsonResponse);

        // then
        assertNotNull(weatherData);
        assertEquals(100, weatherData.getVisibility());
        assertEquals(1, weatherData.getWeather().get(0).getId());
        assertEquals("Rain", weatherData.getWeather().get(0).getMain());
        assertEquals("light rain", weatherData.getWeather().get(0).getDescription());
        assertEquals("10d", weatherData.getWeather().get(0).getIcon());
    }

    @Test
    void testConvertInvalidResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String invalidJsonResponse = "Invalid JSON Response";

        // when & then
        assertThrows(Exception.class, () -> gsonConverter.convertWeather(invalidJsonResponse));
    }
}
