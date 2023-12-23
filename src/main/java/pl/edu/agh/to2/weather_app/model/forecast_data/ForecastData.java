package pl.edu.agh.to2.weather_app.model.forecast_data;

import com.google.gson.annotations.SerializedName;
import pl.edu.agh.to2.weather_app.exceptions.TimeNotFoundException;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.forecast_data.json.CityDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.weather_data.json.SysDTO;

import java.util.ArrayList;
import java.util.List;

public class ForecastData {

    @SerializedName("list")
    private List<WeatherData> weatherList = new ArrayList<>();;
    private CityDTO city;

    //Geocoding data might be unnecessary to keep since the same data is return by API in city
    //leaving this matter to discussion
    private AirPollutionData airPollution;

    public String getName(){
        return this.city.getName();
    }

    public String getCountry(){
        return this.city.getCountry();
    }

    public AirPollutionData getAirPollution() {
        return airPollution;
    }

    public void setAirPollution(AirPollutionData airPollution) {
        this.airPollution = airPollution;
    }

    public SysDTO getSys(){
        return this.weatherList.get(0).getSys();
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


    //Every forecast comes with String dt_time = "YYYY-MM-DD hh:mm:ss"
    public WeatherData getWeatherFromTime(String time) throws TimeNotFoundException {
        for(WeatherData weather: weatherList){
            if(weather.getTime().contains(time)){
                return weather;
            }
        }
        throw new TimeNotFoundException("Time stamp not found in forecast");
    }
}
