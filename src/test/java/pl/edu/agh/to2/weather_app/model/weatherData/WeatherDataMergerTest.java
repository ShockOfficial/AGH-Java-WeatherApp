package pl.edu.agh.to2.weather_app.model.weatherData;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;
import pl.edu.agh.to2.weather_app.model.weatherData.json.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WeatherDataMergerTest {

    @Test
    void mergeWorseWeatherData_OneNull_ReturnsNonNullOriginal() {
        WeatherData dataA = new WeatherData();
        WeatherData result = WeatherDataMerger.mergeWorseWeatherData(dataA, null);

        assertEquals(dataA, result);

        result = WeatherDataMerger.mergeWorseWeatherData(null, dataA);
        assertEquals(dataA, result);
    }

    @Test
    void mergeWorseWeatherData_MergeWeatherData_ReturnsMergedData() {
        WeatherData dataA = createSampleWeatherData("CityA", "CountryA", "Clear", 25.0f, 1010, 70, 5.0f, 0.0f, "01d");
        WeatherData dataB = createSampleWeatherData("CityB", "CountryB", "Clouds", 22.0f, 1015, 75, 4.0f, 0.5f, "02d");

        WeatherData result = WeatherDataMerger.mergeWorseWeatherData(dataA, dataB);

        assertEquals("CityA & CityB", result.getName());
        assertEquals("CountryA & CountryB", result.getSys().getCountry());
        assertEquals("Clear & Clouds", result.getWeather().get(0).getMain());
        assertEquals(22.0, result.getMain().getTemp());
        assertEquals(1015, result.getMain().getPressure());
        assertEquals(75, result.getMain().getHumidity());
        assertEquals(5.0, result.getWind().getSpeed());
        assertEquals(0.5, result.getSnow().getOneH());
    }

    @Test
    void mergeWorseWeatherData_OnlyOneWeatherData_ReturnsSameData() {
        WeatherData dataA = createSampleWeatherData("CityA", "CountryA", "Clear", 25.0f, 1010, 70, 5.0f, 0.0f, "01d");

        WeatherData result = WeatherDataMerger.mergeWorseWeatherData(dataA, null);

        assertEquals(dataA, result);

        result = WeatherDataMerger.mergeWorseWeatherData(null, dataA);
        assertEquals(dataA, result);
    }

    @Test
    void mergeWorseWeatherData_BothWeatherDataEmpty_ReturnsEmptyData() {
        WeatherData dataA = createSampleWeatherData("", "", "", 0.0f, 0, 0, 0.0f, 0.0f, "");
        WeatherData dataB = createSampleWeatherData("", "", "", 0.0f, 0, 0, 0.0f, 0.0f, "");

        WeatherData result = WeatherDataMerger.mergeWorseWeatherData(dataA, dataB);

        assertEquals("", result.getName());
        assertEquals("", result.getSys().getCountry());
        assertEquals("", result.getWeather().get(0).getMain());
        assertEquals(0.0, result.getMain().getTemp());
        assertEquals(0, result.getMain().getPressure());
        assertEquals(0, result.getMain().getHumidity());
        assertEquals(0.0, result.getWind().getSpeed());
        assertEquals(0.0, result.getSnow().getOneH());
    }

    private WeatherData createSampleWeatherData(String city, String country, String mainCondition, float temp,
                                                int pressure, int humidity, float windSpeed, float snowfall, String icon) {
        WeatherData data = new WeatherData();
        data.setName(city);
        data.setSys(new SysDTO());
        data.getSys().setCountry(country);
        data.setMain(new MainInfoDTO());
        data.getMain().setTemp(temp);
        data.getMain().setPressure(pressure);
        data.getMain().setHumidity(humidity);
        data.setWind(new WindDTO());
        data.getWind().setSpeed(windSpeed);
        data.setSnow(new TotalFallDTO());
        data.getSnow().setOneH(snowfall);
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setMain(mainCondition);
        weatherDTO.setIcon(icon);
        data.getWeather().add(weatherDTO);
        return data;
    }
}
