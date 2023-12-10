package pl.edu.agh.to2.weather_app.model.converter;

import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convert(String response);
}
