package pl.edu.agh.to2.WeatherApp;

import javafx.application.Application;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class.toString());
	
	public static void main(String[] args) {
		try{
			Optional<String> data = ApiCaller.getWeather("19.944544" ,"50.049683");
			data.ifPresent(System.out::println);
		} catch (IOException e){
			log.info("IOException at API request");
		}
		log.info("Hello world");
		Application.launch(App.class);
	}
}
