package pl.edu.agh.to2.WeatherApp.model.Impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.api.ApiCaller;
import pl.edu.agh.to2.WeatherApp.logger.Logger;
import pl.edu.agh.to2.WeatherApp.model.Converter.IResponseToModelConverter;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;

import java.io.IOException;

public class WeatherModelImpl implements WeatherModel {

    @Inject
    private Logger logger;
    private final IResponseToModelConverter converter;

    @Inject
    public WeatherModelImpl(IResponseToModelConverter converter) {
        this.converter = converter;
    }

    @Override
    public WeatherData getWeatherDataByCity(String city) throws IOException {
        String jsonResponse = ApiCaller.getWeather(city);
        if(jsonResponse.isEmpty()){
            logger.log("Request to API failed");
        }
        logger.log("Successfully got API response for "+city);
        return converter.convert(jsonResponse);
    }

    @Override
    public WeatherData getWeatherDataByCoordinates(String lon, String lat) throws IOException {
        String jsonResponse = ApiCaller.getWeather(lon, lat);
        if(jsonResponse.isEmpty()){
            logger.log("Request to API failed");
        }
        logger.log("Successfully got API response for ("+lon+","+lat+")");
        return converter.convert(jsonResponse);
    }
}