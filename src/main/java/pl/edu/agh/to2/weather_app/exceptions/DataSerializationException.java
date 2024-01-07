package pl.edu.agh.to2.weather_app.exceptions;

public class DataSerializationException extends RuntimeException{

    public DataSerializationException(String message){
        super(message);
    }
}