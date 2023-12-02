package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SysTest {

    @Test
     void testSetAndGetType() {
        // given
        Sys sys = new Sys();
        int type = 10;

        //when
        sys.setType(type);

        //then
        assertEquals(type, sys.getType());
    }
    @Test
     void testSetAndGetId() {
        // given
        Sys sys = new Sys();
        int id = 10;

        //when
        sys.setId(id);

        //then
        assertEquals(id, sys.getId());
    }
    @Test
     void testSetAndGetCountry() {
        // given
        Sys sys = new Sys();
        String country = "country";

        //when
        sys.setCountry(country);

        //then
        assertEquals(country, sys.getCountry());
    }
    @Test
     void testSetAndGetSunrise() {
        // given
        Sys sys = new Sys();
        long sunrise = 10;

        //when
        sys.setSunrise(sunrise);

        //then
        assertEquals(sunrise, sys.getSunrise());
    }
    @Test
     void testSetAndGetSunset() {
        // given
        Sys sys = new Sys();
        long sunset = 10;

        //when
        sys.setSunset(sunset);

        //then
        assertEquals(sunset, sys.getSunset());
    }

}