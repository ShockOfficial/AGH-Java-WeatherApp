package pl.edu.agh.to2.weather_app.utils;

public class TempCalculator {
    public static double CalculatePerceivedTemp(float temp, float windSpeed) {
        double convSpeed = Math.pow(windSpeed, 0.16);
        double convTemp = temp - 273;
        return 13.12 + 0.6215 * convTemp - 11.37 * convSpeed + 0.3965 * convTemp * convSpeed;
    }
}
