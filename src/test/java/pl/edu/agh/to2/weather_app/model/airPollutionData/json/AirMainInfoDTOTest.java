package pl.edu.agh.to2.weather_app.model.airPollutionData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirMainInfoDTOTest {

    @Test
    void getAqi_ShouldReturnCorrectValue() {
        // given
        String expectedAqi = "2";
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
        airMainInfoDTO.setAqi(expectedAqi);

        // when
        String actualAqi = airMainInfoDTO.getAqi();

        // then
        assertEquals(expectedAqi, actualAqi);
    }

    @Test
    void setAqi_ShouldSetCorrectValue() {
        // given
        String expectedAqi = "3";
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();

        // when
        airMainInfoDTO.setAqi(expectedAqi);
        String actualAqi = airMainInfoDTO.getAqi();

        // then
        assertEquals(expectedAqi, actualAqi);
    }

    @Test
    void setAqi_WithNull_ShouldSetNullValue() {
        // given
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();

        // when
        airMainInfoDTO.setAqi(null);
        String actualAqi = airMainInfoDTO.getAqi();

        // then
        assertNull(actualAqi);
    }
}
