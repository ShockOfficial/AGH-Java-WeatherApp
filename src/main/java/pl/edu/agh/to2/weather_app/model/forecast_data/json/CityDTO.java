package pl.edu.agh.to2.weather_app.model.forecast_data.json;

import com.google.gson.annotations.SerializedName;
import pl.edu.agh.to2.weather_app.model.weather_data.json.CoordDTO;

public class CityDTO {
    private String name;
    @SerializedName("coord")
    private CoordDTO coordinates;
    private String country;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordDTO getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordDTO coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
