package pl.edu.agh.to2.weather_app.presenter.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.model.weather_data.json.MainInfoDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.json.SysDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.json.WindDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

class WeatherPresenterImplTest {

    @BeforeAll
    static void init() {
        // Initialize JavaFX Toolkit
        new JFXPanel();
    }

    @Test
    void testGetWeatherByCity() {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        String city = "TestCity";
        WeatherData mockWeatherData = mock(WeatherData.class);
        CompletableFuture<WeatherData> weatherDataFuture = CompletableFuture.completedFuture(mockWeatherData);
        when(mockModel.getWeatherDataByCity(city)).thenReturn(weatherDataFuture);

        // when
        presenter.getWeatherByCity(city);

        // then
        assertTimeoutPreemptively(
                Duration.ofDays(TimeUnit.SECONDS.toMillis(5)),
                () -> assertDoesNotThrow(() -> verify(mockView).updateWeatherDisplay(mockWeatherData))
        );
    }



    @Test
    void testGetWeatherByCoordinates(){
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        String lat = "12.34";
        String lon = "56.78";
        WeatherData mockWeatherData = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCoordinates(lat, lon)).thenReturn(CompletableFuture.completedFuture(mockWeatherData));

        // when
        CompletableFuture.runAsync(() -> presenter.getWeatherByCoordinates(lat, lon)).thenAccept(aVoid -> {
            try {
                // then
                verify(mockView).updateWeatherDisplay(mockWeatherData);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        });

    }

    @Test
    void testUpdateWeatherDisplay() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
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
            assertEquals(22, updatedWeatherData.getMain().getTempMin(), 0.01);
            assertEquals(28, updatedWeatherData.getMain().getTempMax(), 0.01);
            return true;
        }));

        verifyNoMoreInteractions(mockView);
    }

    @Test
    void testGetWeatherByCities() {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        String cityA = "TestCityA";
        String cityB = "TestCityB";
        WeatherData mockWeatherDataA = mock(WeatherData.class);
        WeatherData mockWeatherDataB = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCity(cityA)).thenReturn(CompletableFuture.completedFuture(mockWeatherDataA));
        when(mockModel.getWeatherDataByCity(cityB)).thenReturn(CompletableFuture.completedFuture(mockWeatherDataB));

        // when
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                presenter.getWeatherByCities(cityA, cityB);
                verify(mockModel, times(1)).getWeatherDataByCity(cityA);
                verify(mockModel, times(1)).getWeatherDataByCity(cityB);
                verify(mockView, never()).showError(any());
                verify(mockView, never()).updateWeatherDisplay(any(WeatherData.class));
                future.complete(null);
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        future.join();

    }

    @Test
    void testGetWeatherByCoordinatesForTwoPlaces() {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        String latA = "12.34";
        String lonA = "56.78";
        String latB = "34.56";
        String lonB = "78.90";
        WeatherData mockWeatherDataA = mock(WeatherData.class);
        WeatherData mockWeatherDataB = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCoordinates(latA, lonA)).thenReturn(CompletableFuture.completedFuture(mockWeatherDataA));
        when(mockModel.getWeatherDataByCoordinates(latB, lonB)).thenReturn(CompletableFuture.completedFuture(mockWeatherDataB));

        // when
        CompletableFuture<Void> future = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                presenter.getWeatherByCoordinates(latA, lonA, latB, lonB);
                verify(mockModel, times(1)).getWeatherDataByCoordinates(latA, lonA);
                verify(mockModel, times(1)).getWeatherDataByCoordinates(latB, lonB);
                verify(mockView, never()).showError(any());
                verify(mockView, never()).updateWeatherDisplay(any(WeatherData.class));
                future.complete(null);
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });

        // then
        future.thenAccept(result -> {
            verify(mockView, times(1)).updateWeatherDisplay(any(WeatherData.class));
        });
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
