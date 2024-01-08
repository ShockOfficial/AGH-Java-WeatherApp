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
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.util.ArrayList;
import java.util.LinkedList;
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
        List<String> citiesToGetForecast = new LinkedList<>();
        List<String> citiesTimes = new LinkedList<>();
        List<String> latitudesToGetForecast = new LinkedList<>();
        List<String> longitudesToGetForecast = new LinkedList<>();
        List<String> coordsTimes = new LinkedList<>();

        List<String> aInputs = new LinkedList<>(List.of(view.getACityInput(), view.getALatitudeInput(), view.getALongitudeInput(), view.getATimeInput()));
        List<String> bInputs = new LinkedList<>(List.of(view.getBCityInput(), view.getBLatitudeInput(), view.getBLongitudeInput(), view.getBTimeInput()));
        List<String> cInputs = new LinkedList<>(List.of(view.getCCityInput(), view.getCLatitudeInput(), view.getCLongitudeInput(), view.getCTimeInput()));
        List<String> dInputs = new LinkedList<>(List.of(view.getDCityInput(), view.getDLatitudeInput(), view.getDLongitudeInput(), view.getDTimeInput()));
        List<String> eInputs = new LinkedList<>(List.of(view.getECityInput(), view.getELatitudeInput(), view.getELongitudeInput(), view.getETimeInput()));

        addToListsIfDataProvided(aInputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
        addToListsIfDataProvided(bInputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
        addToListsIfDataProvided(cInputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
        addToListsIfDataProvided(dInputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
        addToListsIfDataProvided(eInputs, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);

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

    public void setView(WeatherView view) {
        this.view = view;
    }
}
