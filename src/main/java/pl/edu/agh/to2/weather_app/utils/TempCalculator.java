package pl.edu.agh.to2.weather_app.utils;

public class TempCalculator {
    private TempCalculator() {}

    private static double convertWindSpeed(float speed){
        speed *= 3.6;               //converting speed from m/s to km/h
        return Math.pow(speed, 0.16);   //this specific value is required by the formula
    }

    public static double calculatePerceivedTemp(float temp, float windSpeed) {
        double convSpeed = convertWindSpeed(windSpeed);
        return 13.12 + 0.6215 * temp - 11.37 * convSpeed + 0.3965 * temp * convSpeed;
    }
}