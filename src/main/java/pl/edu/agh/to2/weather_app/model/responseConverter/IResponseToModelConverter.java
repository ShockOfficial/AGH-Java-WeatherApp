package pl.edu.agh.to2.weather_app.model.responseConverter;

import pl.edu.agh.to2.weather_app.model.airPollutionData.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.geocodingData.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convertWeather(String response);

    GeocodingData convertCoords(String response);

    AirPollutionData convertAirPollution(String response);
}
