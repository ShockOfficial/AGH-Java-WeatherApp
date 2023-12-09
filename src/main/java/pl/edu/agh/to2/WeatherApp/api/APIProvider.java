package pl.edu.agh.to2.WeatherApp.api;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public abstract class APIProvider {

    //API key has a limit of 100 calls a day if the limit is reached replace this one
    private static final String apiKey = "09fe2451db15d2b60a0a52041ee82126";
    private static final String url = "https://api.openweathermap.org";
    private static final String apiKeyParamName = "appid";
    private static final OkHttpClient client = new OkHttpClient();

    protected static Response makeApiCall(Map<String, String> params, String apiUrl) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment(apiUrl);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        urlBuilder.addQueryParameter(apiKeyParamName, apiKey);      //api key is always required
        String url = urlBuilder.build().toString();
        System.out.println(url);

        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute();                   //executing the request and getting HTTPOk response
    }
}
