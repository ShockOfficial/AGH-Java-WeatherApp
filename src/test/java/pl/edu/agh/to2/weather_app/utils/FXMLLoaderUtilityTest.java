package pl.edu.agh.to2.weather_app.utils;

import javafx.scene.Parent;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import pl.edu.agh.to2.weather_app.model.WeatherModel;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FXMLLoaderUtilityTest extends ApplicationTest {

    @Test
    void testLoadMainView() throws IOException {
        // given
        WeatherModel mockWeatherModel = mock(WeatherModel.class);

        // when
        Parent root = FXMLLoaderUtility.loadMainView(mockWeatherModel);

        // then
        assertNotNull(root);
    }
}
