package pl.edu.agh.to2.weather_app.exceptions;

public class TimeNotFoundException extends RuntimeException{
    public TimeNotFoundException(String message){
        super(message);
    }
}
