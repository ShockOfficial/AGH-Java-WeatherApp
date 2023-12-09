package pl.edu.agh.to2.WeatherApp.api;

import okhttp3.Response;
import java.io.IOException;
import java.util.Map;

public class WeatherDataProvider extends APIProvider{
    private static final String apiUrl = "/data/2.5/weather";
    private static final String latitudeParamName = "lat";
    private static final String longitudeParamName = "lon";
    private static final String cityParamName = "q";

    //Getting weather using longitude and latitude
    public static String getWeather(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon), apiUrl);
        return response.body().string();                            //returning the text in the body response
    }
}
