package pl.edu.agh.to2.weather_app.model.weatherData.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDTOTest {

    @Test
     void testSetAndGetId() {
        // given
        WeatherDTO weatherDTO = new WeatherDTO();
        int id = 10;

        // when
        weatherDTO.setId(id);

        // then
        assertEquals(id, weatherDTO.getId());
    }
    @Test
     void testSetAndGetDescription() {
        // given
        WeatherDTO weatherDTO = new WeatherDTO();
        String description = "description";

        // when
        weatherDTO.setDescription(description);

        // then
        assertEquals(description, weatherDTO.getDescription());
    }
    @Test
     void testSetAndGetMain() {
        // given
        WeatherDTO weatherDTO = new WeatherDTO();
        String main = "main";

        // when
        weatherDTO.setMain(main);

        // then
        assertEquals(main, weatherDTO.getMain());
    }

    @Test
     void testSetAndGetIcon() {
        // given
        WeatherDTO weatherDTO = new WeatherDTO();
        String icon = "1d2";

        // when
        weatherDTO.setIcon(icon);

        // then
        assertEquals(icon, weatherDTO.getIcon());
    }


}