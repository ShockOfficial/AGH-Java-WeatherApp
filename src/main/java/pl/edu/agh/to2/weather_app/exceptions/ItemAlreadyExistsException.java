package pl.edu.agh.to2.weather_app.exceptions;

public class ItemAlreadyExistsException extends RuntimeException{
    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
