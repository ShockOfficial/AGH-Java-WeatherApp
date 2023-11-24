package pl.edu.agh.to2.WeatherApp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class ApiCaller {
    private static final String API_KEY = "09fe2451db15d2b60a0a52041ee82126";

    public static Optional<String> getWeather(String lon, String lat) throws IOException, NullPointerException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?lat="+ lat + "&lon=" + lon +"&appid=" + API_KEY)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Optional.of(response.body().string());
        }
    }
}
