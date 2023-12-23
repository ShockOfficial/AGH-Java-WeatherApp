package pl.edu.agh.to2.weather_app.presenter.impl;

import com.google.inject.Inject;
import javafx.application.Platform;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.Constants;
import pl.edu.agh.to2.weather_app.utils.TempCalculator;
import pl.edu.agh.to2.weather_app.view.WeatherView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class WeatherPresenterImpl implements IWeatherPresenter {
    private final IWeatherModel model;
    private WeatherView view;
    private static final String DEFAULT_ERROR_MSG = "Error fetching weather data";
    private final WeatherDataMerger weatherMerger;
    private final DataProvider provider;

    @Inject
    public WeatherPresenterImpl(IWeatherModel model, WeatherDataMerger merger, DataProvider prov) {
        this.model = model;
        this.weatherMerger = merger;
        this.provider = prov;
    }

    public WeatherPresenterImpl(IWeatherModel model, WeatherView view, WeatherDataMerger merger, DataProvider prov) {
        this.model = model;
        this.view = view;
        this.weatherMerger = merger;
        this.provider = prov;
    }

    @Override
    public void getWeatherByCity(String city) {
        model.getWeatherDataByCity(city).thenAccept(weatherData -> Platform.runLater(() -> updateWeatherDisplay(weatherData))).exceptionally(e -> {
            Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            return null;
        });
    }

    @Override
    public void getWeatherByCities(String cityA, String cityB) {
        CompletableFuture<WeatherData> weatherDataA = model.getWeatherDataByCity(cityA);
        CompletableFuture<WeatherData> weatherDataB = model.getWeatherDataByCity(cityB);

        weatherDataA.thenCombine(weatherDataB, weatherMerger::mergeWorseWeatherData).thenAccept(worstWeatherData -> Platform.runLater(() -> updateWeatherDisplay(worstWeatherData)))
                .exceptionally(e -> {
                    Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
                    return null;
                });
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        model.getWeatherDataByCoordinates(lat, lon).thenAccept(weatherData -> Platform.runLater(() -> updateWeatherDisplay(weatherData))).exceptionally(e -> {
            Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            return null;
        });
    }

    @Override
    public void getWeatherByCoordinates(String latA, String lonA, String latB, String lonB) {
        CompletableFuture<WeatherData> weatherDataA = model.getWeatherDataByCoordinates(latA, lonA);
        CompletableFuture<WeatherData> weatherDataB = model.getWeatherDataByCoordinates(latB, lonB);

        weatherDataA.thenCombine(weatherDataB, weatherMerger::mergeWorseWeatherData).thenAccept(worstWeatherData -> Platform.runLater(() -> updateWeatherDisplay(worstWeatherData)))
                .exceptionally(e -> {
                    Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
                    return null;
                });
    }

    private void updateIconUrl(WeatherData weatherData) {
        if (weatherData.getWeather() != null && !weatherData.getWeather().isEmpty()) {
            List<String> iconCodeList = weatherData.getWeather().get(0).getIconList();
            List<String> newIconList = new ArrayList<>();

            if (iconCodeList == null) {
                String iconUrl = provider.getIconUrl(weatherData.getWeather().get(0).getIcon());
                newIconList.add(iconUrl);
            } else {
                for (String iconCode : iconCodeList) {
                    String iconUrl = provider.getIconUrl(iconCode);
                    newIconList.add(iconUrl);
                }
            }

            weatherData.getWeather().get(0).setIconList(newIconList);
        }
    }

    private void addConditionalIcons(WeatherData weatherData) {
        if (weatherData.getWeather() != null && !weatherData.getWeather().isEmpty()) {
            List<String> newIconList = new ArrayList<>();
            List<String> iconCodeList = weatherData.getWeather().get(0).getIconList();

            if (iconCodeList != null) {
                   newIconList.addAll(iconCodeList);
            }

            if (shouldMaskIconBeAdded(weatherData)) {
                newIconList.add(Constants.MASK_URL);
            }

            if (shouldUmbrellaIconBeAdded(weatherData)) {
                newIconList.add(Constants.UMBRELLA_URL);
            }

            weatherData.getWeather().get(0).setIconList(newIconList);
        }
    }

    // Update color of label displaying temperature, according to the temperature scale
    // (cold (-inf;0), medium <0;10), warm <10;20), hot <20;inf))
    private void updateTemperatureValueColor(float temperature) {
        if (temperature < 0) {
            view.setTemperatureValueClass("temperature-cold");
        } else if (temperature < 10) {
            view.setTemperatureValueClass("temperature-medium");
        } else if (temperature < 20) {
            view.setTemperatureValueClass("temperature-warm");
        } else {
            view.setTemperatureValueClass("temperature-hot");
        }
    }

    private void updateWeatherDisplay(WeatherData weatherData) {
        if (weatherData.getSys() != null) {
            String city = weatherData.getName() == null || Objects.equals(weatherData.getName(), "") ? "Unknown" : weatherData.getName();
            String country = weatherData.getSys().getCountry() == null ? "Unknown" : weatherData.getSys().getCountry();
            weatherData.setName(city);
            weatherData.getSys().setCountry(country);
            weatherData.getWind().setSpeed(round(weatherData.getWind().getSpeed(), 2));
            weatherData.getMain().setFeelsLike(round(getFeelsLike(weatherData), 2));
            weatherData.getMain().setTemp(round(weatherData.getMain().getTemp(), 2));
            weatherData.getMain().setTempMin(round(weatherData.getMain().getTempMin(), 2));
            weatherData.getMain().setTempMax(round(weatherData.getMain().getTempMax(), 2));
            updateTemperatureValueColor(weatherData.getMain().getFeelsLike());
        }
        updateIconUrl(weatherData);
        addConditionalIcons(weatherData);
        view.updateWeatherDisplay(weatherData);
    }

    private float round(double value, int places) {
        double scale = Math.pow(10, places);
        return (float) (Math.round(value * scale) / scale);
    }

    private double getFeelsLike(WeatherData data){
        return TempCalculator.calculatePerceivedTemp(
                data.getMain().getTemp(), data.getWind().getSpeed());
    }

    private boolean shouldMaskIconBeAdded(WeatherData weatherData) {
        if (weatherData.getAirPollutionData() != null) {
            return Float.parseFloat(weatherData.getAirPollutionData().getPollutionListElement().getMainInfo().getAqi()) >= 4;
        }
        return false;
    }

    private boolean shouldUmbrellaIconBeAdded(WeatherData weatherData) {
        if (weatherData.getRain() != null) {
            return weatherData.getRain().getOneH() > 0.0;
        }
        if (weatherData.getSnow() != null) {
            return weatherData.getSnow().getOneH() > 0.0;
        }
        return false;
    }

    public void setView(WeatherView view){
        this.view = view;
    }
}