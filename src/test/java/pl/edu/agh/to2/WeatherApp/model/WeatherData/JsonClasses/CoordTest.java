package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordTest {

    @Test
    void testSetAndGetLon() {
        // given
        Coord coord = new Coord();
        float lon = 10.0F;

        // when
        coord.setLon(lon);

        // then
        assertEquals(lon, coord.getLon());
    }

    @Test
    void testSetAndGetLat() {
        // given
        Coord coord = new Coord();
        float lat = 10.0F;

        // when
        coord.setLat(lat);

        // then
        assertEquals(lat, coord.getLat());
    }
}
