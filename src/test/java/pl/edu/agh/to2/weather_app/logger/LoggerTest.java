package pl.edu.agh.to2.weather_app.logger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
    void testGetInstance() {
        // given
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // then
        assertNotNull(logger1);
        assertNotNull(logger2);
        assertSame(logger1, logger2);
    }

    @Test
    void testRegisterSerializer() {
        // given
        Logger logger = Logger.getInstance();
        TestMessageSerializer testSerializer = new TestMessageSerializer();

        // when
        logger.registerSerializer(testSerializer);

        // then
        assertSame(testSerializer, logger.registeredSerializer);
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

    @Test
    void testLogWithNullArgument() {
        // given
        TestMessageSerializer testSerializer = new TestMessageSerializer();
        Logger logger = new Logger(testSerializer);

        // when
        logger.log(null);

        // then
        assertEquals(1, testSerializer.getMessages().size());
        assertTrue(testSerializer.getMessages().get(0).contains("null"));
    }
}
