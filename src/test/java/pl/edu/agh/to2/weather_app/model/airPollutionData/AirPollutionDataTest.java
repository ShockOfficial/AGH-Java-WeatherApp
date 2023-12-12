package pl.edu.agh.to2.weather_app.model.airPollutionData;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.airPollutionData.json.AirListElementDTO;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AirPollutionDataTest {

    @Test
    void getPollutionListElement_ShouldReturnCorrectValue() {
        // Arrange
        AirListElementDTO expectedElement = new AirListElementDTO();
        List<AirListElementDTO> elementList = new ArrayList<>();
        elementList.add(expectedElement);
        AirPollutionData airPollutionData = new AirPollutionData();
        airPollutionData.setPollutionList(elementList);

        // Act
        AirListElementDTO actualElement = airPollutionData.getPollutionListElement();

        // Assert
        assertEquals(expectedElement, actualElement);
    }

    @Test
    void setPollutionList_ShouldSetCorrectValue() {
        // Arrange
        AirListElementDTO element1 = new AirListElementDTO();
        AirListElementDTO element2 = new AirListElementDTO();
        List<AirListElementDTO> expectedList = List.of(element1, element2);
        AirPollutionData airPollutionData = new AirPollutionData();

        // Act
        airPollutionData.setPollutionList(expectedList);
        AirListElementDTO actualElement = airPollutionData.getPollutionListElement();

        // Assert
        assertEquals(element1, actualElement); // Check the first element since there is no getPollutionElementList method
    }

    @Test
    void setPollutionList_WithNull_ShouldSetNullValue() {
        // Arrange
        AirPollutionData airPollutionData = new AirPollutionData();

        // Act
        airPollutionData.setPollutionList(null);
        assertThrows(NullPointerException.class, () -> airPollutionData.getPollutionListElement());
    }
}
