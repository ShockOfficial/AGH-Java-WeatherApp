package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
     void testSetAndGetId() {
        // given
        Weather weather = new Weather();
        int id = 10;

        // when
        weather.setId(id);

        // then
        assertEquals(id, weather.getId());
    }
    @Test
     void testSetAndGetDescription() {
        // given
        Weather weather = new Weather();
        String description = "description";

        // when
        weather.setDescription(description);

        // then
        assertEquals(description, weather.getDescription());
    }
    @Test
     void testSetAndGetMain() {
        // given
        Weather weather = new Weather();
        String main = "main";

        // when
        weather.setMain(main);

        // then
        assertEquals(main, weather.getMain());
    }

    @Test
     void testSetAndGetIcon() {
        // given
        Weather weather = new Weather();
        String icon = "1d2";

        // when
        weather.setIcon(icon);

        // then
        assertEquals(icon, weather.getIcon());
    }


}