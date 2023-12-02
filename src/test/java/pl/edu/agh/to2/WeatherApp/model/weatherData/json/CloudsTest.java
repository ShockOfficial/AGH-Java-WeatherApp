package pl.edu.agh.to2.WeatherApp.model.weatherData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CloudsTest {

    @Test
    void testSetAndGetAll() {
        // given
        Clouds clouds = new Clouds();
        int all = 10;

        // when
        clouds.setAll(all);

        // then
        assertEquals(all, clouds.getAll());
    }
}
