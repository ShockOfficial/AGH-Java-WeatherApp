package pl.edu.agh.to2.weather_app.model.airPollutionData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirMainInfoDTOTest {

    @Test
    void getAqi_ShouldReturnCorrectValue() {
        // Arrange
        String expectedAqi = "2";
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
        airMainInfoDTO.setAqi(expectedAqi);

        // Act
        String actualAqi = airMainInfoDTO.getAqi();

        // Assert
        assertEquals(expectedAqi, actualAqi);
    }

    @Test
    void setAqi_ShouldSetCorrectValue() {
        // Arrange
        String expectedAqi = "3";
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();

        // Act
        airMainInfoDTO.setAqi(expectedAqi);
        String actualAqi = airMainInfoDTO.getAqi();

        // Assert
        assertEquals(expectedAqi, actualAqi);
    }

    @Test
    void setAqi_WithNull_ShouldSetNullValue() {
        // Arrange
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();

        // Act
        airMainInfoDTO.setAqi(null);
        String actualAqi = airMainInfoDTO.getAqi();

        // Assert
        assertNull(actualAqi);
    }
}
