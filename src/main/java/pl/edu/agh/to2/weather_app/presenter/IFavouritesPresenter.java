package pl.edu.agh.to2.weather_app.presenter;

import pl.edu.agh.to2.weather_app.presenter.impl.WeatherPresenterImpl;
import pl.edu.agh.to2.weather_app.view.FavouritesView;

public interface IFavouritesPresenter {
    void setView(FavouritesView view);
    void setWeatherPresenter(WeatherPresenterImpl weatherPresenter);
    void favouriteSelectedByText(String selectedFavouriteText);

    void addToFavourites(String name, String city, String lon, String lat, String time);

    void removeFromFavourites(String favouriteText);

}