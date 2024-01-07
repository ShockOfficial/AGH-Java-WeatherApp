package pl.edu.agh.to2.weather_app.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.logger.FileSerializer;
import pl.edu.agh.to2.weather_app.logger.IMessageSerializer;
import pl.edu.agh.to2.weather_app.model.impl.WeatherModelImpl;
import pl.edu.agh.to2.weather_app.model.response_converter.GsonConverter;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesDao;
import pl.edu.agh.to2.weather_app.presenter.impl.FavouritesPresenterImpl;
import pl.edu.agh.to2.weather_app.presenter.impl.WeatherPresenterImpl;

public class WeatherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherPresenterImpl.class);
        bind(FavouritesPresenterImpl.class);
    }


    @Provides
    public IResponseToModelConverter provideResponseToModelConverter(GsonConverter conv){
        return conv;
    }

    @Provides
    public IMessageSerializer provideSerializer(FileSerializer serializer){
        return serializer;
    }

    @Provides
    public IWeatherModel provideModel(WeatherModelImpl model){ return model;}
}