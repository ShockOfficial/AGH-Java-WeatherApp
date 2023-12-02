package pl.edu.agh.to2.WeatherApp.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class WeatherDataProvider {
    //API key has a limit of 100 calls a day if the limit is reached replace this one
    private static final String apiKey = "09fe2451db15d2b60a0a52041ee82126";
    private static final String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
    private static final String apiKeyParamName = "appid";
    private static final String latitudeParamName = "lat";
    private static final String longitudeParamName = "lon";
    private static final String cityParamName = "q";

    //Getting weather using longitude and latitude
    public static String getWeather(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon));
        return response.body().string();                            //returning the text in the body response
    }

    //This way is technically "deprecated" but works fine
    public static String getWeather(String city) throws IOException {
        Response response = makeApiCall(Map.of(cityParamName, city));
        return response.body().string();                            //returning the text in the body response
    }

    public static Response makeApiCall(Map<String, String> params) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(apiUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        urlBuilder.addQueryParameter(apiKeyParamName, apiKey);      //api key is always required
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();                   //executing the request and getting HTTPOk response
    }
}
