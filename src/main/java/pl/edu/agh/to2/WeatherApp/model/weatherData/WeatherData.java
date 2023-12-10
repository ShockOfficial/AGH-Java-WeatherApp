package pl.edu.agh.to2.WeatherApp.model.weatherData;

import pl.edu.agh.to2.WeatherApp.model.airPollutionData.AirPollutionData;
import pl.edu.agh.to2.WeatherApp.model.geocodingData.GeocodingData;
import pl.edu.agh.to2.WeatherApp.model.weatherData.json.*;

import java.util.ArrayList;
import java.util.List;


//Detailed description at https://openweathermap.org/current
public class WeatherData {


    //name of the city
    private GeocodingData geocodingData = new GeocodingData();
    private AirPollutionData airPollutionData;
    //Coordinates of the place
    private CoordDTO coord;
    //Group of weather parameters (Rain, Snow, Clouds etc.) and detailed i.e "heavy rain"
    private final List<WeatherDTO> weather = new ArrayList<>();

    //most important information temperature, feelsLike, pressure, humidity
    private MainInfoDTO main;

    //general visibilty
    private int visibility;
    //Speed(m/s), angle(deg), and gust(m/s) of the wind
    private WindDTO wind;

    //Rain and snow volume over the past 1h and 3h in mm(only if applicable)
    private TotalFallDTO rain;
    private TotalFallDTO snow;

    //Cloudiness %
    private CloudsDTO clouds;

    //Useful ones are sunrise and sunset times(in seconds)
    private SysDTO sys;


    //Internal parameters
    private int id;
    private int timezone;
    private long dt;
    private String base;
    private int cod;

//***************GETTERS AND SETTERS************************

    public CoordDTO getCoord() {
        return coord;
    }

    public void setCoord(CoordDTO coord) {
        this.coord = coord;
    }

    public List<WeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(WeatherDTO weather) {
        this.weather.add(weather);
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainInfoDTO getMain() {
        return main;
    }

    public void setMain(MainInfoDTO main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public TotalFallDTO getRain() {
        return rain;
    }

    public void setRain(TotalFallDTO rain) {
        this.rain = rain;
    }

    public CloudsDTO getClouds() {
        return clouds;
    }

    public void setClouds(CloudsDTO clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public SysDTO getSys() {
        return sys;
    }

    public void setSys(SysDTO sys) {
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
        return geocodingData.getName();
    }

    public void setName(String name) {
        this.geocodingData.setName(name);
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public TotalFallDTO getSnow() {
        return snow;
    }

    public void setSnow(TotalFallDTO snow) {
        this.snow = snow;
    }

    public GeocodingData getGeocodingData() {
        return geocodingData;
    }

    public void setGeocodingData(GeocodingData geocodingData) {
        this.geocodingData = geocodingData;
    }

    public AirPollutionData getAirPollutionData() {
        return airPollutionData;
    }

    public void setAirPollutionData(AirPollutionData airPollutionData) {
        this.airPollutionData = airPollutionData;
    }

    @Override
    public String toString(){
        return ""+this.main.getFeelsLike();
    }


}
