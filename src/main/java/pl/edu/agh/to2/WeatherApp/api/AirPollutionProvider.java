package pl.edu.agh.to2.WeatherApp.api;

import okhttp3.Response;
import java.io.IOException;
import java.util.Map;

public class AirPollutionProvider extends APIProvider{
    private static final String apiUrl = "data/2.5/air_pollution";
    private static final String latitudeParamName = "lat";
    private static final String longitudeParamName = "lon";

    //The API does not support air pollution calls with city name
    public static String getAirPolution(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon), apiUrl);
        return response.body().string();                          //returning the text in the body response
    }
}
