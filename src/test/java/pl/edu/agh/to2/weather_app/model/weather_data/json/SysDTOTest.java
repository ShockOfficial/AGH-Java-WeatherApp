package pl.edu.agh.to2.weather_app.model.weather_data.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SysDTOTest {

    @Test
     void testSetAndGetType() {
        // given
        SysDTO sysDTO = new SysDTO();
        int type = 10;

        //when
        sysDTO.setType(type);

        //then
        assertEquals(type, sysDTO.getType());
    }
    @Test
     void testSetAndGetId() {
        // given
        SysDTO sysDTO = new SysDTO();
        int id = 10;

        //when
        sysDTO.setId(id);

        //then
        assertEquals(id, sysDTO.getId());
    }
    @Test
     void testSetAndGetCountry() {
        // given
        SysDTO sysDTO = new SysDTO();
        String country = "country";

        //when
        sysDTO.setCountry(country);

        //then
        assertEquals(country, sysDTO.getCountry());
    }
    @Test
     void testSetAndGetSunrise() {
        // given
        SysDTO sysDTO = new SysDTO();
        long sunrise = 10;

        //when
        sysDTO.setSunrise(sunrise);

        //then
        assertEquals(sunrise, sysDTO.getSunrise());
    }
    @Test
     void testSetAndGetSunset() {
        // given
        SysDTO sysDTO = new SysDTO();
        long sunset = 10;

        //when
        sysDTO.setSunset(sunset);

        //then
        assertEquals(sunset, sysDTO.getSunset());
    }

}