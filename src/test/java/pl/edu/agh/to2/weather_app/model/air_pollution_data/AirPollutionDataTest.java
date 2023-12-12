package pl.edu.agh.to2.weather_app.model.air_pollution_data;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AirPollutionDataTest {

    @Test
    void getPollutionListElement_ShouldReturnCorrectValue() {
        // given
        AirListElementDTO expectedElement = new AirListElementDTO();
        List<AirListElementDTO> elementList = new ArrayList<>();
        elementList.add(expectedElement);
        AirPollutionData airPollutionData = new AirPollutionData();
        airPollutionData.setPollutionList(elementList);

        // when
        AirListElementDTO actualElement = airPollutionData.getPollutionListElement();

        // then
        assertEquals(expectedElement, actualElement);
    }

    @Test
    void setPollutionList_ShouldSetCorrectValue() {
        // given
        AirListElementDTO element1 = new AirListElementDTO();
        AirListElementDTO element2 = new AirListElementDTO();
        List<AirListElementDTO> expectedList = List.of(element1, element2);
        AirPollutionData airPollutionData = new AirPollutionData();

        // when
        airPollutionData.setPollutionList(expectedList);
        AirListElementDTO actualElement = airPollutionData.getPollutionListElement();

        // then
        assertEquals(element1, actualElement); // Check the first element since there is no getPollutionElementList method
    }

    @Test
    void setPollutionList_WithNull_ShouldSetNullValue() {
        // given
        AirPollutionData airPollutionData = new AirPollutionData();

        // when
        airPollutionData.setPollutionList(null);

        // then
        assertThrows(NullPointerException.class, airPollutionData::getPollutionListElement);
    }
}
