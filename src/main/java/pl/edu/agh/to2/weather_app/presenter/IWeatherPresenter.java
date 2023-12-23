package pl.edu.agh.to2.weather_app.presenter;

public interface IWeatherPresenter {
    void getWeatherByCity(String city);
    void getWeatherByCoordinates(String lat, String lon);
    void getWeatherByCities(String cityA, String cityB);
    void getWeatherByCoordinates(String latA, String lonA, String latB, String lonB);
}