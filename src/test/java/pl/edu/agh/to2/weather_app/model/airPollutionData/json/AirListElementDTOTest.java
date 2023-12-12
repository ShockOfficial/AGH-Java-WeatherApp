package pl.edu.agh.to2.weather_app.model.airPollutionData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirListElementDTOTest {

    @Test
    void getMainInfo_ShouldReturnCorrectValue() {
        // Arrange
        AirMainInfoDTO expectedMainInfo = new AirMainInfoDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        airListElementDTO.setMainInfo(expectedMainInfo);

        // Act
        AirMainInfoDTO actualMainInfo = airListElementDTO.getMainInfo();

        // Assert
        assertEquals(expectedMainInfo, actualMainInfo);
    }

    @Test
    void setMainInfo_ShouldSetCorrectValue() {
        // Arrange
        AirMainInfoDTO expectedMainInfo = new AirMainInfoDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();

        // Act
        airListElementDTO.setMainInfo(expectedMainInfo);
        AirMainInfoDTO actualMainInfo = airListElementDTO.getMainInfo();

        // Assert
        assertEquals(expectedMainInfo, actualMainInfo);
    }

    @Test
    void getComponents_ShouldReturnCorrectValue() {
        // Arrange
        ComponentsDTO expectedComponents = new ComponentsDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        airListElementDTO.setComponents(expectedComponents);

        // Act
        ComponentsDTO actualComponents = airListElementDTO.getComponents();

        // Assert
        assertEquals(expectedComponents, actualComponents);
    }

    @Test
    void setComponents_ShouldSetCorrectValue() {
        // Arrange
        ComponentsDTO expectedComponents = new ComponentsDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();

        // Act
        airListElementDTO.setComponents(expectedComponents);
        ComponentsDTO actualComponents = airListElementDTO.getComponents();

        // Assert
        assertEquals(expectedComponents, actualComponents);
    }
}
