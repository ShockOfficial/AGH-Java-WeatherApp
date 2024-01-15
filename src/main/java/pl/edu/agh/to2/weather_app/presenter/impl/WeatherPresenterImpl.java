package pl.edu.agh.to2.weather_app.presenter.impl;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.logger.Logger;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.persistence.favourite.Favourite;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataToDisplay;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.Constants;
import pl.edu.agh.to2.weather_app.utils.FXMLLoaderUtility;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class WeatherPresenterImpl implements IWeatherPresenter {
    private final IWeatherModel model;
    private WeatherView view;
    private static final String DEFAULT_ERROR_MSG = "Error fetching weather data";
    private final WeatherDataMerger weatherMerger;
    private final DataProvider provider;

    private final Logger logger = Logger.getInstance();


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

    public void getForecast(List<String> cities, List<String> citiesTimes, List<String> latList, List<String> lonList, List<String> coordsTimes) {
        List<CompletableFuture<WeatherData>> weatherDataList = new ArrayList<>();
        for (int i = 0; i < latList.size(); i++) {
            weatherDataList.add(model.getForecastDataByCoordinates(latList.get(i), lonList.get(i), coordsTimes.get(i)));
        }
        for (int i = 0; i < cities.size(); i++) {
            weatherDataList.add(model.getForecastDataByCity(cities.get(i), citiesTimes.get(i)));
        }
        getWeather(weatherDataList);
    }

    public void handleGetForecastAction() {
        List<String> citiesToGetForecast = new ArrayList<>();
        List<String> citiesTimes = new ArrayList<>();
        List<String> latitudesToGetForecast = new ArrayList<>();
        List<String> longitudesToGetForecast = new ArrayList<>();
        List<String> coordsTimes = new ArrayList<>();

        List<Function<WeatherView, List<String>>> inputGetters = List.of(
                v -> List.of(v.getACityInput(), v.getALatitudeInput(), v.getALongitudeInput(), v.getATimeInput()),
                v -> List.of(v.getBCityInput(), v.getBLatitudeInput(), v.getBLongitudeInput(), v.getBTimeInput()),
                v -> List.of(v.getCCityInput(), v.getCLatitudeInput(), v.getCLongitudeInput(), v.getCTimeInput()),
                v -> List.of(v.getDCityInput(), v.getDLatitudeInput(), v.getDLongitudeInput(), v.getDTimeInput()),
                v -> List.of(v.getECityInput(), v.getELatitudeInput(), v.getELongitudeInput(), v.getETimeInput())
        );

        for (Function<WeatherView, List<String>> inputGetter : inputGetters) {
            List<String> inputs = inputGetter.apply(view);
            addToListsIfDataProvided(inputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
        }

        getForecast(citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
    }

    private void addToListsIfDataProvided(List<String> inputs, List<String> citiesToGetForecast, List<String> citiesTimes, List<String> latitudesToGetForecast, List<String> longitudesToGetForecast, List<String> coordsTimes) {
        boolean cityProvided = !inputs.get(0).isEmpty();
        boolean coordsProvided = !inputs.get(1).isEmpty() && !inputs.get(2).isEmpty();
        if (cityProvided) {
            citiesToGetForecast.add(inputs.get(0));
            citiesTimes.add(inputs.get(3));
        } else if (coordsProvided) {
            latitudesToGetForecast.add(inputs.get(1));
            longitudesToGetForecast.add(inputs.get(2));
            coordsTimes.add(inputs.get(3));
        }
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
    // (cold (-inf;0), medium <0;10), warm <10;20), hot <20;30), very hot <30;+inf)
    private void updateTemperatureValueColor(float temperature) {
        if (temperature < 0) {
            view.setTemperatureValueClass("temperature-cold");
        } else if (temperature < 10) {
            view.setTemperatureValueClass("temperature-medium");
        } else if (temperature < 20) {
            view.setTemperatureValueClass("temperature-warm");
        } else if (temperature < 30) {
            view.setTemperatureValueClass("temperature-hot");
        } else {
            view.setTemperatureValueClass("temperature-very-hot");
        }
    }

    private void updateWeatherDisplay(WeatherDataToDisplay weatherData) {
        weatherData.setTemperature(round(weatherData.getTemperature(), 2));
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

    private boolean shouldMaskIconBeAdded(WeatherDataToDisplay weatherData) {
        if (!weatherData.getAirQuality().equals(Constants.VALUE_WHEN_NO_DATA)) {
            return Float.parseFloat(weatherData.getAirQuality()) >= 4;
        }
        return false;
    }

    private boolean shouldUmbrellaIconBeAdded(WeatherDataToDisplay weatherData) {
        return weatherData.getRain() > 0 || weatherData.getSnow() > 0;
    }

    private void setAInputs(Favourite favourite) {
        if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
            view.setACityInput(favourite.getCity());
        }
        if (favourite.getLon() != null) {
            view.setALongitudeInput(favourite.getLon().toString());
        }
        if (favourite.getLat() != null) {
            view.setALatitudeInput(favourite.getLat().toString());
        }
        view.setATimeInput(favourite.getTime());
    }

    private void setBInputs(Favourite favourite) {
        if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
            view.setBCityInput(favourite.getCity());
        }
        if (favourite.getLon() != null) {
            view.setBLongitudeInput(favourite.getLon().toString());
        }
        if (favourite.getLat() != null) {
            view.setBLatitudeInput(favourite.getLat().toString());
        }
        view.setBTimeInput(favourite.getTime());
    }

    private void setCInputs(Favourite favourite) {
        if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
            view.setCCityInput(favourite.getCity());
        }
        if (favourite.getLon() != null) {
            view.setCLongitudeInput(favourite.getLon().toString());
        }
        if (favourite.getLat() != null) {
            view.setCLatitudeInput(favourite.getLat().toString());
        }
        view.setCTimeInput(favourite.getTime());
    }

    private void setDInputs(Favourite favourite) {
        if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
            view.setDCityInput(favourite.getCity());
        }
        if (favourite.getLon() != null) {
            view.setDLongitudeInput(favourite.getLon().toString());
        }
        if (favourite.getLat() != null) {
            view.setDLatitudeInput(favourite.getLat().toString());
        }
        view.setDTimeInput(favourite.getTime());
    }

    private void setEInputs(Favourite favourite) {
        if (favourite.getCity() != null && !favourite.getCity().isEmpty()) {
            view.setECityInput(favourite.getCity());
        }
        if (favourite.getLon() != null) {
            view.setELongitudeInput(favourite.getLon().toString());
        }
        if (favourite.getLat() != null) {
            view.setELatitudeInput(favourite.getLat().toString());
        }
        view.setETimeInput(favourite.getTime());
    }

    public void fillWeatherAppInputs(Favourite favourite) {
        Platform.runLater(() -> {
            if (this.view.areAInputsClear()) {
                setAInputs(favourite);
            } else if (this.view.areBInputsClear()) {
                setBInputs(favourite);
            } else if (this.view.areCInputsClear()) {
                setCInputs(favourite);
            } else if (this.view.areDInputsClear()) {
                setDInputs(favourite);
            } else if (this.view.areEInputsClear()) {
                setEInputs(favourite);
            } else {
                this.view.showError("All inputs are filled");
            }
        });
    }

    @Override
    public void onShowFavouritesAction() {
        try {
            Parent favouritesView = FXMLLoaderUtility.loadFavouritesView();
            Stage stage = new Stage();
            stage.setScene(new Scene(favouritesView));
            stage.show();
        } catch (IOException e) {
            logger.log("Error loading favourites view");
        }
    }

    public void setView(WeatherView view) {
        this.view = view;
    }
}
