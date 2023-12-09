package pl.edu.agh.to2.WeatherApp.utils.converter;

import pl.edu.agh.to2.WeatherApp.model.airPollutionData.AirPollutionData;

import java.util.Map;

public class AirQualityConverter {
    private static Map<String, String> pollutionMap = Map.of(
            "1", "Bardzo dobra",
            "2", "Dobra",
            "3", "Przecietna",
            "4", "Zla",
            "5", "Bardzo zla");
    public static String getAirQualityString(AirPollutionData data){
        return pollutionMap.get(data.
                getPollutionListElement().
                getMainInfo().
                getAqi());
    }
}
