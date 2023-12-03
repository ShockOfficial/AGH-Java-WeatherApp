package pl.edu.agh.to2.WeatherApp.presenter.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javafx.embed.swing.JFXPanel;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.model.weatherData.json.MainInfoDTO;
import pl.edu.agh.to2.WeatherApp.model.weatherData.json.SysDTO;
import pl.edu.agh.to2.WeatherApp.model.weatherData.json.WindDTO;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class WeatherDTOPresenterImplTest {

    @BeforeAll
    static void init() {
        // Initialize JavaFX Toolkit
        new JFXPanel();
    }

    @Test
    void testGetWeatherByCity() throws InterruptedException, ExecutionException {
        // given
        WeatherModel mockModel = mock(WeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView);
        String city = "TestCity";
        WeatherData mockWeatherData = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCity(city)).thenReturn(CompletableFuture.completedFuture(mockWeatherData));

        // when
        presenter.getWeatherByCity(city);
        Thread.sleep(1000); // Consider using CompletableFuture.join or other non-blocking methods

        // then
        verify(mockView).updateWeatherDisplay(mockWeatherData);
    }

    @Test
    void testGetWeatherByCoordinates() throws InterruptedException {
        // given
        WeatherModel mockModel = mock(WeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView);
        String lat = "12.34";
        String lon = "56.78";
        WeatherData mockWeatherData = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCoordinates(lat, lon)).thenReturn(CompletableFuture.completedFuture(mockWeatherData));

        // when
        presenter.getWeatherByCoordinates(lat, lon);
        Thread.sleep(1000);

        // then
        verify(mockView).updateWeatherDisplay(mockWeatherData);
    }

    @Test
    void testUpdateWeatherDisplay() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        WeatherModel mockModel = mock(WeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView);
        WeatherData weatherData = getExampleWeatherData();
        Method privateMethod = WeatherPresenterImpl.class.getDeclaredMethod("updateWeatherDisplay", WeatherData.class);
        privateMethod.setAccessible(true);

        // when
        privateMethod.invoke(presenter, weatherData);

        // then
        verify(mockView).updateWeatherDisplay(argThat(updatedWeatherData -> {
            assertEquals("Unknown", updatedWeatherData.getName());
            assertEquals("Poland", updatedWeatherData.getSys().getCountry());
            assertEquals(25, updatedWeatherData.getMain().getTemp(), 0.01);
            assertEquals(26, updatedWeatherData.getMain().getFeelsLike(), 0.01);
            assertEquals(22, updatedWeatherData.getMain().getTempMin(), 0.01);
            assertEquals(28, updatedWeatherData.getMain().getTempMax(), 0.01);
            return true;
        }));

        verifyNoMoreInteractions(mockView);
    }

    @NotNull
    private static WeatherData getExampleWeatherData() {
        WeatherData weatherData = new WeatherData();
        WindDTO windDTO = new WindDTO();
        windDTO.setSpeed(12);
        SysDTO sysDTO = new SysDTO();
        sysDTO.setCountry("Poland");
        weatherData.setSys(sysDTO);
        MainInfoDTO main = new MainInfoDTO();
        main.setTemp(25);
        main.setFeelsLike(26);
        main.setTempMin(22);
        main.setTempMax(28);
        weatherData.setMain(main);
        weatherData.setWind(windDTO);
        return weatherData;
    }
}