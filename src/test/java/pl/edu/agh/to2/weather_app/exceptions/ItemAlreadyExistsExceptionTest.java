package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemAlreadyExistsExceptionTest {

    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Test error message";

        // When
        ItemAlreadyExistsException exception = new ItemAlreadyExistsException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }
}
