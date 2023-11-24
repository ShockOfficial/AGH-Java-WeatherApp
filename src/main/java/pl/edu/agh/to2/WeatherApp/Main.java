package pl.edu.agh.to2.WeatherApp;

import com.google.gson.Gson;
import javafx.application.Application;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import pl.edu.agh.to2.WeatherApp.WeatherData.WeatherData;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class.toString());
	
	public static void main(String[] args) {
		WeatherData weather;
		try{
			String response = ApiCaller.getWeather("19.944544" ,"50.049683");
			weather = new Gson().fromJson(response, WeatherData.class);
		} catch (IOException e){
			log.info("IOException at API request");
		}
		log.info("Hello world");
		Application.launch(App.class);
	}
}
