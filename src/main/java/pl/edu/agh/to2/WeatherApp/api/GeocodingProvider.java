package pl.edu.agh.to2.WeatherApp.api;

import okhttp3.Response;
import java.io.IOException;
import java.util.Map;

public class GeocodingProvider extends APIProvider {
    private static final String apiUrl = "geo/1.0/direct";
    private static final String cityParamName = "q";
    private static final String limitParamName = "limit";
    private static final String limit = "1";


    //The API does not support air pollution calls with city name
    public static String getCoords(String city) throws IOException {
        Response response = makeApiCall(Map.of(cityParamName, city, limitParamName, limit), apiUrl);
        return response.body().string();                          //returning the text in the body response
    }

}
