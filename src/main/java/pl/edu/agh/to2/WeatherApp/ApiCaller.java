package pl.edu.agh.to2.WeatherApp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiCaller {
    //API key has a limit of 100 calls a day if the limit is reached replace this one
    private static final String API_KEY = "09fe2451db15d2b60a0a52041ee82126";

    //Api call using longitude and latitude according to the creators the "correct" way of requesting
    public static String getWeather(String lon, String lat) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?lat=" +lat
                        +"&lon=" + lon
                        +"&appid=" + API_KEY).build();
        Response response = client.newCall(request).execute();      //executing the request and getting HTTPOk response
        return response.body().string();                            //returning the text in the body response

    }
    //This way is technically "deprecated" but works fine
    public static String getWeather(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" +city
                        +"&appid=" + API_KEY).build();
        Response response = client.newCall(request).execute();  //executing the request and getting HTTPOk response
        return response.body().string();                        //returning the text in the body response

    }
}
