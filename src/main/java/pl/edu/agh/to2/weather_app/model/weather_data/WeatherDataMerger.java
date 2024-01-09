package pl.edu.agh.to2.weather_app.model.weather_data;

import pl.edu.agh.to2.weather_app.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataMerger {
    public WeatherDataToDisplay mergeWorseWeatherData(List<WeatherDataToDisplay> dataToDisplay) {
        ArrayList<WeatherDataToDisplay> dataToDisplayCopy = new ArrayList<>(dataToDisplay);
        WeatherDataToDisplay result = dataToDisplayCopy.remove(0);
        dataToDisplayCopy.forEach(data -> mergeWorseWeatherData(result, data));
        return result;
    }

    private static void mergeWorseWeatherData(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        mergeCityName(dataA, dataB);
        mergeCountry(dataA, dataB);
        mergeWeatherParameter(dataA, dataB);
        mergeWeatherDetails(dataA, dataB);
        mergeAirQuality(dataA, dataB);
    }

    private static void mergeCityName(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getCityName().equals(Constants.VALUE_WHEN_NO_DATA)) {
            if (dataB.getCityName() != null) {
                dataA.setCityName(dataB.getCityName());
            }
        } else {
            if (dataB.getCityName() != null) {
                dataA.setCityName(dataA.getCityName() + " & " + dataB.getCityName());
            }
        }
    }

    private static void mergeCountry(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getCountry().equals(Constants.VALUE_WHEN_NO_DATA)) {
            if (dataB.getCountry() != null) {
                dataA.setCountry(dataB.getCountry());
            }
        } else {
            if (dataB.getCountry() != null) {
                dataA.setCountry(dataA.getCountry() + " & " + dataB.getCountry());
            }
        }
    }

    private static void mergeWeatherParameter(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getWeatherParameter().equals(Constants.VALUE_WHEN_NO_DATA)) {
            if (dataB.getWeatherParameter() != null) {
                dataA.setWeatherParameter(dataB.getWeatherParameter());
            }
        } else {
            if (dataB.getWeatherParameter() != null) {
                dataA.setWeatherParameter(dataA.getWeatherParameter() + " & " + dataB.getWeatherParameter());
            }
        }
    }

    private static void mergeWeatherDetails(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        dataA.setTemperature(Math.min(dataA.getTemperature(), dataB.getTemperature()));
        dataA.setPressure(Math.max(dataA.getPressure(), dataB.getPressure()));
        dataA.setHumidity(Math.max(dataA.getHumidity(), dataB.getHumidity()));
        dataA.setWindSpeed(Math.max(dataA.getWindSpeed(), dataB.getWindSpeed()));
        dataA.setRain(Math.max(dataA.getRain(), dataB.getRain()));
        dataA.setSnow(Math.max(dataA.getSnow(), dataB.getSnow()));
    }

    private static void mergeAirQuality(WeatherDataToDisplay dataA, WeatherDataToDisplay dataB) {
        if (dataA.getAirQuality().equals(Constants.VALUE_WHEN_NO_DATA)) {
            if (dataB.getAirQuality() != null) {
                dataA.setAirQuality(dataB.getAirQuality());
            }
        } else {
            if (dataB.getAirQuality() != null) {
                dataA.setAirQuality(Integer.parseInt(dataA.getAirQuality()) >= Integer.parseInt(dataB.getAirQuality()) ? dataA.getAirQuality() : dataB.getAirQuality());
            }
        }
    }
}