package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SysTest {

    @Test
    public void testSetAndGetType() {
        Sys sys = new Sys();
        int type = 10;
        sys.setType(type);
        assertEquals(type, sys.getType());
    }
    @Test
    public void testSetAndGetId() {
        Sys sys = new Sys();
        int id = 10;
        sys.setId(id);
        assertEquals(id, sys.getId());
    }
    @Test
    public void testSetAndGetCountry() {
        Sys sys = new Sys();
        String country = "country";
        sys.setCountry(country);
        assertEquals(country, sys.getCountry());
    }
    @Test
    public void testSetAndGetSunrise() {
        Sys sys = new Sys();
        long sunrise = 10;
        sys.setSunrise(sunrise);
        assertEquals(sunrise, sys.getSunrise());
    }
    @Test
    public void testSetAndGetSunset() {
        Sys sys = new Sys();
        long sunset = 10;
        sys.setSunset(sunset);
        assertEquals(sunset, sys.getSunset());
    }

}