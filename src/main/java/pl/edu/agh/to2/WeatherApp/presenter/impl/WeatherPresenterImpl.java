package pl.edu.agh.to2.WeatherApp.presenter.impl;

import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherModel model;
    private final WeatherView view;

    public WeatherPresenterImpl(WeatherModel model, WeatherView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getWeatherByCity(String city) {
        // TODO
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        // TODO
    }
}
