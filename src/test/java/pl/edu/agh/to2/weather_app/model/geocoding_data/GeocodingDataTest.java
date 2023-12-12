package pl.edu.agh.to2.weather_app.model.geocoding_data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeocodingDataTest {

    @Test
    void getCountry_ShouldReturnCorrectValue() {
        // given
        String expectedCountry = "Poland";
        GeocodingData geocodingData = new GeocodingData();
        geocodingData.setCountry(expectedCountry);

        // when
        String actualCountry = geocodingData.getCountry();

        // then
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void setCountry_ShouldSetCorrectValue() {
        // given
        String expectedCountry = "Germany";
        GeocodingData geocodingData = new GeocodingData();

        // when
        geocodingData.setCountry(expectedCountry);
        String actualCountry = geocodingData.getCountry();

        // then
        assertEquals(expectedCountry, actualCountry);
    }

    @Test
    void getName_ShouldReturnCorrectValue() {
        // given
        String expectedName = "CityName";
        GeocodingData geocodingData = new GeocodingData();
        geocodingData.setName(expectedName);

        // when
        String actualName = geocodingData.getName();

        // then
        assertEquals(expectedName, actualName);
    }

    @Test
    void setName_ShouldSetCorrectValue() {
        // given
        String expectedName = "AnotherCity";
        GeocodingData geocodingData = new GeocodingData();

        // when
        geocodingData.setName(expectedName);
        String actualName = geocodingData.getName();

        // then
        assertEquals(expectedName, actualName);
    }

    @Test
    void getLat_ShouldReturnCorrectValue() {
        // given
        String expectedLat = "40.7128";
        GeocodingData geocodingData = new GeocodingData();
        geocodingData.setLat(expectedLat);

        // when
        String actualLat = geocodingData.getLat();

        // then
        assertEquals(expectedLat, actualLat);
    }

    @Test
    void setLat_ShouldSetCorrectValue() {
        // given
        String expectedLat = "37.7749";
        GeocodingData geocodingData = new GeocodingData();

        // when
        geocodingData.setLat(expectedLat);
        String actualLat = geocodingData.getLat();

        // then
        assertEquals(expectedLat, actualLat);
    }


    @Test
    void setCountry_WithNull_ShouldSetNullValue() {
        // given
        GeocodingData geocodingData = new GeocodingData();

        // when
        geocodingData.setCountry(null);
        String actualCountry = geocodingData.getCountry();

        // then
        assertNull(actualCountry);
    }
    @Test
    void getLon_ShouldReturnCorrectValue() {
        // given
        String expectedLon = "74.0060";
        GeocodingData geocodingData = new GeocodingData();
        geocodingData.setLon(expectedLon);

        // when
        String actualLon = geocodingData.getLon();

        // then
        assertEquals(expectedLon, actualLon);
    }

    @Test
    void setLon_ShouldSetCorrectValue() {
        // given
        String expectedLon = "122.4194";
        GeocodingData geocodingData = new GeocodingData();

        // when
        geocodingData.setLon(expectedLon);
        String actualLon = geocodingData.getLon();

        // then
        assertEquals(expectedLon, actualLon);
    }

}
