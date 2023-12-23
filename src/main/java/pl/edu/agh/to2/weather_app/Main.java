package pl.edu.agh.to2.weather_app;

import javafx.application.Application;
import okhttp3.OkHttpClient;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
import pl.edu.agh.to2.weather_app.model.response_converter.GsonConverter;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;

public class Main {
	public static void main(String[] args) {

		Application.launch(App.class);
	}
}
