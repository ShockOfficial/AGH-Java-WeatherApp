package pl.edu.agh.to2.weather_app.exceptions;

public class DataFetchException extends RuntimeException{
    public DataFetchException(String exception){
        super(exception);
    }
}
