package pl.edu.agh.to2.weather_app.utils.converter;

import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;

import java.util.Map;

public class AirQualityConverter {
    private AirQualityConverter(){}

    private static final Map<String, String> pollutionMap = Map.of(
            "1", "Very good",
            "2", "Good",
            "3", "Average",
            "4", "Bad",
            "5", "Very bad");
    public static String getAirQualityString(AirPollutionData data){
        return pollutionMap.get(data.
                getPollutionListElement().
                getMainInfo().
                getAqi());
    }
}
