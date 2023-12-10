package pl.edu.agh.to2.WeatherApp.model.responseConverter;

import pl.edu.agh.to2.WeatherApp.model.airPollutionData.AirPollutionData;
import pl.edu.agh.to2.WeatherApp.model.geocodingData.GeocodingData;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convertWeather(String response);

    GeocodingData convertCoords(String response);

    AirPollutionData convertAirPollution(String response);
}
