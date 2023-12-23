package pl.edu.agh.to2.weather_app.model.forecast_data;

import com.google.gson.annotations.SerializedName;
import pl.edu.agh.to2.weather_app.exceptions.TimeNotFoundException;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.forecast_data.json.CityDTO;
import pl.edu.agh.to2.weather_app.model.geocoding_data.GeocodingData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class ForecastData {

    @SerializedName("list")
    private List<WeatherData> weatherList = new ArrayList<>();;
    private CityDTO city;

    private AirPollutionData airPollution;

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public AirPollutionData getAirPollution() {
        return airPollution;
    }

    public void setAirPollution(AirPollutionData airPollution) {
        this.airPollution = airPollution;
    }
    public List<WeatherData> getWeatherList() {
        return weatherList;
    }
    public void setWeatherList(List<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    public void addWeatherDataList(WeatherData data){
        this.weatherList.add(data);
    }

    public WeatherData getWeatherFromTime(String time) throws TimeNotFoundException {
        for(WeatherData weather: weatherList){
            if(weather.getTime().contains(time)){
                return weather;
            }
        }
        throw new TimeNotFoundException("Time stamp not found in forecast");
    }
}
