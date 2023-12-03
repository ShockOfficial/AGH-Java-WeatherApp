package pl.edu.agh.to2.WeatherApp.model.converter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;

public class GsonConverter implements IResponseToModelConverter{

    @Override
    public WeatherData convert(String response) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.fromJson(response, WeatherData.class);
    }

}
