package pl.edu.agh.to2.WeatherApp.presenter.impl;

import com.google.inject.Inject;
import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

import java.io.IOException;
import java.util.Objects;

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
            updateWeatherDisplay(weatherData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        try {
            WeatherData weatherData = model.getWeatherDataByCoordinates(lon, lat);
            updateWeatherDisplay(weatherData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateWeatherDisplay(WeatherData weatherData) {
        if (weatherData.getSys() != null) {
            String city = weatherData.getName() == null || Objects.equals(weatherData.getName(), "") ? "Unknown" : weatherData.getName();
            String country = weatherData.getSys().getCountry() == null ? "Unknown" : weatherData.getSys().getCountry();
            weatherData.setName(city);
            weatherData.getSys().setCountry(country);
            weatherData.getWind().setSpeed(round(weatherData.getWind().getSpeed(), 2));
            weatherData.getMain().setFeels_like(round(weatherData.getMain().getFeels_like(), 2));
            weatherData.getMain().setTemp(round(weatherData.getMain().getTemp(), 2));
            weatherData.getMain().setFeels_like(round(weatherData.getMain().getFeels_like(), 2));
            weatherData.getMain().setTemp_min(round(weatherData.getMain().getTemp_min(), 2));
            weatherData.getMain().setTemp_max(round(weatherData.getMain().getTemp_max(), 2));
        }
        view.updateWeatherDisplay(weatherData);
    }

    private float round(double value, int places) {
        double scale = Math.pow(10, places);
        return (float) (Math.round(value * scale) / scale);
    }
}
