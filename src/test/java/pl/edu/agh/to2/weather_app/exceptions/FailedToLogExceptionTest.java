package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FailedToLogExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Test error message";

        // When
        FailedToLogException exception = new FailedToLogException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }
}
