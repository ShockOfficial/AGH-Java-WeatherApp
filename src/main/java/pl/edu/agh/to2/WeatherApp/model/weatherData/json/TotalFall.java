package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import com.google.gson.annotations.SerializedName;

public class TotalFall {
    @SerializedName("1h")
    private float oneH;
    @SerializedName("3h")
    private float threeH;

    public float getThreeH() {
        return threeH;
    }

    public void setThreeH(float threeH) {
        this.threeH = threeH;
    }

    public float getOneH() {
        return oneH;
    }

    public void setOneH(float oneH) {
        this.oneH = oneH;
    }
}
