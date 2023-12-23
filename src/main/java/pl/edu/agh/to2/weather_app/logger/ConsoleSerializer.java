package pl.edu.agh.to2.weather_app.logger;

public class ConsoleSerializer implements IMessageSerializer{
    @Override
    public void serializeMessage(String message) {
        System.out.println(message);
    }
}
