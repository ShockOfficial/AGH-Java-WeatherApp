package pl.edu.agh.to2.weather_app.logger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerTest {

    static class TestMessageSerializer implements IMessageSerializer {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void serializeMessage(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    @Test
    void testLogWithoutError() {
        // given
        TestMessageSerializer testSerializer = new TestMessageSerializer();
        Logger logger = new Logger(testSerializer);
        String testMessage = "Test message";

        // when
        logger.log(testMessage);

        // then
        assertEquals(1, testSerializer.getMessages().size());
        assertTrue(testSerializer.getMessages().get(0).contains(testMessage));
    }

    @Test
    void testLogWithError() {
        // given
        TestMessageSerializer testSerializer = new TestMessageSerializer();
        Logger logger = new Logger(testSerializer);
        Throwable error = new RuntimeException("Test error");
        String testMessage = "Test message";

        // when
        logger.log(testMessage, error);

        // then
        assertEquals(1, testSerializer.getMessages().size());
        String loggedMessage = testSerializer.getMessages().get(0);
        assertTrue(loggedMessage.contains(testMessage) && loggedMessage.contains("RuntimeException"));
    }
}
