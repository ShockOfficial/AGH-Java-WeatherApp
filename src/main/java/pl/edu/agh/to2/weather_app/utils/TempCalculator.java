package pl.edu.agh.to2.weather_app.utils;

public class TempCalculator {
    public static double CalculatePerceivedTemp(float temp, float windSpeed) {
        windSpeed += 3.6;   // converting from m/s to km/h
        double convSpeed = Math.pow(windSpeed, 0.16);
        return 13.12 + 0.6215 * temp - 11.37 * convSpeed + 0.3965 * temp * convSpeed;
    }
}