package pl.edu.agh.to2.weather_app.model.weather_data;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataToDisplay {
    public WeatherDataToDisplay(WeatherData weatherData) {
        this.cityName = weatherData.getName();
        this.country = weatherData.getSys().getCountry();
        this.weatherParameter = weatherData.getWeather().get(0).getMain();
        this.temperature = weatherData.getMain().getTemp();
        this.feelsLike = weatherData.getMain().getFeelsLike();
        this.pressure = weatherData.getMain().getPressure();
        this.humidity = weatherData.getMain().getHumidity();
        this.windSpeed = weatherData.getWind().getSpeed();
        this.airQuality = weatherData.getAirPollutionData().getPollutionListElement().getMainInfo().getAqi();
        this.icon = weatherData.getWeather().get(0).getIcon();
    }

    private String cityName;
    private String country;
    private String weatherParameter;
    private float temperature;
    private float feelsLike;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private String airQuality;
    private String icon;
    private List<String> iconList;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeatherParameter() {
        return weatherParameter;
    }

    public void setWeatherParameter(String weatherParameter) {
        this.weatherParameter = weatherParameter;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getIconList() {
        return iconList;
    }

    public void setIconList(List<String> iconList) {
        this.iconList = iconList;
    }

    public void addIconToList(String icon) {
        if (this.iconList == null) {
            this.iconList = new ArrayList<>();
        }
        this.iconList.add(icon);
    }
}
