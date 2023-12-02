package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordDTOTest {

    @Test
    void testSetAndGetLon() {
        // given
        CoordDTO coordDTO = new CoordDTO();
        float lon = 10.0F;

        // when
        coordDTO.setLon(lon);

        // then
        assertEquals(lon, coordDTO.getLon());
    }

    @Test
    void testSetAndGetLat() {
        // given
        CoordDTO coordDTO = new CoordDTO();
        float lat = 10.0F;

        // when
        coordDTO.setLat(lat);

        // then
        assertEquals(lat, coordDTO.getLat());
    }
}
