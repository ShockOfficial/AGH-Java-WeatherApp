package pl.edu.agh.to2.weather_app.model.airPollutionData.json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ComponentsDTOTest {


    @Test
    void getPm10_ShouldReturnCorrectValue() {
        // Arrange
        String expectedPm10 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setPm10(expectedPm10);

        // Act
        String actualPm10 = componentsDTO.getPm10();

        // Assert
        assertEquals(expectedPm10, actualPm10);
    }

    @Test
    void setPm10_ShouldSetCorrectValue() {
        // Arrange
        String expectedPm10 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setPm10(expectedPm10);
        String actualPm10 = componentsDTO.getPm10();

        // Assert
        assertEquals(expectedPm10, actualPm10);
    }

    @Test

    void getPm25_ShouldReturnCorrectValue() {
        // Arrange
        String expectedPm25 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setPm2_5(expectedPm25);

        // Act
        String actualPm25 = componentsDTO.getPm2_5();

        // Assert
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void setPm25_ShouldSetCorrectValue() {
        // Arrange
        String expectedPm25 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setPm2_5(expectedPm25);
        String actualPm25 = componentsDTO.getPm2_5();

        // Assert
        assertEquals(expectedPm25, actualPm25);
    }

    @Test
    void getO3_ShouldReturnCorrectValue() {
        // Arrange
        String expectedO3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setO3(expectedO3);

        // Act
        String actualO3 = componentsDTO.getO3();

        // Assert
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void setO3_ShouldSetCorrectValue() {
        // Arrange
        String expectedO3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setO3(expectedO3);
        String actualO3 = componentsDTO.getO3();

        // Assert
        assertEquals(expectedO3, actualO3);
    }

    @Test
    void getSo2_ShouldReturnCorrectValue() {
        // Arrange
        String expectedSo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setSo2(expectedSo2);

        // Act
        String actualSo2 = componentsDTO.getSo2();

        // Assert
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void setSo2_ShouldSetCorrectValue() {
        // Arrange
        String expectedSo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setSo2(expectedSo2);
        String actualSo2 = componentsDTO.getSo2();

        // Assert
        assertEquals(expectedSo2, actualSo2);
    }

    @Test
    void getNh3_ShouldReturnCorrectValue() {
        // Arrange
        String expectedNh3 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNh3(expectedNh3);

        // Act
        String actualNh3 = componentsDTO.getNh3();

        // Assert
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void setNh3_ShouldSetCorrectValue() {
        // Arrange
        String expectedNh3 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setNh3(expectedNh3);
        String actualNh3 = componentsDTO.getNh3();

        // Assert
        assertEquals(expectedNh3, actualNh3);
    }

    @Test
    void getNo2_ShouldReturnCorrectValue() {
        // Arrange
        String expectedNo2 = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNo2(expectedNo2);

        // Act
        String actualNo2 = componentsDTO.getNo2();

        // Assert
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void setNo2_ShouldSetCorrectValue() {
        // Arrange
        String expectedNo2 = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setNo2(expectedNo2);
        String actualNo2 = componentsDTO.getNo2();

        // Assert
        assertEquals(expectedNo2, actualNo2);
    }

    @Test
    void getCo_ShouldReturnCorrectValue() {
        // Arrange
        String expectedCo = "0.5";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setCo(expectedCo);

        // Act
        String actualCo = componentsDTO.getCo();

        // Assert
        assertEquals(expectedCo, actualCo);
    }

    @Test
    void setCo_ShouldSetCorrectValue() {
        // Arrange
        String expectedCo = "1.0";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setCo(expectedCo);
        String actualCo = componentsDTO.getCo();

        // Assert
        assertEquals(expectedCo, actualCo);
    }

    @Test
    void getNo_ShouldReturnCorrectValue() {
        // Arrange
        String expectedNo = "0.2";
        ComponentsDTO componentsDTO = new ComponentsDTO();
        componentsDTO.setNo(expectedNo);

        // Act
        String actualNo = componentsDTO.getNo();

        // Assert
        assertEquals(expectedNo, actualNo);
    }

    @Test
    void setNo_ShouldSetCorrectValue() {
        // Arrange
        String expectedNo = "0.7";
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setNo(expectedNo);
        String actualNo = componentsDTO.getNo();

        // Assert
        assertEquals(expectedNo, actualNo);
    }

    // Repeat the above pattern for other getters and setters

    @Test
    void setNh3_WithNull_ShouldSetNullValue() {
        // Arrange
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setNh3(null);
        String actualNh3 = componentsDTO.getNh3();

        // Assert
        assertNull(actualNh3);
    }

    @Test
    void setNh3_WithNonEmptyString() {
        // Arrange
        ComponentsDTO componentsDTO = new ComponentsDTO();

        // Act
        componentsDTO.setNh3("test");
        String actualNh3 = componentsDTO.getNh3();

        // Assert
        assertEquals("test", actualNh3);
    }
}
