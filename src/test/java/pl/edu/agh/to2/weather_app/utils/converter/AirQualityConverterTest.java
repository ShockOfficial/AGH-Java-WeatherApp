package pl.edu.agh.to2.weather_app.utils.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirMainInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class AirQualityConverterTest {

    @Mock
    private AirPollutionData mockAirPollutionData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAirQualityString() {
        // given
        AirMainInfoDTO mainInfo = new AirMainInfoDTO();
        mainInfo.setAqi("3");

        AirListElementDTO listElement = new AirListElementDTO();
        listElement.setMainInfo(mainInfo);

        when(mockAirPollutionData.getPollutionListElement()).thenReturn(listElement);

        // when
        String result = AirQualityConverter.getAirQualityString(mockAirPollutionData);

        // then
        assertEquals("Average", result);
    }

    @Test
    void testGetAirQualityString_InvalidAqi() {
        // given
        AirMainInfoDTO mainInfo = new AirMainInfoDTO();
        mainInfo.setAqi("6"); // Invalid AQI

        AirListElementDTO listElement = new AirListElementDTO();
        listElement.setMainInfo(mainInfo);
        when(mockAirPollutionData.getPollutionListElement()).thenReturn(listElement);

        // when
        String result = AirQualityConverter.getAirQualityString(mockAirPollutionData);

        // then
        assertNull(result);
    }
}
