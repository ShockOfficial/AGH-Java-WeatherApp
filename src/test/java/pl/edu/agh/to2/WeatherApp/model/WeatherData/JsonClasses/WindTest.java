package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindTest {

    @Test
    public void testSetAndGetSpeed() {
        Wind wind = new Wind();
        float speed = 10;
        wind.setSpeed(speed);
        assertEquals(speed, wind.getSpeed());
    }
    @Test
    public void testSetAndGetDeg() {
        Wind wind = new Wind();
        int deg = 10;
        wind.setDeg(deg);
        assertEquals(deg, wind.getDeg());
    }
    @Test
    public void testSetAndGetGust() {
        Wind wind = new Wind();
        float gust = 10;
        wind.setGust(gust);
        assertEquals(gust, wind.getGust());
    }

}