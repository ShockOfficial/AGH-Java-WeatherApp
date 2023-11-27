package pl.edu.agh.to2.WeatherApp.model.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class WeatherDataTest {

    @Test
    public void testSetAndGetCoord() {
        WeatherData weatherData = new WeatherData();
        Coord coord = new Coord();
        coord.setLat(10);
        coord.setLon(20);
        weatherData.setCoord(coord);

        assertEquals(coord, weatherData.getCoord());
    }

    @Test
    public void testSetAndGetWeather() {
        WeatherData weatherData = new WeatherData();
        Weather weather = new Weather();

        weatherData.setWeather(weather);

        assertTrue(weatherData.getWeather().contains(weather));
    }

    @Test
    public void testSetAndGetBase() {
        WeatherData weatherData = new WeatherData();
        String base = "testBase";

        weatherData.setBase(base);

        assertEquals(base, weatherData.getBase());
    }

    @Test
    public void testSetAndGetMain() {
        WeatherData weatherData = new WeatherData();
        MainInfo mainInfo = new MainInfo();
        mainInfo.setFeels_like(25);

        weatherData.setMain(mainInfo);

        assertEquals(mainInfo, weatherData.getMain());
    }

    @Test
    public void testSetAndGetVisibility() {
        WeatherData weatherData = new WeatherData();
        int visibility = 1000;

        weatherData.setVisibility(visibility);

        assertEquals(visibility, weatherData.getVisibility());
    }

    @Test
    public void testSetAndGetWind() {
        WeatherData weatherData = new WeatherData();
        Wind wind = new Wind();
        wind.setDeg(5);
        wind.setGust(10);
        wind.setSpeed(20);
        weatherData.setWind(wind);

        assertEquals(wind, weatherData.getWind());
    }

    @Test
    public void testSetAndGetRain() {
        WeatherData weatherData = new WeatherData();
        TotalFall rain = new TotalFall();
        rain.set1h(10);
        rain.set_3h(20);
        weatherData.setRain(rain);

        assertEquals(rain, weatherData.getRain());
    }

    @Test
    public void testSetAndGetClouds() {
        WeatherData weatherData = new WeatherData();
        Clouds clouds = new Clouds();
        clouds.setAll(10);
        weatherData.setClouds(clouds);

        assertEquals(clouds, weatherData.getClouds());
    }

    @Test
    public void testSetAndGetDt() {
        WeatherData weatherData = new WeatherData();
        long dt = 1637966400L;

        weatherData.setDt(dt);

        assertEquals(dt, weatherData.getDt());
    }

    @Test
    public void testSetAndGetSys() {
        WeatherData weatherData = new WeatherData();
        Sys sys = new Sys();
        sys.setCountry("PL");
        sys.setId(1);
        sys.setSunrise(1637966400L);
        sys.setSunset(1637966400L);
        sys.setType(1);

        weatherData.setSys(sys);

        assertEquals(sys, weatherData.getSys());
    }

    @Test
    public void testSetAndGetTimezone() {
        WeatherData weatherData = new WeatherData();
        int timezone = 7200;

        weatherData.setTimezone(timezone);

        assertEquals(timezone, weatherData.getTimezone());
    }

    @Test
    public void testSetAndGetId() {
        WeatherData weatherData = new WeatherData();
        int id = 123;

        weatherData.setId(id);

        assertEquals(id, weatherData.getId());
    }

    @Test
    public void testSetAndGetName() {
        WeatherData weatherData = new WeatherData();
        String name = "Krakow";

        weatherData.setName(name);

        assertEquals(name, weatherData.getName());
    }

    @Test
    public void testSetAndGetCod() {
        WeatherData weatherData = new WeatherData();
        int cod = 200;

        weatherData.setCod(cod);

        assertEquals(cod, weatherData.getCod());
    }

    @Test
    public void testSetAndGetSnow() {
        WeatherData weatherData = new WeatherData();
        TotalFall snow = new TotalFall();
        snow.set1h(10.1F);
        weatherData.setSnow(snow);

        assertEquals(snow, weatherData.getSnow());
        assertEquals(10.1F, weatherData.getSnow().get1h());
    }


}
