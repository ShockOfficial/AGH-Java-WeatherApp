package pl.edu.agh.to2.weather_app.model.response_converter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

public class GsonConverter implements IResponseToModelConverter {
    private final Gson gson;

    public GsonConverter(){
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Override
    public WeatherData convertWeather(String response) {
        return gson.fromJson(response, WeatherData.class);
    }

    @Override
    public GeocodingData convertCoords(String response){
        return gson.fromJson(response, GeocodingData.class);
    }

    @Override
    public AirPollutionData convertAirPollution(String response){
        return gson.fromJson(response, AirPollutionData.class);
    }
    @Override
    public ForecastData convertForecast(String response){
        return gson.fromJson(response, ForecastData.class);
    }
}
