package pl.edu.agh.to2.weather_app.model.weather_data;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirMainInfoDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;
import pl.edu.agh.to2.weather_app.utils.TempCalculator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherDataToDisplayTest {

    @Mock
    private WeatherData weatherData;
    void prepareData(){
        weatherData = new WeatherData();
        weatherData.setName("Krakow");
        SysDTO sysDTO = new SysDTO();
        sysDTO.setCountry("Poland");
        weatherData.setSys(sysDTO);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain("Clouds");
        weather.setIcon("04d");
        weatherData.setWeather(weather);
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        mainInfoDTO.setTemp((float) TempCalculator.calculatePerceivedTemp(10F,10F));
        mainInfoDTO.setFeelsLike(10);
        mainInfoDTO.setPressure(1000);
        mainInfoDTO.setHumidity(50);
        weatherData.setMain(mainInfoDTO);
        WindDTO windDTO = new WindDTO();
        windDTO.setSpeed(10);
        weatherData.setWind(windDTO);
        AirPollutionData airPollutionData = new AirPollutionData();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
        airMainInfoDTO.setAqi("Good");
        airListElementDTO.setMainInfo(airMainInfoDTO);
        airPollutionData.setPollutionList(List.of(airListElementDTO));
        weatherData.setAirPollutionData(airPollutionData);
        TotalFallDTO totalFallDTO = new TotalFallDTO();
        totalFallDTO.setOneH(0);
        weatherData.setRain(totalFallDTO);
        TotalFallDTO totalFallDTO1 = new TotalFallDTO();
        totalFallDTO1.setOneH(10);
        weatherData.setSnow(totalFallDTO1);
        weatherData.getRain().setOneH(0);
        weatherData.getSnow().setOneH(10);

    }

    @Test
    void testGetCityName() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals("Krakow", weatherDataToDisplay.getCityName());
    }

    @Test
    void testGetCountry() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals("Poland", weatherDataToDisplay.getCountry());
    }

    @Test
    void testGetWeatherParameter() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals("Clouds", weatherDataToDisplay.getWeatherParameter());
    }

    @Test
    void testGetTemperature() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(1.15, Math.round(weatherDataToDisplay.getTemperature()),2);
    }

    @Test
    void testGetPressure() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(1000, weatherDataToDisplay.getPressure());
    }

    @Test
    void testGetHumidity() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(50, weatherDataToDisplay.getHumidity());
    }

    @Test
    void testGetWindSpeed() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(10, weatherDataToDisplay.getWindSpeed());
    }

    @Test
    void testGetAirQuality() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals("Good", weatherDataToDisplay.getAirQuality());
    }

    @Test
    void testGetRain() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(0, weatherDataToDisplay.getRain());
    }

    @Test
    void testGetSnow() {
        prepareData();
        WeatherDataToDisplay weatherDataToDisplay = new WeatherDataToDisplay(weatherData);
        assertEquals(10, weatherDataToDisplay.getSnow());
    }

}
