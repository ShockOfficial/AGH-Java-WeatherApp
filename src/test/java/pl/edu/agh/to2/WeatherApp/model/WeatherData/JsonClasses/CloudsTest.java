package pl.edu.agh.to2.WeatherApp.model.WeatherData.JsonClasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudsTest {

        @Test
        void testSetAndGetAll() {
            Clouds clouds = new Clouds();
            clouds.setAll(10);
            assertEquals(10, clouds.getAll());
        }

}