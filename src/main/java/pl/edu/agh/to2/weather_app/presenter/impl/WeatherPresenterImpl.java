package pl.edu.agh.to2.weather_app.presenter.impl;

import com.google.inject.Inject;
import javafx.application.Platform;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.persistence.favourite.Favourite;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesDao;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataToDisplay;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.Constants;
import pl.edu.agh.to2.weather_app.utils.TempCalculator;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WeatherPresenterImpl implements IWeatherPresenter {
    private final IWeatherModel model;
    private WeatherView view;
    private static final String DEFAULT_ERROR_MSG = "Error fetching weather data";
    private final WeatherDataMerger weatherMerger;
    private final DataProvider provider;
    private FavouritesPresenterImpl favouritesPresenter;
    private final FavouritesDao favouritesDao;

    @Inject
    public WeatherPresenterImpl(IWeatherModel model, WeatherDataMerger merger, DataProvider prov, FavouritesDao dao) {
        this.model = model;
        this.weatherMerger = merger;
        this.provider = prov;
        this.favouritesDao = dao;
    }

    public WeatherPresenterImpl(IWeatherModel model, WeatherView view, WeatherDataMerger merger, DataProvider prov, FavouritesDao dao) {
        this.model = model;
        this.view = view;
        this.weatherMerger = merger;
        this.provider = prov;
        this.favouritesDao = dao;
    }

    public void setFavouritesPresenter(FavouritesPresenterImpl favouritesPresenter) {
        this.favouritesPresenter = favouritesPresenter;
    }

    @Override
    public void getWeatherByCity(String city) {
        model.getWeatherDataByCity(city).thenAccept(weatherData -> {
            if (weatherData.getSys() != null) {
                Platform.runLater(() -> updateWeatherDisplay(new WeatherDataToDisplay(weatherData)));
            } else {
                Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            }
        }).exceptionally(e -> {
            Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            return null;
        });
    }

    @Override
    public void getWeatherByCities(List<String> cities) {
        List<CompletableFuture<WeatherData>> weatherDataList = new ArrayList<>();
        cities.forEach(city -> weatherDataList.add(model.getWeatherDataByCity(city)));
        getWeather(weatherDataList);
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        model.getWeatherDataByCoordinates(lat, lon).thenAccept(weatherData -> {
            if (weatherData.getSys() != null) {
                Platform.runLater(() -> updateWeatherDisplay(new WeatherDataToDisplay(weatherData)));
            } else {
                Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            }
        }).exceptionally(e -> {
            Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            return null;
        });
    }

    @Override
    public void getWeatherByCoordinates(List<String> latList, List<String> lonList) {
        List<CompletableFuture<WeatherData>> weatherDataList = new ArrayList<>();
        for (int i = 0; i < latList.size(); i++) {
            weatherDataList.add(model.getWeatherDataByCoordinates(latList.get(i), lonList.get(i)));
        }
        getWeather(weatherDataList);
    }

    private void getWeather(List<CompletableFuture<WeatherData>> weatherDataList) {
        CompletableFuture.allOf(weatherDataList.toArray(CompletableFuture[]::new)).thenAccept(v -> {
            List<WeatherDataToDisplay> weatherDataToDisplayList = new ArrayList<>();
            for (CompletableFuture<WeatherData> weatherData : weatherDataList) {
                WeatherData wt = weatherData.join();
                if (wt.getSys() != null) {
                    weatherDataToDisplayList.add(new WeatherDataToDisplay(weatherData.join()));
                }
            }
            if (weatherDataToDisplayList.isEmpty()) {
                Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            } else {
                Platform.runLater(() -> updateWeatherDisplay(weatherMerger.mergeWorseWeatherData(weatherDataToDisplayList)));
            }
        }).exceptionally(e -> {
            Platform.runLater(() -> view.showError(DEFAULT_ERROR_MSG));
            return null;
        });
    }

    private void updateIconUrl(WeatherDataToDisplay weatherData) {
        if (weatherData.getIconList() != null) {
            List<String> iconCodeList = weatherData.getIconList();
            List<String> newIconList = new ArrayList<>();
            for (String iconCode : iconCodeList) {
                String iconUrl = provider.getIconUrl(iconCode);
                newIconList.add(iconUrl);
            }
            weatherData.setIconList(newIconList);
        }
    }

    private void addConditionalIcons(WeatherDataToDisplay weatherData) {
        List<String> newIconList = new ArrayList<>();
        List<String> iconCodeList = weatherData.getIconList();

        if (iconCodeList != null) {
            newIconList.addAll(iconCodeList);
        }

        if (shouldMaskIconBeAdded(weatherData)) {
            newIconList.add(Constants.MASK_URL);
        }

        if (shouldUmbrellaIconBeAdded(weatherData)) {
            newIconList.add(Constants.UMBRELLA_URL);
        }

        weatherData.setIconList(newIconList);
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

    private void updateWeatherDisplay(WeatherDataToDisplay weatherData) {
        weatherData.setTemperature(round(getFeelsLike(weatherData), 2));
        weatherData.setWindSpeed(round(weatherData.getWindSpeed(), 2));
        updateTemperatureValueColor(weatherData.getTemperature());
        updateIconUrl(weatherData);
        addConditionalIcons(weatherData);
        view.updateWeatherDisplay(weatherData);
    }

    private float round(double value, int places) {
        double scale = Math.pow(10, places);
        return (float) (Math.round(value * scale) / scale);
    }

    private double getFeelsLike(WeatherDataToDisplay data) {
        return TempCalculator.calculatePerceivedTemp(
                data.getTemperature(), data.getWindSpeed());
    }

    private boolean shouldMaskIconBeAdded(WeatherDataToDisplay weatherData) {
        if (!weatherData.getAirQuality().equals("Unknown")) {
            return Float.parseFloat(weatherData.getAirQuality()) >= 4;
        }
        return false;
    }

    private boolean shouldUmbrellaIconBeAdded(WeatherDataToDisplay weatherData) {
        return weatherData.getRain() > 0 || weatherData.getSnow() > 0;
    }


    @Override
    public void addFavourite(String name, String city, String lon, String lat, String time) {
        Favourite favourite;
        if (lon.isEmpty() || lat.isEmpty()) {
            favourite = new Favourite(name, city, time);
        } else {
            favourite = new Favourite(name, Float.parseFloat(lon), Float.parseFloat(lat), time);
        }
        favouritesDao.save(favourite);

        if (favouritesPresenter != null) {
            favouritesPresenter.updateView();
        }
    }

    private void clearInputs() {
        Platform.runLater(() -> {
            view.setACityInput("");
            view.setALongitudeInput("");
            view.setALatitudeInput("");
            view.setTimeInput("");
        });
    }

    public void fillWeatherAppInputs(Favourite favourite) {
        clearInputs();
        Platform.runLater(() -> {
            if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
                view.setACityInput(favourite.getCity());
            }
            if (favourite.getLon() != null) {
                view.setALongitudeInput(favourite.getLon().toString());
            }
            if (favourite.getLat() != null) {
                view.setALatitudeInput(favourite.getLat().toString());
            }
            view.setTimeInput(favourite.getTime());
        });
    }
    public void setView(WeatherView view) {
        this.view = view;
    }
}
