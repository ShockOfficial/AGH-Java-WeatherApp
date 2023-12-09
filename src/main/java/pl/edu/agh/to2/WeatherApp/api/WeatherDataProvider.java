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

    //This way is technically "deprecated" but works fine
    public static String getWeather(String city) throws IOException {
        Response response = makeApiCall(Map.of(cityParamName, city), "weather");
        return response.body().string();                            //returning the text in the body response
    }

    public static String getIconUrl(String iconCode) {
        return "https://openweathermap.org/img/wn/" + iconCode + "@4x.png";
    }

    public static Response makeApiCall(Map<String, String> params, String call_type) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(apiUrl).newBuilder();
        urlBuilder.addPathSegment(call_type);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        urlBuilder.addQueryParameter(apiKeyParamName, apiKey);      //api key is always required
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();                   //executing the request and getting HTTPOk response
    }

    //The API does not support air pollution calls with city name
    public static void getAirPolution(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon), "air_pollution");
        System.out.println(response.body().string());                           //returning the text in the body response
    }
}
