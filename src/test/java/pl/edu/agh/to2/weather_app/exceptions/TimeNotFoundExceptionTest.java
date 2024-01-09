package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Test error message";

        // When
        TimeNotFoundException exception = new TimeNotFoundException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }
}
