package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainInfoTest {

    @Test
    void testSetAndGetSeaLevel() {
        // given
        MainInfo mainInfo = new MainInfo();
        int seaLevel = 10;

        // when
        mainInfo.setSea_level(seaLevel);

        // then
        assertEquals(seaLevel, mainInfo.getSea_level());
    }

    @Test
    void testSetAndGetGrndLevel() {
        // given
        MainInfo mainInfo = new MainInfo();
        int grndLevel = 10;

        // when
        mainInfo.setGrnd_level(grndLevel);

        // then
        assertEquals(grndLevel, mainInfo.getGrnd_level());
    }

    @Test
    void testSetAndGetTemp() {
        // given
        MainInfo mainInfo = new MainInfo();
        float temp = 10.0F;

        // when
        mainInfo.setTemp(temp);

        // then
        assertEquals(temp, mainInfo.getTemp());
    }

    @Test
    void testSetAndGetFeelsLike() {
        // given
        MainInfo mainInfo = new MainInfo();
        float feelsLike = 10.0F;

        // when
        mainInfo.setFeels_like(feelsLike);

        // then
        assertEquals(feelsLike, mainInfo.getFeels_like());
    }

    @Test
    void testSetAndGetTempMin() {
        // given
        MainInfo mainInfo = new MainInfo();
        float tempMin = 10.0F;

        // when
        mainInfo.setTemp_min(tempMin);

        // then
        assertEquals(tempMin, mainInfo.getTemp_min());
    }

    @Test
    void testSetAndGetTempMax() {
        // given
        MainInfo mainInfo = new MainInfo();
        float tempMax = 10.0F;

        // when
        mainInfo.setTemp_max(tempMax);

        // then
        assertEquals(tempMax, mainInfo.getTemp_max());
    }

    @Test
    void testSetAndGetPressure() {
        // given
        MainInfo mainInfo = new MainInfo();
        int pressure = 10;

        // when
        mainInfo.setPressure(pressure);

        // then
        assertEquals(pressure, mainInfo.getPressure());
    }

    @Test
    void testSetAndGetHumidity() {
        // given
        MainInfo mainInfo = new MainInfo();
        int humidity = 10;

        // when
        mainInfo.setHumidity(humidity);

        // then
        assertEquals(humidity, mainInfo.getHumidity());
    }
}
