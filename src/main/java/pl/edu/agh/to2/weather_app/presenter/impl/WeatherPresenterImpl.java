package pl.edu.agh.to2.weather_app.presenter.impl;

import com.google.inject.Inject;
import javafx.application.Platform;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;
import pl.edu.agh.to2.weather_app.model.WeatherModel;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.presenter.WeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.TempCalculator;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherModel model;
    private final WeatherView view;
    private static String maskURL = "https://cdn-icons-png.flaticon.com/512/3579/3579773.png";
    private static final String DEFAULT_ERROR_MSG = "Error fetching weather data";

    @Inject
    public WeatherPresenterImpl(WeatherModel model, WeatherView view) {
        this.model = model;
        this.view = view;
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

        weatherDataA.thenCombine(weatherDataB, WeatherDataMerger::mergeWorseWeatherData).thenAccept(worstWeatherData -> Platform.runLater(() -> updateWeatherDisplay(worstWeatherData)))
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

        weatherDataA.thenCombine(weatherDataB, WeatherDataMerger::mergeWorseWeatherData).thenAccept(worstWeatherData -> Platform.runLater(() -> updateWeatherDisplay(worstWeatherData)))
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
                String iconUrl = DataProvider.getIconUrl(weatherData.getWeather().get(0).getIcon());
                newIconList.add(iconUrl);
                if (weatherData.getAirPollutionData()!=null){
                    String poll = weatherData.getAirPollutionData().getPollutionListElement().getMainInfo().getAqi();
                    if (Float.parseFloat(poll) >= 2){
                        newIconList.add(maskURL);
                    }
                }
            } else {
                for (String iconCode : iconCodeList) {
                    if (Objects.equals(iconCode, "mask")){
                        newIconList.add(maskURL);
                    }
                    String iconUrl = DataProvider.getIconUrl(iconCode);
                    newIconList.add(iconUrl);
                }
            }

            weatherData.getWeather().get(0).setIconList(newIconList);
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

    private double getFeelsLike(WeatherData data){
        return TempCalculator.CalculatePerceivedTemp(
                data.getMain().getTemp(), data.getWind().getSpeed()) + 273; //+273 is temporary only so that the unit matches the rest of the temperatures, remove after proper conversion is done
    }

}
