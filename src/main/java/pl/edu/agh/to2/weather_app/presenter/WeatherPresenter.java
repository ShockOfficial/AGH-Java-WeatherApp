package pl.edu.agh.to2.weather_app.presenter;

public interface WeatherPresenter {
    void getWeatherByCity(String city);
    void getWeatherByCoordinates(String lat, String lon);
    void getWeatherByCities(String cityA, String cityB); // TODO Test this
    void getWeatherByCoordinates(String latA, String lonA, String latB, String lonB); // TODO Test this
}