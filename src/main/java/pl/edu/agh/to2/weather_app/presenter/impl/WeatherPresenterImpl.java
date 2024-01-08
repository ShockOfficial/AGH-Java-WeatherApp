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
        boolean aCityProvided = !view.getACityInput().isEmpty();
        boolean bCityProvided = !view.getBCityInput().isEmpty();
        boolean cCityProvided = !view.getCCityInput().isEmpty();
        boolean dCityProvided = !view.getDCityInput().isEmpty();
        boolean eCityProvided = !view.getECityInput().isEmpty();
        boolean aCoordsProvided = !view.getALatitudeInput().isEmpty() && !view.getALongitudeInput().isEmpty();
        boolean bCoordsProvided = !view.getBLatitudeInput().isEmpty() && !view.getBLongitudeInput().isEmpty();
        boolean cCoordsProvided = !view.getCLatitudeInput().isEmpty() && !view.getCLongitudeInput().isEmpty();
        boolean dCoordsProvided = !view.getDLatitudeInput().isEmpty() && !view.getDLongitudeInput().isEmpty();
        boolean eCoordsProvided = !view.getELatitudeInput().isEmpty() && !view.getELongitudeInput().isEmpty();

        List<String> citiesToGetForecast = new LinkedList<>();
        List<String> citiesTimes = new LinkedList<>();

        List<String> latitudesToGetForecast = new LinkedList<>();
        List<String> longitudesToGetForecast = new LinkedList<>();
        List<String> coordsTimes = new LinkedList<>();

        addToListsIfDataProvided(aCityProvided, aCoordsProvided, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes, view.getACityInput(), view.getALatitudeInput(), view.getALongitudeInput(), view.getATimeInput());
        addToListsIfDataProvided(bCityProvided, bCoordsProvided, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes, view.getBCityInput(), view.getBLatitudeInput(), view.getBLongitudeInput(), view.getBTimeInput());
        addToListsIfDataProvided(cCityProvided, cCoordsProvided, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes, view.getCCityInput(), view.getCLatitudeInput(), view.getCLongitudeInput(), view.getCTimeInput());
        addToListsIfDataProvided(dCityProvided, dCoordsProvided, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes, view.getDCityInput(), view.getDLatitudeInput(), view.getDLongitudeInput(), view.getDTimeInput());
        addToListsIfDataProvided(eCityProvided, eCoordsProvided, citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes, view.getECityInput(), view.getELatitudeInput(), view.getELongitudeInput(), view.getETimeInput());
        getForecast(citiesToGetForecast, citiesTimes, latitudesToGetForecast, longitudesToGetForecast, coordsTimes);
    }

    private void addToListsIfDataProvided(boolean cityProvided, boolean coordsProvided, List<String> citiesToGetForecast, List<String> citiesTimes, List<String> latitudesToGetForecast, List<String> longitudesToGetForecast, List<String> coordsTimes, String inputCity, String latitudeInput, String longitudeInput, String forecastTimeInput) {
        if (cityProvided) {
            citiesToGetForecast.add(inputCity);
            citiesTimes.add(forecastTimeInput);
        } else if (coordsProvided) {
            latitudesToGetForecast.add(latitudeInput);
            longitudesToGetForecast.add(longitudeInput);
            coordsTimes.add(forecastTimeInput);
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

    private void clearInputs() {
        Platform.runLater(() -> {
            view.setACityInput("");
            view.setALongitudeInput("");
            view.setALatitudeInput("");
            view.setATimeInput("");
        });
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
            if (this.view.aInputsClear()) {
                setAInputs(favourite);
            } else if (this.view.bInputsClear()) {
                setBInputs(favourite);
            } else if (this.view.cInputsClear()) {
                setCInputs(favourite);
            } else if (this.view.dInputsClear()) {
                setDInputs(favourite);
            } else if (this.view.eInputsClear()) {
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
