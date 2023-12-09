package pl.edu.agh.to2.WeatherApp.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import pl.edu.agh.to2.WeatherApp.logger.ConsoleSerializer;
import pl.edu.agh.to2.WeatherApp.logger.IMessageSerializer;
import pl.edu.agh.to2.WeatherApp.model.converter.GsonConverter;
import pl.edu.agh.to2.WeatherApp.model.converter.IResponseToModelConverter;


public class WeatherModule extends AbstractModule {
    @Provides
    public IResponseToModelConverter provideResponseToModelConverter(GsonConverter conv){
        return conv;
    }

    @Provides
    public IMessageSerializer provideSerializer(ConsoleSerializer serializer){
        return serializer;
    }
}