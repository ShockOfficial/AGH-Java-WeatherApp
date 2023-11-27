package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    public void testSetAndGetId() {
        Weather weather = new Weather();
        int id = 10;
        weather.setId(id);
        assertEquals(id, weather.getId());
    }
    @Test
    public void testSetAndGetDescription() {
        Weather weather = new Weather();
        String description = "description";
        weather.setDescription(description);
        assertEquals(description, weather.getDescription());
    }
    @Test
    public void testSetAndGetMain() {
        Weather weather = new Weather();
        String main = "main";
        weather.setMain(main);
        assertEquals(main, weather.getMain());
    }

    @Test
    public void testSetAndGetIcon() {
        Weather weather = new Weather();
        String icon = "1d2";
        weather.setIcon(icon);
        assertEquals(icon, weather.getIcon());
    }


}