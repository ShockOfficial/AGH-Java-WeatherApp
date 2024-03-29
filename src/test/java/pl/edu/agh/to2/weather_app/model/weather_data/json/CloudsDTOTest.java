package pl.edu.agh.to2.weather_app.model.weather_data.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CloudsDTOTest {

    @Test
    void testSetAndGetAll() {
        // given
        CloudsDTO cloudsDTO = new CloudsDTO();
        int all = 10;

        // when
        cloudsDTO.setAll(all);

        // then
        assertEquals(all, cloudsDTO.getAll());
    }
}
