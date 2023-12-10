package pl.edu.agh.to2.WeatherApp.presenter.impl;

import com.google.inject.Inject;
import javafx.application.Platform;
import pl.edu.agh.to2.WeatherApp.api.DataProvider;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;
import pl.edu.agh.to2.WeatherApp.utils.TempCalculator;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

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
    public void getWeatherByCity(String city)  {
        model.getWeatherDataByCity(city).thenAccept(weatherData -> {
            Platform.runLater(() -> updateWeatherDisplay(weatherData));
        }).exceptionally(e -> {
            Platform.runLater(() -> view.setWeatherError("Error fetching weather data"));
            return null;
        });
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        model.getWeatherDataByCoordinates(lat, lon).thenAccept(weatherData -> {
            Platform.runLater(() -> updateWeatherDisplay(weatherData));
        }).exceptionally(e -> {
            Platform.runLater(() -> view.setWeatherError("Error fetching weather data"));
            return null;
        });
    }

    private void updateIconUrl(WeatherData weatherData) {
        if (weatherData.getWeather() != null && !weatherData.getWeather().isEmpty()) {
            String iconCode = weatherData.getWeather().get(0).getIcon();
            String iconUrl = DataProvider.getIconUrl(iconCode);
            weatherData.getWeather().get(0).setIcon(iconUrl);
        }
    }

    private void updateWeatherDisplay(WeatherData weatherData) {
        if (weatherData.getSys() != null) {
            String city = weatherData.getName() == null || Objects.equals(weatherData.getName(), "") ? "Unknown" : weatherData.getName();
            String country = weatherData.getSys().getCountry() == null ? "Unknown" : weatherData.getSys().getCountry();
            weatherData.setName(city);
            weatherData.getSys().setCountry(country);
            weatherData.getWind().setSpeed(round(weatherData.getWind().getSpeed(), 2));
            weatherData.getMain().setFeelsLike(round(get_feels_like(weatherData), 2));
            weatherData.getMain().setTemp(round(weatherData.getMain().getTemp(), 2));
            weatherData.getMain().setFeelsLike(round(weatherData.getMain().getFeelsLike(), 2));
            weatherData.getMain().setTempMin(round(weatherData.getMain().getTempMin(), 2));
            weatherData.getMain().setTempMax(round(weatherData.getMain().getTempMax(), 2));
        }
        updateIconUrl(weatherData);
        view.updateWeatherDisplay(weatherData);
    }

    private float round(double value, int places) {
        double scale = Math.pow(10, places);
        return (float) (Math.round(value * scale) / scale);
    }

    private double get_feels_like(WeatherData data){
        return TempCalculator.CalculatePerceivedTemp(
                data.getMain().getTemp(), data.getWind().getSpeed()) + 273; //+273 is temporary only so that the unit matches the rest of the temperatures, remove after proper conversion is done
    }
}
