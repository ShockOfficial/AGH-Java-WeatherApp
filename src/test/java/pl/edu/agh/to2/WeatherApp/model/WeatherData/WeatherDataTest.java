package pl.edu.agh.to2.WeatherApp.model.WeatherData;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses.*;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @Test
    void testSetAndGetCoord() {
        // given
        WeatherData weatherData = new WeatherData();
        Coord coord = new Coord();
        coord.setLat(10);
        coord.setLon(20);

        // when
        weatherData.setCoord(coord);

        // then
        assertEquals(coord, weatherData.getCoord());
    }

    @Test
    void testSetAndGetWeather() {
        // given
        WeatherData weatherData = new WeatherData();
        Weather weather = new Weather();

        // when
        weatherData.setWeather(weather);

        // then
        assertTrue(weatherData.getWeather().contains(weather));
    }

    @Test
    void testSetAndGetBase() {
        // given
        WeatherData weatherData = new WeatherData();
        String base = "testBase";

        // when
        weatherData.setBase(base);

        // then
        assertEquals(base, weatherData.getBase());
    }

    @Test
    void testSetAndGetMain() {
        // given
        WeatherData weatherData = new WeatherData();
        MainInfo mainInfo = new MainInfo();
        mainInfo.setFeels_like(25);

        // when
        weatherData.setMain(mainInfo);

        // then
        assertEquals(mainInfo, weatherData.getMain());
    }

    @Test
    void testSetAndGetVisibility() {
        // given
        WeatherData weatherData = new WeatherData();
        int visibility = 1000;

        // when
        weatherData.setVisibility(visibility);

        // then
        assertEquals(visibility, weatherData.getVisibility());
    }

    @Test
    void testSetAndGetWind() {
        // given
        WeatherData weatherData = new WeatherData();
        Wind wind = new Wind();
        wind.setDeg(5);
        wind.setGust(10);
        wind.setSpeed(20);

        // when
        weatherData.setWind(wind);

        // then
        assertEquals(wind, weatherData.getWind());
    }

    @Test
    void testSetAndGetRain() {
        // given
        WeatherData weatherData = new WeatherData();
        TotalFall rain = new TotalFall();
        rain.set1h(10);
        rain.set_3h(20);

        // when
        weatherData.setRain(rain);

        // then
        assertEquals(rain, weatherData.getRain());
    }

    @Test
    void testSetAndGetClouds() {
        // given
        WeatherData weatherData = new WeatherData();
        Clouds clouds = new Clouds();
        clouds.setAll(10);

        // when
        weatherData.setClouds(clouds);

        // then
        assertEquals(clouds, weatherData.getClouds());
    }

    @Test
    void testSetAndGetDt() {
        // given
        WeatherData weatherData = new WeatherData();
        long dt = 1637966400L;

        // when
        weatherData.setDt(dt);

        // then
        assertEquals(dt, weatherData.getDt());
    }

    @Test
    void testSetAndGetSys() {
        // given
        WeatherData weatherData = new WeatherData();
        Sys sys = new Sys();
        sys.setCountry("PL");
        sys.setId(1);
        sys.setSunrise(1637966400L);
        sys.setSunset(1637966400L);
        sys.setType(1);

        // when
        weatherData.setSys(sys);

        // then
        assertEquals(sys, weatherData.getSys());
    }

    @Test
    void testSetAndGetTimezone() {
        // given
        WeatherData weatherData = new WeatherData();
        int timezone = 7200;

        // when
        weatherData.setTimezone(timezone);

        // then
        assertEquals(timezone, weatherData.getTimezone());
    }

    @Test
    void testSetAndGetId() {
        // given
        WeatherData weatherData = new WeatherData();
        int id = 123;

        // when
        weatherData.setId(id);

        // then
        assertEquals(id, weatherData.getId());
    }

    @Test
    void testSetAndGetName() {
        // given
        WeatherData weatherData = new WeatherData();
        String name = "Krakow";

        // when
        weatherData.setName(name);

        // then
        assertEquals(name, weatherData.getName());
    }

    @Test
    void testSetAndGetCod() {
        // given
        WeatherData weatherData = new WeatherData();
        int cod = 200;

        // when
        weatherData.setCod(cod);

        // then
        assertEquals(cod, weatherData.getCod());
    }

    @Test
    void testSetAndGetSnow() {
        // given
        WeatherData weatherData = new WeatherData();
        TotalFall snow = new TotalFall();
        snow.set1h(10.1F);

        // when
        weatherData.setSnow(snow);

        // then
        assertEquals(snow, weatherData.getSnow());
        assertEquals(10.1F, weatherData.getSnow().get1h());
    }
}
