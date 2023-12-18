package pl.edu.agh.to2.weather_app.exceptions;

public class GeocodingException extends RuntimeException{
    public GeocodingException(String message){super(message);}
}
