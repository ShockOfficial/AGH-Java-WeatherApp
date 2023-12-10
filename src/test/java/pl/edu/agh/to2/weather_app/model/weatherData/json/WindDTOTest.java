package pl.edu.agh.to2.weather_app.model.weatherData.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindDTOTest {

    @Test
     void testSetAndGetSpeed() {
        // given
        WindDTO windDTO = new WindDTO();
        float speed = 10;

        // when
        windDTO.setSpeed(speed);

        // then
        assertEquals(speed, windDTO.getSpeed());
    }
    @Test
     void testSetAndGetDeg() {
        // given
        WindDTO windDTO = new WindDTO();
        int deg = 10;

        // when
        windDTO.setDeg(deg);

        // then
        assertEquals(deg, windDTO.getDeg());
    }
    @Test
     void testSetAndGetGust() {
        // given
        WindDTO windDTO = new WindDTO();
        float gust = 10;

        // when
        windDTO.setGust(gust);

        // then
        assertEquals(gust, windDTO.getGust());
    }

}