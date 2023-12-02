package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainInfoTest {

    @Test
    void testSetAndGetSeaLevel() {
        // given
        MainInfo mainInfo = new MainInfo();
        int seaLevel = 10;

        // when
        mainInfo.setSeaLevel(seaLevel);

        // then
        assertEquals(seaLevel, mainInfo.getSeaLevel());
    }

    @Test
    void testSetAndGetGrndLevel() {
        // given
        MainInfo mainInfo = new MainInfo();
        int grndLevel = 10;

        // when
        mainInfo.setGrndLevel(grndLevel);

        // then
        assertEquals(grndLevel, mainInfo.getGrndLevel());
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
        mainInfo.setFeelsLike(feelsLike);

        // then
        assertEquals(feelsLike, mainInfo.getFeelsLike());
    }

    @Test
    void testSetAndGetTempMin() {
        // given
        MainInfo mainInfo = new MainInfo();
        float tempMin = 10.0F;

        // when
        mainInfo.setTempMin(tempMin);

        // then
        assertEquals(tempMin, mainInfo.getTempMin());
    }

    @Test
    void testSetAndGetTempMax() {
        // given
        MainInfo mainInfo = new MainInfo();
        float tempMax = 10.0F;

        // when
        mainInfo.setTempMax(tempMax);

        // then
        assertEquals(tempMax, mainInfo.getTempMax());
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
