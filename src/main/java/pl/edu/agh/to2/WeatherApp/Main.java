package pl.edu.agh.to2.WeatherApp;

import com.google.gson.Gson;
import javafx.application.Application;

import java.io.IOException;
import java.util.logging.Logger;
import pl.edu.agh.to2.WeatherApp.WeatherData.WeatherData;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class.toString());
	
	public static void main(String[] args) {

		log.info("Hello world");
		Application.launch(App.class);
	}
}
