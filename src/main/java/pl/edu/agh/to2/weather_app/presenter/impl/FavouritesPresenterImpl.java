package pl.edu.agh.to2.weather_app.presenter.impl;

import javafx.application.Platform;
import pl.edu.agh.to2.weather_app.persistence.favourite.Favourite;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesDao;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesList;
import pl.edu.agh.to2.weather_app.presenter.IFavouritesPresenter;
import pl.edu.agh.to2.weather_app.view.FavouritesView;

import javax.inject.Inject;


public class FavouritesPresenterImpl implements IFavouritesPresenter {
    private FavouritesView view;
    private final FavouritesDao favouritesDao;

    private WeatherPresenterImpl weatherPresenter;


    @Inject
    public FavouritesPresenterImpl(FavouritesDao dao) {
        this.favouritesDao = dao;
    }

    public void setWeatherPresenter(WeatherPresenterImpl weatherPresenter) {
        this.weatherPresenter = weatherPresenter;
    }

    @Override
    public void setView(FavouritesView view) {
        this.view = view;
        updateView();
    }

    public void updateView() {
        if (view != null) {
            FavouritesList list = favouritesDao.getList();
            view.updateFavouritesList(list.getList().stream()
                    .map(Favourite::toString)
                    .toList());
        }
    }


    private void clearInputs() {
        Platform.runLater(() -> {
            view.setCityInput("");
            view.setLatitudeInput("");
            view.setLongitudeInput("");
            view.setForecastTimeInput("");
        });
    }

    public void favouriteSelectedByText(String favouriteText) {
        Favourite selectedFavourite = favouritesDao.getList().getList().stream()
                .filter(f -> f.toString().equals(favouriteText))
                .findFirst()
                .orElse(null);

        if (selectedFavourite != null && weatherPresenter != null) {
            weatherPresenter.fillWeatherAppInputs(selectedFavourite);
        }
    }

    @Override
    public void addToFavourites(String name, String city, String lon, String lat, String time) {
        Favourite favourite;
        if (lon.isEmpty() || lat.isEmpty()) {
            favourite = new Favourite(name, city, time);
        } else {
            try {
                favourite = new Favourite(name, Float.parseFloat(lon), Float.parseFloat(lat), time);
                view.hideError();
            } catch (NumberFormatException e) {
                view.showError("Wrong coordinates format");
                return;
            }
        }
        favouritesDao.save(favourite);
        clearInputs();
        updateView();
    }

    @Override
    public void removeFromFavourites(String favouriteText) {
        Favourite toRemove = favouritesDao.getList().getList().stream()
                .filter(f -> f.toString().equals(favouriteText))
                .findFirst()
                .orElse(null);

        if (toRemove != null) {
            favouritesDao.delete(toRemove);
            updateView();
        }
    }

}