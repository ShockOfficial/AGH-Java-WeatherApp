package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import com.google.gson.annotations.SerializedName;

public class TotalFall {
    @SerializedName("1h")
    private float _1h;
    @SerializedName("3h")
    private float _3h;
    public float get_3h() {
        return _3h;
    }

    public void set_3h(float _3h) {
        this._3h = _3h;
    }
    public float get1h() {
        return _1h;
    }

    public void set1h(float _1h) {
        this._1h = _1h;
    }
}
