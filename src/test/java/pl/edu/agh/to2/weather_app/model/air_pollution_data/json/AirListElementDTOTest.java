package pl.edu.agh.to2.weather_app.model.air_pollution_data.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirListElementDTOTest {

    @Test
    void getMainInfo_ShouldReturnCorrectValue() {
        // given
        AirMainInfoDTO expectedMainInfo = new AirMainInfoDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        airListElementDTO.setMainInfo(expectedMainInfo);

        // when
        AirMainInfoDTO actualMainInfo = airListElementDTO.getMainInfo();

        // then
        assertEquals(expectedMainInfo, actualMainInfo);
    }

    @Test
    void setMainInfo_ShouldSetCorrectValue() {
        // given
        AirMainInfoDTO expectedMainInfo = new AirMainInfoDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();

        // when
        airListElementDTO.setMainInfo(expectedMainInfo);
        AirMainInfoDTO actualMainInfo = airListElementDTO.getMainInfo();

        // then
        assertEquals(expectedMainInfo, actualMainInfo);
    }

    @Test
    void getComponents_ShouldReturnCorrectValue() {
        // given
        ComponentsDTO expectedComponents = new ComponentsDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        airListElementDTO.setComponents(expectedComponents);

        // when
        ComponentsDTO actualComponents = airListElementDTO.getComponents();

        // then
        assertEquals(expectedComponents, actualComponents);
    }

    @Test
    void setComponents_ShouldSetCorrectValue() {
        // given
        ComponentsDTO expectedComponents = new ComponentsDTO();
        AirListElementDTO airListElementDTO = new AirListElementDTO();

        // when
        airListElementDTO.setComponents(expectedComponents);
        ComponentsDTO actualComponents = airListElementDTO.getComponents();

        // then
        assertEquals(expectedComponents, actualComponents);
    }
}
