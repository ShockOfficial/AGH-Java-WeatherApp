package pl.edu.agh.to2.weather_app.logger;

import pl.edu.agh.to2.weather_app.exceptions.FailedToLogException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSerializer implements IMessageSerializer {
    private static final String DB_PATH = "src/persistence/logs.txt";

    @Override
    public void serializeMessage(String message) throws FailedToLogException {
        File file = new File(DB_PATH);
        try (FileWriter fr = new FileWriter(file, false)) {
            fr.write(message);
        } catch (IOException e) {
            throw new FailedToLogException("Failed to save a log to a file");
        }
    }
}
