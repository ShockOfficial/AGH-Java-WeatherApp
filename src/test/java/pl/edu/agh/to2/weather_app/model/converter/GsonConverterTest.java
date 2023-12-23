package pl.edu.agh.to2.weather_app.model.converter;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.response_converter.GsonConverter;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

import static org.junit.jupiter.api.Assertions.*;

class GsonConverterTest {

    @Test
    void testConvertValidWeatherResponse() {
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
    void testConvertInvalidWeatherResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String invalidJsonResponse = "Invalid JSON Response";

        // when & then
        assertThrows(Exception.class, () -> gsonConverter.convertWeather(invalidJsonResponse));
    }

    @Test
    void testConvertValidCoordsResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String jsonResponse = "{ \"lat\": 12.34, \"lon\": 56.78 }";

        // when
        GeocodingData geocodingData = gsonConverter.convertCoords(jsonResponse);

        // then
        assertNotNull(geocodingData);
        assertEquals("12.34", geocodingData.getLat());
        assertEquals("56.78", geocodingData.getLon());
    }

    @Test
    void testConvertInvalidCoordsResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String invalidJsonResponse = "Invalid JSON Response";

        // when & then
        assertThrows(Exception.class, () -> gsonConverter.convertCoords(invalidJsonResponse));
    }

    @Test
    void testConvertValidAirPollutionResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String jsonResponse = "{ \"list\": [ { \"main\": { \"aqi\": 2 } } ] }";

        // when
        AirPollutionData airPollutionData = gsonConverter.convertAirPollution(jsonResponse);

        // then
        assertNotNull(airPollutionData);
        assertEquals("2", airPollutionData.getPollutionListElement().getMainInfo().getAqi());
    }

    @Test
    void testConvertInvalidAirPollutionResponse() {
        // given
        GsonConverter gsonConverter = new GsonConverter();
        String invalidJsonResponse = "Invalid JSON Response";

        // when & then
        assertThrows(Exception.class, () -> gsonConverter.convertAirPollution(invalidJsonResponse));
    }
}
