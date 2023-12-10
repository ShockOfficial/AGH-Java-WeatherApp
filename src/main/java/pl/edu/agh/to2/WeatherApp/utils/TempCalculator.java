package pl.edu.agh.to2.WeatherApp.utils;

public class TempCalculator {
    public static double CalculatePerceivedTemp(float temp, float wind_speed){
        double conv_speed = Math.pow(wind_speed, 0.16);
        double conv_temp = temp - 273;
        return 13.12 + 0.6215*conv_temp - 11.37*conv_speed + 0.3965*conv_temp*conv_speed;
    }
}
