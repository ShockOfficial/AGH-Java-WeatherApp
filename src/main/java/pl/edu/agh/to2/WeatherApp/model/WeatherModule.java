package pl.edu.agh.to2.WeatherApp.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import pl.edu.agh.to2.WeatherApp.model.Converter.GsonConverter;
import pl.edu.agh.to2.WeatherApp.model.Converter.IResponseToModelConverter;

public class WeatherModule extends AbstractModule {
    @Provides
    public IResponseToModelConverter provideResponseToModelConverter(GsonConverter conv){
        return conv;
    }
}