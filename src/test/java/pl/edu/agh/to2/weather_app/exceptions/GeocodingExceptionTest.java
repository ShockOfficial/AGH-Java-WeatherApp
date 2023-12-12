package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeocodingExceptionTest {

    @Test
    void geocodingException_ShouldHaveCorrectMessage() {
        // given
        String expectedMessage = "Error in geocoding process.";

        // when
        GeocodingException geocodingException = new GeocodingException(expectedMessage);
        String actualMessage = geocodingException.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }
}
