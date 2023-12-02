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
        totalFall.set_3h(_3h);

        // then
        assertEquals(_3h, totalFall.get_3h());
    }

    @Test
    void testSetAndGet1h() {
        // given
        TotalFall totalFall = new TotalFall();
        float _1h = 10;

        // when
        totalFall.set1h(_1h);

        // then
        assertEquals(_1h, totalFall.get1h());
    }

}