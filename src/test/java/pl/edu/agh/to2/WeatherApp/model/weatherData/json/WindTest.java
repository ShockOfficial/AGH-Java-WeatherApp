package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindTest {

    @Test
     void testSetAndGetSpeed() {
        // given
        Wind wind = new Wind();
        float speed = 10;

        // when
        wind.setSpeed(speed);

        // then
        assertEquals(speed, wind.getSpeed());
    }
    @Test
     void testSetAndGetDeg() {
        // given
        Wind wind = new Wind();
        int deg = 10;

        // when
        wind.setDeg(deg);

        // then
        assertEquals(deg, wind.getDeg());
    }
    @Test
     void testSetAndGetGust() {
        // given
        Wind wind = new Wind();
        float gust = 10;

        // when
        wind.setGust(gust);

        // then
        assertEquals(gust, wind.getGust());
    }

}