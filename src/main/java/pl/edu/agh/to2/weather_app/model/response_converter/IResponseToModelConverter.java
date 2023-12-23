package pl.edu.agh.to2.weather_app.model.response_converter;

import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convertWeather(String response);

    GeocodingData convertCoords(String response);

    AirPollutionData convertAirPollution(String response);
}
