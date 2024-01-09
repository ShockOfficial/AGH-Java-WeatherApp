package pl.edu.agh.to2.weather_app.model.weather_data;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirMainInfoDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WeatherDataMergerTest {

    private final WeatherDataMerger weatherMerger = new WeatherDataMerger();

    @Test
    void mergeWorseWeatherData_MergeWeatherData_ReturnsMergedData() {
        WeatherData dataA = prepareData("CityA", "CountryA", "Clear", 25.0f, 1010, 70, 5.0f, 0.0f, "01d");
        WeatherData dataB = prepareData("CityB", "CountryB", "Clouds", 22.0f, 1015, 75, 4.0f, 0.5f, "02d");
        List<WeatherDataToDisplay> dataToDisplay = List.of(new WeatherDataToDisplay(dataA), new WeatherDataToDisplay(dataB));
        WeatherDataToDisplay result = weatherMerger.mergeWorseWeatherData(dataToDisplay);

        assertEquals("CityA & CityB", result.getCityName());
        assertEquals("CountryA & CountryB", result.getCountry());
        assertEquals("Clear & Clouds", result.getWeatherParameter());
        assertEquals(1015, result.getPressure());
        assertEquals(75, result.getHumidity());
        assertEquals(5.0, result.getWindSpeed());
    }

    @Test
    void mergeWorseWeatherData_BothWeatherDataEmpty_ReturnsEmptyData() {
        WeatherData dataA = prepareData("", "", "", 0.0f, 0, 0, 0.0f, 0.0f, "");
        WeatherData dataB = prepareData("", "", "", 0.0f, 0, 0, 0.0f, 0.0f, "");
        List<WeatherDataToDisplay> dataToDisplay = List.of(new WeatherDataToDisplay(dataA), new WeatherDataToDisplay(dataB));
        WeatherDataToDisplay result = weatherMerger.mergeWorseWeatherData(dataToDisplay);

        assertEquals(0, result.getPressure());
        assertEquals(0, result.getHumidity());
        assertEquals(0.0, result.getWindSpeed());
        assertEquals(0.0, result.getRain());
    }


    WeatherData prepareData(String city, String country, String mainCondition, float temp,
                            int pressure, int humidity, float windSpeed, float snowfall, String icon) {
        WeatherData weatherData = new WeatherData();
        weatherData.setName(city);
        SysDTO sysDTO = new SysDTO();
        sysDTO.setCountry(country);
        weatherData.setSys(sysDTO);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain(mainCondition);
        weather.setIcon(icon);
        weatherData.setWeather(weather);
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        mainInfoDTO.setTemp(temp);
        mainInfoDTO.setPressure(pressure);
        mainInfoDTO.setHumidity(humidity);
        weatherData.setMain(mainInfoDTO);
        WindDTO windDTO = new WindDTO();
        windDTO.setSpeed(windSpeed);
        weatherData.setWind(windDTO);
        AirPollutionData airPollutionData = new AirPollutionData();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
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
        weatherData.getSnow().setOneH(snowfall);
        return weatherData;
    }
}
