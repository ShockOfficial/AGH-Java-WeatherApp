package pl.edu.agh.to2.WeatherApp.model.Converter;

import okhttp3.Response;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

public interface IResponseToModelConverter {
    public WeatherData convert(String response);
}
