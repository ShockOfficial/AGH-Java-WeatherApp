package pl.edu.agh.to2.weather_app.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFetchExceptionTest {

    @Test
    void dataFetchException_ShouldHaveCorrectMessage() {
        // // given
        String expectedMessage = "Error while fetching data.";

        // when
        DataFetchException dataFetchException = new DataFetchException(expectedMessage);
        String actualMessage = dataFetchException.getMessage();

        // then
        assertEquals(expectedMessage, actualMessage);
    }
}
