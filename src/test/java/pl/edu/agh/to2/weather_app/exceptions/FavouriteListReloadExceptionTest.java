package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavouriteListReloadExceptionTest {
    @Test
    void testConstructorWithMessage() {
        // Given
        String errorMessage = "Favourite list reload error message";

        // When
        FavouriteListReloadException exception = new FavouriteListReloadException(errorMessage);

        // Then
        assertEquals(errorMessage, exception.getMessage());
    }

}