package pl.edu.agh.to2.weather_app.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.exceptions.FailedToLogException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileSerializerTest {

    private final String testFilePath = "src/persistence/logs.txt";

    @BeforeEach
    void setUp() {
        cleanLogFile();
    }

    @AfterEach
    void tearDown() {
        clearLogFileContent();
    }

    private void cleanLogFile() {
        File file = new File(testFilePath);
        if (file.exists() && !file.delete()) {
            System.err.println("Failed to delete the test file: " + testFilePath);
        }
    }

    private void clearLogFileContent() {
        try (FileWriter fileWriter = new FileWriter(testFilePath)) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSerializeMessage() {
        FileSerializer fileSerializer = new FileSerializer();
        String testMessage = "Test log message";

        try {
            fileSerializer.serializeMessage(testMessage);

            assertTrue(isMessageInFile(testMessage));
        } catch (FailedToLogException e) {
            fail("Failed to serialize the message: " + e.getMessage());
        }
    }

    private boolean isMessageInFile(String message) {
        try (BufferedReader br = new BufferedReader(new FileReader(testFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(message)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
