package pl.edu.agh.to2.weather_app.model.weatherData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainInfoDTOTest {

    @Test
    void testSetAndGetSeaLevel() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        int seaLevel = 10;

        // when
        mainInfoDTO.setSeaLevel(seaLevel);

        // then
        assertEquals(seaLevel, mainInfoDTO.getSeaLevel());
    }

    @Test
    void testSetAndGetGrndLevel() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        int grndLevel = 10;

        // when
        mainInfoDTO.setGrndLevel(grndLevel);

        // then
        assertEquals(grndLevel, mainInfoDTO.getGrndLevel());
    }

    @Test
    void testSetAndGetTemp() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        float temp = 10.0F;

        // when
        mainInfoDTO.setTemp(temp);

        // then
        assertEquals(temp, mainInfoDTO.getTemp());
    }

    @Test
    void testSetAndGetFeelsLike() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        float feelsLike = 10.0F;

        // when
        mainInfoDTO.setFeelsLike(feelsLike);

        // then
        assertEquals(feelsLike, mainInfoDTO.getFeelsLike());
    }

    @Test
    void testSetAndGetTempMin() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        float tempMin = 10.0F;

        // when
        mainInfoDTO.setTempMin(tempMin);

        // then
        assertEquals(tempMin, mainInfoDTO.getTempMin());
    }

    @Test
    void testSetAndGetTempMax() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        float tempMax = 10.0F;

        // when
        mainInfoDTO.setTempMax(tempMax);

        // then
        assertEquals(tempMax, mainInfoDTO.getTempMax());
    }

    @Test
    void testSetAndGetPressure() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        int pressure = 10;

        // when
        mainInfoDTO.setPressure(pressure);

        // then
        assertEquals(pressure, mainInfoDTO.getPressure());
    }

    @Test
    void testSetAndGetHumidity() {
        // given
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        int humidity = 10;

        // when
        mainInfoDTO.setHumidity(humidity);

        // then
        assertEquals(humidity, mainInfoDTO.getHumidity());
    }
}
