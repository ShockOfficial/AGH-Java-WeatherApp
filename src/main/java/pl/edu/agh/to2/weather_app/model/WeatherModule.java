package pl.edu.agh.to2.weather_app.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import pl.edu.agh.to2.weather_app.logger.ConsoleSerializer;
import pl.edu.agh.to2.weather_app.logger.IMessageSerializer;
import pl.edu.agh.to2.weather_app.model.impl.WeatherModelImpl;
import pl.edu.agh.to2.weather_app.model.response_converter.GsonConverter;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;

public class WeatherModule extends AbstractModule {
    @Provides
    public IResponseToModelConverter provideResponseToModelConverter(GsonConverter conv){
        return conv;
    }

    @Provides
    public IMessageSerializer provideSerializer(ConsoleSerializer serializer){
        return serializer;
    }

    @Provides
    public WeatherModel provideModel(WeatherModelImpl model){ return model;}
}