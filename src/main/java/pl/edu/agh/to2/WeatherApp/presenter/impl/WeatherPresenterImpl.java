package pl.edu.agh.to2.WeatherApp.presenter.impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

import java.io.IOException;

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherModel model;
    private final WeatherView view;

    @Inject
    public WeatherPresenterImpl(WeatherModel model, WeatherView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getWeatherByCity(String city) {
        try {
            WeatherData weatherData = model.getWeatherDataByCity(city);
            view.updateWeatherDisplay(weatherData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        try {
            WeatherData weatherData = model.getWeatherDataByCoordinates(lon, lat);
            view.updateWeatherDisplay(weatherData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
