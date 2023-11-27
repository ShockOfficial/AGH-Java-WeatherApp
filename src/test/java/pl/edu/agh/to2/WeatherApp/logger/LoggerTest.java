package pl.edu.agh.to2.WeatherApp.logger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoggerTest {
    static class TestMessageSerializer implements IMessageSerializer {
        private final List<String> messages = new ArrayList<>();

        public void serializeMessage(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    @Test
    public void testLogWithoutError() {
        TestMessageSerializer testSerializer = new TestMessageSerializer();
        Logger logger = new Logger(testSerializer);

        logger.log("Test message");

        assertEquals(1, testSerializer.getMessages().size());
        assertTrue(testSerializer.getMessages().get(0).contains("Test message"));

    }

    @Test
    public void testLogWithError() {
        TestMessageSerializer testSerializer = new TestMessageSerializer();
        Logger logger = new Logger(testSerializer);
        Throwable error = new RuntimeException("Test error");

        logger.log("Test message", error);

        assertEquals(1, testSerializer.getMessages().size());
        System.out.println(testSerializer.getMessages().get(0));
        assertTrue(testSerializer.getMessages().get(0).contains("messagejava.lang.RuntimeException"));
    }

}
