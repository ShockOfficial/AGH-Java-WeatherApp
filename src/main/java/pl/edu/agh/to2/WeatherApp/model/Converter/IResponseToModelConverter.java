package pl.edu.agh.to2.WeatherApp.model.Converter;

import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

public interface IResponseToModelConverter {
    WeatherData convert(String response);
}
