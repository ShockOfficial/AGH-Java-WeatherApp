package pl.edu.agh.to2.weather_app.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class DataProvider {
    //API key has a limit of 100 calls a day if the limit is reached replace this one
    private static final String apiKey = "09fe2451db15d2b60a0a52041ee82126";
    private static final String url = "https://api.openweathermap.org";
    private static final String apiKeyParamName = "appid";
    private static final String cityParamName = "q";
    private static final String limitParamName = "limit";
    private static final String limit = "1";
    private static final String latitudeParamName = "lat";
    private static final String longitudeParamName = "lon";
    private static final Map<String, String> apiUrls = Map.of(
            "geo", "geo/1.0/direct",
            "air", "data/2.5/air_pollution",
            "weather", "data/2.5/weather" );
    private static final OkHttpClient client = new OkHttpClient();
    private WeatherDataProvider(){}

    protected static Response makeApiCall(Map<String, String> params, String apiUrl) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment(apiUrl);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        urlBuilder.addQueryParameter(apiKeyParamName, apiKey);      //api key is always required
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();                   //executing the request and getting HTTPOk response
    }

    public static String getIconUrl(String iconCode) {
        return "https://openweathermap.org/img/wn/" + iconCode + "@4x.png";
    }

    public static String getWeather(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon), apiUrls.get("weather"));
        return response.body().string();                            //returning the text in the body response
    }

    //This way is technically deprecated but works fine
    public static String getWeather(String city) throws IOException {
        Response response = makeApiCall(Map.of(cityParamName, city), apiUrls.get("weather"));
        return response.body().string();                            //returning the text in the body response
    }

    public static String getCoords(String city) throws IOException {
        Response response = makeApiCall(Map.of(cityParamName, city, limitParamName, limit), apiUrls.get("geo"));
        return response.body().string();                          //returning the text in the body response
    }

    public static String getAirPollution(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(latitudeParamName, lat, longitudeParamName, lon), apiUrls.get("air"));
        return response.body().string();                          //returning the text in the body response
    }


}
