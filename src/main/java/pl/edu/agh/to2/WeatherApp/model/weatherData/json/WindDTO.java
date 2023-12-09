package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

public class WindDTO {
    private float speed;
    private int deg;
    private float gust;

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}