package pl.edu.agh.to2.WeatherApp.model.Converter;

import com.google.gson.Gson;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;

public class GsonConverter implements IResponseToModelConverter{

    @Override
    public WeatherData convert(String response){
        return new Gson().fromJson(response, WeatherData.class);
    }

}
