package pl.edu.agh.to2.weather_app.utils.converter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirQualityConverterTest {

    @Test
    void getAirQualityString_ValidInput_ReturnsCorrectString() {
        // Arrange
        String input = "1";
        String expectedOutput = "Very good";

        // Act
        String result = AirQualityConverter.getAirQualityString(input);

        // Assert
        assertEquals(expectedOutput, result);
    }

    @Test
    void getAirQualityString_UnknownInput_ReturnsNull() {
        // Arrange
        String input = "Unknown";

        // Act
        String result = AirQualityConverter.getAirQualityString(input);

        // Assert
        assertEquals(null, result);
    }

    @Test
    void getAirQualityString_InvalidInput_ReturnsNull() {
        // Arrange
        String input = "6"; // Invalid input

        // Act
        String result = AirQualityConverter.getAirQualityString(input);

        // Assert
        assertEquals(null, result);
    }

    // Add more test cases as needed
}
