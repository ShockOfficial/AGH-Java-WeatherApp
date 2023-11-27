package pl.edu.agh.to2.WeatherApp.logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConsoleSerializerTest {

    @Test
    public void testSerializeMessage() {
        ConsoleSerializer consoleSerializer = new ConsoleSerializer();
        assertDoesNotThrow(() -> consoleSerializer.serializeMessage("Test message"));
    }
}