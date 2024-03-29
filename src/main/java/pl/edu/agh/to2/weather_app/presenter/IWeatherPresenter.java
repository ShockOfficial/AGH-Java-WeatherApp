package pl.edu.agh.to2.weather_app.presenter;

import pl.edu.agh.to2.weather_app.presenter.impl.FavouritesPresenterImpl;
import java.util.List;

public interface IWeatherPresenter {
    void getWeatherByCity(String city);
    void getWeatherByCoordinates(String lat, String lon);
    void getWeatherByCities(List<String> cities);
    void getWeatherByCoordinates(List<String> lat, List<String> lon);
    void getForecast(List<String> cities, List<String> citiesTimes, List<String> latList, List<String> lonList, List<String> coordsTimes);
    void handleGetForecastAction();
    void onShowFavouritesAction();
}