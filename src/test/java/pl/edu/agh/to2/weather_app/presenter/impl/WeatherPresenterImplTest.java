package pl.edu.agh.to2.weather_app.presenter.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.edu.agh.to2.weather_app.api.DataProvider;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirMainInfoDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataToDisplay;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.IWeatherModel;
import pl.edu.agh.to2.weather_app.persistence.favourite.FavouritesDao;
import pl.edu.agh.to2.weather_app.utils.TempCalculator;
import pl.edu.agh.to2.weather_app.view.WeatherView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        CompletableFuture.runAsync(() -> presenter.getWeatherByCity(city)).thenAccept(aVoid -> {
            try {
                // then
                verify(mockView).updateWeatherDisplay(new WeatherDataToDisplay(mockWeatherData));
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    @Test
    void testGetWeatherByCoordinates() {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        FavouritesDao mockFavouritesDao = mock(FavouritesDao.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        String lat = "12.34";
        String lon = "56.78";
        WeatherData mockWeatherData = mock(WeatherData.class);
        when(mockModel.getWeatherDataByCoordinates(lat, lon)).thenReturn(CompletableFuture.completedFuture(mockWeatherData));

        // when
        CompletableFuture.runAsync(() -> presenter.getWeatherByCoordinates(lat, lon)).thenAccept(aVoid -> {
            try {
                // then
                verify(mockView).updateWeatherDisplay(new WeatherDataToDisplay(mockWeatherData));
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
        FavouritesDao mockFavouritesDao = mock(FavouritesDao.class);
        DataProvider mockDataProvider = mock(DataProvider.class);
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(mockModel, mockView, mockMerger, mockDataProvider);
        WeatherData weatherData = getExampleWeatherData();
        Method privateMethod = WeatherPresenterImpl.class.getDeclaredMethod("updateWeatherDisplay", WeatherDataToDisplay.class);
        privateMethod.setAccessible(true);

        // when
        privateMethod.invoke(presenter, new WeatherDataToDisplay(weatherData));

        // then
        InOrder inOrder = inOrder(mockView);

        inOrder.verify(mockView).updateWeatherDisplay(argThat(updatedWeatherData -> {
            assertEquals("Krakow", updatedWeatherData.getCityName());
            assertEquals("Poland", updatedWeatherData.getCountry());
            assertEquals("Clouds", updatedWeatherData.getWeatherParameter());
            assertEquals(1000, updatedWeatherData.getPressure());
            assertEquals(50, updatedWeatherData.getHumidity());
            return true;
        }));

        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void testGetWeatherByCities() {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        FavouritesDao mockFavouritesDao = mock(FavouritesDao.class);
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
                presenter.getWeatherByCities(List.of(cityA, cityB));
                verify(mockModel, times(1)).getWeatherDataByCity(cityA);
                verify(mockModel, times(1)).getWeatherDataByCity(cityB);
                verify(mockView, never()).showError(any());
                verify(mockView, never()).updateWeatherDisplay(any(WeatherDataToDisplay.class));
                future.complete(null);
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        future.join();

    }

    @Test
    void testGetWeatherByCoordinatesForTwoPlaces() throws InterruptedException {
        // given
        IWeatherModel mockModel = mock(IWeatherModel.class);
        WeatherView mockView = mock(WeatherView.class);
        WeatherDataMerger mockMerger = mock(WeatherDataMerger.class);
        FavouritesDao mockFavouritesDao = mock(FavouritesDao.class);
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
                presenter.getWeatherByCoordinates(List.of(latA, latB), List.of(lonA, lonB));
                verify(mockModel, times(1)).getWeatherDataByCoordinates(latA, lonA);
                verify(mockModel, times(1)).getWeatherDataByCoordinates(latB, lonB);
                verify(mockView, never()).showError(any());
                verify(mockView, never()).updateWeatherDisplay(any(WeatherDataToDisplay.class));
                future.complete(null);
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });

        // then
        future.thenAccept(result -> {
            verify(mockView, times(1)).updateWeatherDisplay(any(WeatherDataToDisplay.class));
        });
    }

    @NotNull
    private static WeatherData getExampleWeatherData() {
        WeatherData weatherData = new WeatherData();
        weatherData.setName("Krakow");
        SysDTO sysDTO = new SysDTO();
        sysDTO.setCountry("Poland");
        weatherData.setSys(sysDTO);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain("Clouds");
        weather.setIcon("04d");
        weatherData.setWeather(weather);
        MainInfoDTO mainInfoDTO = new MainInfoDTO();
        mainInfoDTO.setTemp((float) TempCalculator.calculatePerceivedTemp(10F, 10F));
        mainInfoDTO.setFeelsLike(10);
        mainInfoDTO.setPressure(1000);
        mainInfoDTO.setHumidity(50);
        weatherData.setMain(mainInfoDTO);
        WindDTO windDTO = new WindDTO();
        windDTO.setSpeed(10);
        weatherData.setWind(windDTO);
        AirPollutionData airPollutionData = new AirPollutionData();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
        airListElementDTO.setMainInfo(airMainInfoDTO);
        airPollutionData.setPollutionList(List.of(airListElementDTO));
        weatherData.setAirPollutionData(airPollutionData);
        TotalFallDTO totalFallDTO = new TotalFallDTO();
        totalFallDTO.setOneH(0);
        weatherData.setRain(totalFallDTO);
        TotalFallDTO totalFallDTO1 = new TotalFallDTO();
        totalFallDTO1.setOneH(10);
        weatherData.setSnow(totalFallDTO1);
        weatherData.getRain().setOneH(0);
        weatherData.getSnow().setOneH(10);
        return weatherData;
    }

}
