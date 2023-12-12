package pl.edu.agh.to2.weather_app.model.air_pollution_data.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComponentsDTOTest {


    @Test
    void getPm10_ShouldReturnCorrectValue() {
        // given
        String expectedPm10 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setPm10(expectedPm10);

        // when
        String actualPm10 = componentsDTO.getPm10();

        // then
        assertEquals(expectedPm10, actualPm10);
    }

    @Test
    void setPm10_ShouldSetCorrectValue() {
        // given
        String expectedPm10 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setPm10(expectedPm10);
        String actualPm10 = componentsDTO.getPm10();

        // then
        assertEquals(expectedPm10, actualPm10);
    }

    @Test

    void getPm25_ShouldReturnCorrectValue() {
        // given
        String expectedPm25 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setPm2_5(expectedPm25);

        // when
        String actualPm25 = componentsDTO.getPm2_5();

        // then
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void setPm25_ShouldSetCorrectValue() {
        // given
        String expectedPm25 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setPm2_5(expectedPm25);
        String actualPm25 = componentsDTO.getPm2_5();

        // then
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void getO3_ShouldReturnCorrectValue() {
        // given
        String expectedO3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setO3(expectedO3);

        // when
        String actualO3 = componentsDTO.getO3();

        // then
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void setO3_ShouldSetCorrectValue() {
        // given
        String expectedO3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setO3(expectedO3);
        String actualO3 = componentsDTO.getO3();

        // then
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void getSo2_ShouldReturnCorrectValue() {
        // given
        String expectedSo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setSo2(expectedSo2);

        // when
        String actualSo2 = componentsDTO.getSo2();

        // then
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void setSo2_ShouldSetCorrectValue() {
        // given
        String expectedSo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setSo2(expectedSo2);
        String actualSo2 = componentsDTO.getSo2();

        // then
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void getNh3_ShouldReturnCorrectValue() {
        // given
        String expectedNh3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNh3(expectedNh3);

        // when
        String actualNh3 = componentsDTO.getNh3();

        // then
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void setNh3_ShouldSetCorrectValue() {
        // given
        String expectedNh3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setNh3(expectedNh3);
        String actualNh3 = componentsDTO.getNh3();

        // then
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void getNo2_ShouldReturnCorrectValue() {
        // given
        String expectedNo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNo2(expectedNo2);

        // when
        String actualNo2 = componentsDTO.getNo2();

        // then
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void setNo2_ShouldSetCorrectValue() {
        // given
        String expectedNo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setNo2(expectedNo2);
        String actualNo2 = componentsDTO.getNo2();

        // then
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void getCo_ShouldReturnCorrectValue() {
        // given
        String expectedCo = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setCo(expectedCo);

        // when
        String actualCo = componentsDTO.getCo();

        // then
        assertEquals(expectedCo, actualCo);
    }

    @Test
    void setCo_ShouldSetCorrectValue() {
        // given
        String expectedCo = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setCo(expectedCo);
        String actualCo = componentsDTO.getCo();

        // then
        assertEquals(expectedCo, actualCo);
    }

    @Test
    void getNo_ShouldReturnCorrectValue() {
        // given
        String expectedNo = "0.2";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNo(expectedNo);

        // when
        String actualNo = componentsDTO.getNo();

        // then
        assertEquals(expectedNo, actualNo);
    }

    @Test
    void setNo_ShouldSetCorrectValue() {
        // given
        String expectedNo = "0.7";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setNo(expectedNo);
        String actualNo = componentsDTO.getNo();

        // then
        assertEquals(expectedNo, actualNo);
    }

    // Repeat the above pattern for other getters and setters

    @Test
    void setNh3_WithNull_ShouldSetNullValue() {
        // given
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setNh3(null);
        String actualNh3 = componentsDTO.getNh3();

        // then
        assertNull(actualNh3);
    }

    @Test
    void setNh3_WithNonEmptyString() {
        // given
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setNh3("test");
        String actualNh3 = componentsDTO.getNh3();

        // then
        assertEquals("test", actualNh3);
    }
}
