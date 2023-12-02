package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalFallTest {

    @Test
    void testSetAndGet_3h() {
        // given
        TotalFall totalFall = new TotalFall();
        float _3h = 10;

        // when
        totalFall.setThreeH(_3h);

        // then
        assertEquals(_3h, totalFall.getThreeH());
    }

    @Test
    void testSetAndGet1h() {
        // given
        TotalFall totalFall = new TotalFall();
        float _1h = 10;

        // when
        totalFall.setOneH(_1h);

        // then
        assertEquals(_1h, totalFall.getOneH());
    }

}