package pl.edu.agh.to2.WeatherApp.model.converter;

import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convert(String response);
}
