package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataSerializationExceptionTest {
    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Data serialization error message";

        // When
        DataSerializationException exception = new DataSerializationException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }
}