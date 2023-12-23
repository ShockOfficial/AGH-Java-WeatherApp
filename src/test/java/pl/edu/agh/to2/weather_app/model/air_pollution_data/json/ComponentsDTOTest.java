package pl.edu.agh.to2.weather_app.model.air_pollution_data.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComponentsDTOTest {


    @Test
    void getPm10_ShouldReturnCorrectValue() {
        // given
        String expectedPm10 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfCoarseParticulateMatter(expectedPm10);

        // when
        String actualPm10 = componentsDTO.getConcentrationOfCoarseParticulateMatter();

        // then
        assertEquals(expectedPm10, actualPm10);
    }

    @Test
    void setPm10_ShouldSetCorrectValue() {
        // given
        String expectedPm10 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfCoarseParticulateMatter(expectedPm10);
        String actualPm10 = componentsDTO.getConcentrationOfCoarseParticulateMatter();

        // then
        assertEquals(expectedPm10, actualPm10);
    }

    @Test

    void getPm25_ShouldReturnCorrectValue() {
        // given
        String expectedPm25 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfFineParticlesMatter(expectedPm25);

        // when
        String actualPm25 = componentsDTO.getConcentrationOfFineParticlesMatter();

        // then
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void setPm25_ShouldSetCorrectValue() {
        // given
        String expectedPm25 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfFineParticlesMatter(expectedPm25);
        String actualPm25 = componentsDTO.getConcentrationOfFineParticlesMatter();

        // then
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void getO3_ShouldReturnCorrectValue() {
        // given
        String expectedO3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfOzone(expectedO3);

        // when
        String actualO3 = componentsDTO.getConcentrationOfOzone();

        // then
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void setO3_ShouldSetCorrectValue() {
        // given
        String expectedO3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfOzone(expectedO3);
        String actualO3 = componentsDTO.getConcentrationOfOzone();

        // then
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void getSo2_ShouldReturnCorrectValue() {
        // given
        String expectedSo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfSulphurDioxide(expectedSo2);

        // when
        String actualSo2 = componentsDTO.getConcentrationOfSulphurDioxide();

        // then
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void setSo2_ShouldSetCorrectValue() {
        // given
        String expectedSo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfSulphurDioxide(expectedSo2);
        String actualSo2 = componentsDTO.getConcentrationOfSulphurDioxide();

        // then
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void getNh3_ShouldReturnCorrectValue() {
        // given
        String expectedNh3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfAmmonia(expectedNh3);

        // when
        String actualNh3 = componentsDTO.getConcentrationOfAmmonia();

        // then
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void setNh3_ShouldSetCorrectValue() {
        // given
        String expectedNh3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfAmmonia(expectedNh3);
        String actualNh3 = componentsDTO.getConcentrationOfAmmonia();

        // then
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void getNo2_ShouldReturnCorrectValue() {
        // given
        String expectedNo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfNitrogenDioxide(expectedNo2);

        // when
        String actualNo2 = componentsDTO.getConcentrationOfNitrogenDioxide();

        // then
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void setNo2_ShouldSetCorrectValue() {
        // given
        String expectedNo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfNitrogenDioxide(expectedNo2);
        String actualNo2 = componentsDTO.getConcentrationOfNitrogenDioxide();

        // then
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void getCo_ShouldReturnCorrectValue() {
        // given
        String expectedCo = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setConcentrationOfCarbonMonoxide(expectedCo);

        // when
        String actualCo = componentsDTO.getConcentrationOfCarbonMonoxide();

        // then
        assertEquals(expectedCo, actualCo);
    }

    @Test
    void setCo_ShouldSetCorrectValue() {
        // given
        String expectedCo = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfCarbonMonoxide(expectedCo);
        String actualCo = componentsDTO.getConcentrationOfCarbonMonoxide();

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
        String actualNo = componentsDTO.getConcentrationOfNitrogenMonoxide();

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
        String actualNo = componentsDTO.getConcentrationOfNitrogenMonoxide();

        // then
        assertEquals(expectedNo, actualNo);
    }

    // Repeat the above pattern for other getters and setters

    @Test
    void setNh3_WithNull_ShouldSetNullValue() {
        // given
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfAmmonia(null);
        String actualNh3 = componentsDTO.getConcentrationOfAmmonia();

        // then
        assertNull(actualNh3);
    }

    @Test
    void setNh3_WithNonEmptyString() {
        // given
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // when
        componentsDTO.setConcentrationOfAmmonia("test");
        String actualNh3 = componentsDTO.getConcentrationOfAmmonia();

        // then
        assertEquals("test", actualNh3);
    }
}
