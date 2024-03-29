package pl.edu.agh.to2.weather_app.api;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Map;

@Singleton
public class DataProvider {
    //API key has a limit of 100 calls a day if the limit is reached replace this one
    private static final String API_KEY = "09fe2451db15d2b60a0a52041ee82126";
    private static final String URL = "https://api.openweathermap.org";
    private static final String ICON_URL = "https://openweathermap.org/img/wn";
    private static final String API_KEY_PARAM_NAME = "appid";
    private static final String CITY_PARAM_NAME = "q";
    private static final String LIMIT_PARAM_NAME = "limit";
    private static final String LIMIT = "1";
    private static final String TIMESTAMP_PARAM_NAME = "cnt";
    private static final String TIMESTAMP = "8";
    private static final String LATITUDE_PARAM_NAME = "lat";
    private static final String LONGITUDE_PARAM_NAME = "lon";
    private static final String UNITS_PARAM_NAME = "units";
    private static final String UNIT = "metric";
    private static final String IMAGE_FORMAT = "@4x.png";

    private static final String WEATHER_URL = "data/2.5/weather";
    private static final String GEO_URL = "geo/1.0/direct";
    private static final String FORECAST_URL = "data/2.5/forecast";
    private static final String AIR_URL = "data/2.5/air_pollution";

    private final OkHttpClient client;
    @Inject
    public DataProvider(OkHttpClient client){
        this.client = client;
    }

    private Response makeApiCall(Map<String, String> params, String urlKey) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(URL).newBuilder();
        urlBuilder.addPathSegment(urlKey);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        urlBuilder.addQueryParameter(API_KEY_PARAM_NAME, API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();
    }

    public String getIconUrl(String iconCode) {
        HttpUrl.Builder builder = HttpUrl.parse(ICON_URL).newBuilder();
        builder.addPathSegment(iconCode + IMAGE_FORMAT);
        return builder.build().toString();
    }

    public String getWeather(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(LATITUDE_PARAM_NAME, lat,
                        LONGITUDE_PARAM_NAME, lon,
                        UNITS_PARAM_NAME, UNIT),
                WEATHER_URL);
        return response.body().string();
    }

    //This way is technically deprecated but works fine
    public String getWeather(String city) throws IOException {
        Response response = makeApiCall(Map.of(CITY_PARAM_NAME, city,
                    UNITS_PARAM_NAME, UNIT),
                WEATHER_URL);
        return response.body().string();
    }

    public String getCoords(String city) throws IOException {
        Response response = makeApiCall(Map.of(CITY_PARAM_NAME, city,
                        LIMIT_PARAM_NAME, LIMIT),
                GEO_URL);
        return response.body().string();
    }

    public String getAirPollution(String lon, String lat) throws IOException {
        Response response = makeApiCall(Map.of(LATITUDE_PARAM_NAME, lat,
                        LONGITUDE_PARAM_NAME, lon,
                        UNITS_PARAM_NAME, UNIT),
                AIR_URL);
        return response.body().string();
    }


    //The free version only allows forecast in increments of 3 hours
    public String getForecast(String lon, String lat) throws  IOException{
        Response response = makeApiCall(Map.of(LATITUDE_PARAM_NAME, lat,
                                                LONGITUDE_PARAM_NAME, lon,
                                                UNITS_PARAM_NAME, UNIT,
                                                TIMESTAMP_PARAM_NAME, TIMESTAMP),
                                        FORECAST_URL);
        return response.body().string();
    }

}
