package pl.edu.agh.to2.weather_app.model.weather_data;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @Test
    void testSetAndGetCoord() {
        // given
        WeatherData weatherData = new WeatherData();
        CoordDTO coordDTO = new CoordDTO();
        coordDTO.setLat(10);
        coordDTO.setLon(20);

        // when
        weatherData.setCoord(coordDTO);

        // then
        assertEquals(coordDTO, weatherData.getCoord());
    }

    @Test
    void testSetAndGetWeather() {
        // given
        WeatherData weatherData = new WeatherData();
        WeatherDTO weatherDTO = new WeatherDTO();

        // when
        weatherData.setWeather(weatherDTO);

        // then
        assertTrue(weatherData.getWeather().contains(weatherDTO));
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
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        mainInfoDTO.setFeelsLike(25);

        // when
        weatherData.setMain(mainInfoDTO);

        // then
        assertEquals(mainInfoDTO, weatherData.getMain());
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
        WindDTO windDTO = new WindDTO();
        windDTO.setDeg(5);
        windDTO.setGust(10);
        windDTO.setSpeed(20);

        // when
        weatherData.setWind(windDTO);

        // then
        assertEquals(windDTO, weatherData.getWind());
    }

    @Test
    void testSetAndGetRain() {
        // given
        WeatherData weatherData = new WeatherData();
        TotalFallDTO rain = new TotalFallDTO();
        rain.setOneH(10);
        rain.setThreeH(20);

        // when
        weatherData.setRain(rain);

        // then
        assertEquals(rain, weatherData.getRain());
    }

    @Test
    void testSetAndGetClouds() {
        // given
        WeatherData weatherData = new WeatherData();
        CloudsDTO cloudsDTO = new CloudsDTO();
        cloudsDTO.setAll(10);

        // when
        weatherData.setClouds(cloudsDTO);

        // then
        assertEquals(cloudsDTO, weatherData.getClouds());
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
        SysDTO sysDTO = new SysDTO();
        sysDTO.setCountry("PL");
        sysDTO.setId(1);
        sysDTO.setSunrise(1637966400L);
        sysDTO.setSunset(1637966400L);
        sysDTO.setType(1);

        // when
        weatherData.setSys(sysDTO);

        // then
        assertEquals(sysDTO, weatherData.getSys());
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
        TotalFallDTO snow = new TotalFallDTO();
        snow.setOneH(10.1F);

        // when
        weatherData.setSnow(snow);

        // then
        assertEquals(snow, weatherData.getSnow());
        assertEquals(10.1F, weatherData.getSnow().getOneH());
    }

    @Test
    void testSetAndGetGeocodingData() {
        // given
        WeatherData weatherData = new WeatherData();
        GeocodingData geocodingData = new GeocodingData();
        geocodingData.setName("Krakow");

        // when
        weatherData.setGeocodingData(geocodingData);

        // then
        assertEquals(geocodingData, weatherData.getGeocodingData());
        assertEquals("Krakow", weatherData.getGeocodingData().getName());
    }

}
