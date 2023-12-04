package pl.edu.agh.to2.WeatherApp.exceptions;

public class DataFetchException extends RuntimeException{

    public DataFetchException(String exception){
        super(exception);
    }
}
