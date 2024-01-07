package pl.edu.agh.to2.weather_app.exceptions;

public class FailedToLogException extends RuntimeException{

    public FailedToLogException(String message){
        super(message);
    }
}
