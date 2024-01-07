package pl.edu.agh.to2.weather_app.presenter;

import pl.edu.agh.to2.weather_app.presenter.impl.FavouritesPresenterImpl;

public interface IWeatherPresenter {
    void getWeatherByCity(String city);
    void getWeatherByCoordinates(String lat, String lon);
    void getWeatherByCities(String cityA, String cityB);
    void getWeatherByCoordinates(String latA, String lonA, String latB, String lonB);
    void setFavouritesPresenter(FavouritesPresenterImpl favouritesPresenter);
    void addFavourite(String name, String city,String lon, String lat ,String time);

}