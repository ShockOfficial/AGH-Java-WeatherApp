package pl.edu.agh.to2.weather_app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TempCalculatorTest {
    @Test
    void testCalculatePerceivedTemp() {
        // given
        float temp = 10;
        float windSpeed = 3.4f;

        // when
        double result = TempCalculator.calculatePerceivedTemp(temp, windSpeed);

        // then
        assertEquals(8.27, result, 0.1);
    }
    @Test
    void testCalculatePerceivedTemp2() {
        // given
        float temp = 5;
        float windSpeed = 9;

        // when
        double result = TempCalculator.calculatePerceivedTemp(temp, windSpeed);

        // then
        assertEquals(-0.15, result, 0.1);
    }

}