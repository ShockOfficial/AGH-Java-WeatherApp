package pl.edu.agh.to2.weather_app.logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ConsoleSerializerTest {

    @Test
    void testSerializeMessage() {
        // given
        ConsoleSerializer consoleSerializer = new ConsoleSerializer();
        String testMessage = "Test message";

        // when & then
        assertDoesNotThrow(() -> consoleSerializer.serializeMessage(testMessage));
    }
}