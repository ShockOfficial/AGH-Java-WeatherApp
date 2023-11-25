package pl.edu.agh.to2.WeatherApp.presenter;

public interface WeatherPresenter {
    void getWeatherByCity(String city);
    void getWeatherByCoordinates(String lat, String lon);
}