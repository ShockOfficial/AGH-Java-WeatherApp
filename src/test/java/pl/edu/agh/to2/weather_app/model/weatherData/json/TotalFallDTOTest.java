package pl.edu.agh.to2.weather_app.model.weatherData.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalFallDTOTest {

    @Test
    void testSetAndGet_3h() {
        // given
        TotalFallDTO totalFallDTO = new TotalFallDTO();
        float _3h = 10;

        // when
        totalFallDTO.setThreeH(_3h);

        // then
        assertEquals(_3h, totalFallDTO.getThreeH());
    }

    @Test
    void testSetAndGet1h() {
        // given
        TotalFallDTO totalFallDTO = new TotalFallDTO();
        float _1h = 10;

        // when
        totalFallDTO.setOneH(_1h);

        // then
        assertEquals(_1h, totalFallDTO.getOneH());
    }

}