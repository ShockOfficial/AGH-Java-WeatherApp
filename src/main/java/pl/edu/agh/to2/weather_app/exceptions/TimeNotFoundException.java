package pl.edu.agh.to2.weather_app.exceptions;

import java.sql.Time;

public class TimeNotFoundException extends RuntimeException{
    public TimeNotFoundException(String message){
        super(message);
    }
}
