package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalFallTest {

    @Test
    public void testSetAndGet_3h() {
        TotalFall totalFall = new TotalFall();
        float _3h = 10;
        totalFall.set_3h(_3h);
        assertEquals(_3h, totalFall.get_3h());
    }
    @Test
    public void testSetAndGet1h() {
        TotalFall totalFall = new TotalFall();
        float _1h = 10;
        totalFall.set1h(_1h);
        assertEquals(_1h, totalFall.get1h());
    }

}