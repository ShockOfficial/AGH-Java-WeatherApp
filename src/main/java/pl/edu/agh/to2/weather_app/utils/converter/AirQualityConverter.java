package pl.edu.agh.to2.weather_app.utils.converter;

import java.util.Map;

public class AirQualityConverter {
    private AirQualityConverter(){}

    private static final Map<String, String> pollutionMap = Map.of(
            "1", "Very good",
            "2", "Good",
            "3", "Average",
            "4", "Bad",
            "5", "Very bad");

    public static String getAirQualityString(String data){
        return pollutionMap.get(data);
    }
}
