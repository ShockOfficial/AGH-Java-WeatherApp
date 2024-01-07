package pl.edu.agh.to2.weather_app.presenter.impl;

import pl.edu.agh.to2.weather_app.persistence.favourite.Favourite;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesDao;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesList;
import pl.edu.agh.to2.weather_app.presenter.IFavouritesPresenter;
import pl.edu.agh.to2.weather_app.view.FavouritesView;

import javax.inject.Inject;


public class FavouritesPresenterImpl implements IFavouritesPresenter {
    private FavouritesView view;
    private final FavouritesDao dao;

    private WeatherPresenterImpl weatherPresenter;


    @Inject
    public FavouritesPresenterImpl(FavouritesDao dao) {
        this.dao = dao;
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
            FavouritesList list = dao.getList();
            view.updateFavouritesList(list.getList().stream()
                    .map(Favourite::toString)
                    .toList());
        }
    }
    public void favouriteSelectedByText(String favouriteText) {

        Favourite selectedFavourite = dao.getList().getList().stream()
                .filter(f -> f.toString().equals(favouriteText))
                .findFirst()
                .orElse(null);

        if (selectedFavourite != null && weatherPresenter != null) {
            weatherPresenter.fillWeatherAppInputs(selectedFavourite);
        }
    }

}