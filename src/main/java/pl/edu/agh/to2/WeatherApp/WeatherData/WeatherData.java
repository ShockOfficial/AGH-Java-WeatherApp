package pl.edu.agh.to2.WeatherApp.WeatherData;

import pl.edu.agh.to2.WeatherApp.WeatherData.JsonClasses.*;

import java.util.ArrayList;
import java.util.List;


//Detailed description at https://openweathermap.org/current
public class WeatherData {
    //name of the city
    private String name;
    //Coordinates of the place
    private Coord coord;
    //Group of weather parameters (Rain, Snow, Clouds etc.) and detailed i.e "heavy rain"
    private final List<Weather> weather = new ArrayList<>();

    //most important information temperature, feel_like, pressure, humidity
    private MainInfo main;

    //general visibilty
    private int visibility;
    //Speed(m/s), angle(deg), and gust(m/s) of the wind
    private Wind wind;

    //Rain and snow volume over the past 1h and 3h in mm(only if aplicable)
    private Rain rain;
    private Snow snow;

    //Cloudiness %
    private Clouds clouds;

    //Useful ones are sunrise and sunset times(in seconds)
    private Sys sys;


    //Internal parameters
    private int id;
    private int timezone;
    private long dt;
    private String base;


    private int cod;

//***************GETTERS AND SETTERS************************

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather.add(weather);
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    @Override
    public String toString(){
        return ""+this.main.getFeels_like();
    }


}
