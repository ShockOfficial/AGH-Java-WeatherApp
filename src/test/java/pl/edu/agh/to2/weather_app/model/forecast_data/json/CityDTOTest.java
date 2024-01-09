package pl.edu.agh.to2.weather_app.model.forecast_data.json;

import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.model.weather_data.json.CoordDTO;

import static org.junit.jupiter.api.Assertions.*;

class CityDTOTest {

    @Test
    void testGetName() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Name");
        assertEquals("Name", cityDTO.getName());
    }

    @Test
    void testSetName() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Name");
        assertEquals("Name", cityDTO.getName());
    }

    @Test
    void testGetCoordinates() {
        CityDTO cityDTO = new CityDTO();
        CoordDTO coordDTO = new CoordDTO();
        coordDTO.setLat(12);
        coordDTO.setLon(15);
        cityDTO.setCoordinates(coordDTO);
        assertEquals(coordDTO, cityDTO.getCoordinates());
    }

    @Test
    void testSetCoordinates() {
        CityDTO cityDTO = new CityDTO();
        CoordDTO coordDTO = new CoordDTO();
        coordDTO.setLat(12);
        coordDTO.setLon(24);
        cityDTO.setCoordinates(coordDTO);
        assertEquals(coordDTO, cityDTO.getCoordinates());
    }

    @Test
    void testGetCountry() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCountry("Country");
        assertEquals("Country", cityDTO.getCountry());
    }

    @Test
    void testSetCountry() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCountry("Country");
        assertEquals("Country", cityDTO.getCountry());
    }


}