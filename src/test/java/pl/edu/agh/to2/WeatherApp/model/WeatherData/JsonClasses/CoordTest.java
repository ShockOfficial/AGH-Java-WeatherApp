package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordTest {

    @Test
    public void testSetAndGetLon() {
        Coord coord = new Coord();
        float lon = 10;
        coord.setLon(lon);
        assertEquals(lon, coord.getLon());
    }
    @Test
    public void testSetAndGetLat() {
        Coord coord = new Coord();
        float lat = 10;
        coord.setLat(lat);
        assertEquals(lat, coord.getLat());
    }

}