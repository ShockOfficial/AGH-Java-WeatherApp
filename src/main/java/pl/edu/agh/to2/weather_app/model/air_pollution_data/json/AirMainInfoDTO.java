package pl.edu.agh.to2.weather_app.model.air_pollution_data.json;

public class AirMainInfoDTO {
    //Air Quality Index, the API uses some proprietary indexing, but we don't need to show precise information
    //All that is needed is that 1 is best and 5 is worst, 3 is average
    //I would start recommending masks at 2
    private String aqi;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
}
