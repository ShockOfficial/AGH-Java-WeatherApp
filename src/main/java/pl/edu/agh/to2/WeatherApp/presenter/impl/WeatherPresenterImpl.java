package pl.edu.agh.to2.WeatherApp.presenter.impl;

import pl.edu.agh.to2.WeatherApp.model.WeatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

import java.io.IOException;

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherModel model;
    private final WeatherView view;

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
            view.setWeatherOutputInformer("Weather in " + weatherData.getName() + " (" + weatherData.getSys().getCountry() + "): " + weatherData.getWeather().get(0).getMain() + "\n");

            view.setCloudinessValue(Integer.toString(weatherData.getClouds().getAll()));
            view.setPressureValue(Integer.toString(weatherData.getMain().getPressure()));
            view.setHumidityValue(Integer.toString(weatherData.getMain().getHumidity()));
            view.setWindValue(Double.toString(round(weatherData.getWind().getSpeed(), 2)));

            if (weatherData.getRain() != null) {
                view.setRainValue(Double.toString(weatherData.getRain().get1h()));
            } else {
                view.setRainValue("Unknown");
            }

            if (weatherData.getSnow() != null) {
                view.setSnowValue(Double.toString(weatherData.getSnow().get1h()));
            } else {
                view.setSnowValue("Unknown");
            }

            view.setTemperatureValue(Double.toString(round(weatherData.getMain().getTemp(), 2)));
            view.setSensedTemperatureValue(Double.toString(round(weatherData.getMain().getFeels_like(), 2)));
            view.setMinimumTemperatureValue(Double.toString(round(weatherData.getMain().getTemp_min(), 2)));
            view.setMaximumTemperatureValue(Double.toString(round(weatherData.getMain().getTemp_max(), 2)));

            if (!view.isWeatherDisplaying()) {
                view.setWeatherDisplaying(true);
            }
        } else {
            view.setWeatherError("City not found");
            view.setWeatherDisplaying(false);
        }
    }

    private double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
