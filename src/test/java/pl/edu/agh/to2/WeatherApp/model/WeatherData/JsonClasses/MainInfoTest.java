package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainInfoTest {

    @Test
    public void testSetAndGetSeaLevel() {
        MainInfo mainInfo = new MainInfo();
        int seaLevel = 10;
        mainInfo.setSea_level(seaLevel);
        assertEquals(seaLevel, mainInfo.getSea_level());
    }
    @Test
    public void testSetAndGetGrndLevel() {
        MainInfo mainInfo = new MainInfo();
        int grndLevel = 10;
        mainInfo.setGrnd_level(grndLevel);
        assertEquals(grndLevel, mainInfo.getGrnd_level());
    }
    @Test
    public void testSetAndGetTemp() {
        MainInfo mainInfo = new MainInfo();
        float temp = 10;
        mainInfo.setTemp(temp);
        assertEquals(temp, mainInfo.getTemp());
    }
    @Test
    public void testSetAndGetFeelsLike() {
        MainInfo mainInfo = new MainInfo();
        float feelsLike = 10;
        mainInfo.setFeels_like(feelsLike);
        assertEquals(feelsLike, mainInfo.getFeels_like());
    }
    @Test
    public void testSetAndGetTempMin() {
        MainInfo mainInfo = new MainInfo();
        float tempMin = 10;
        mainInfo.setTemp_min(tempMin);
        assertEquals(tempMin, mainInfo.getTemp_min());
    }
    @Test
    public void testSetAndGetTempMax() {
        MainInfo mainInfo = new MainInfo();
        float tempMax = 10;
        mainInfo.setTemp_max(tempMax);
        assertEquals(tempMax, mainInfo.getTemp_max());
    }
    @Test
    public void testSetAndGetPressure() {
        MainInfo mainInfo = new MainInfo();
        int pressure = 10;
        mainInfo.setPressure(pressure);
        assertEquals(pressure, mainInfo.getPressure());
    }
    @Test
    public void testSetAndGetHumidity() {
        MainInfo mainInfo = new MainInfo();
        int humidity = 10;
        mainInfo.setHumidity(humidity);
        assertEquals(humidity, mainInfo.getHumidity());
    }

}