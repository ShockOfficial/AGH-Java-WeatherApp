package pl.edu.agh.to2.weather_app.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;

import java.util.List;

import static org.testfx.api.FxAssert.verifyThat;

class WeatherViewTest extends ApplicationTest {

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=test&appid=1&units=metric";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/weatherApp/weatherApp.fxml"));
        Parent root = loader.load();
        WeatherView weatherView = loader.getController();
        MockPresenter presenter = new MockPresenter(weatherView);
        weatherView.setPresenter(presenter);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void displayWeatherFromCoords() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aLatitudeInput").write("1");
        robot.clickOn("#aLongitudeInput").write("1");
        robot.clickOn(".button");

        //then
        Platform.runLater(() -> {
            WaitForAsyncUtils.waitForFxEvents();
            verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
            verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
            verifyThat("#sensedTemperatureValue", LabeledMatchers.hasText("1.0ºC"));

        });
    }

    @Test
    void displayWeatherFromCity() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aInputCity").write("test");
        robot.clickOn(".button");

        //then
        Platform.runLater(() -> {
            WaitForAsyncUtils.waitForFxEvents();
            verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
            verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
            verifyThat("#sensedTemperatureValue", LabeledMatchers.hasText("1.0ºC"));
        });
    }

    @Test
    void displayWeatherFromCoordsForTwoPlaces() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aLatitudeInput").write("1");
        robot.clickOn("#aLongitudeInput").write("1");
        robot.clickOn(".button");
        robot.clickOn("#bLatitudeInput").write("2");
        robot.clickOn("#bLongitudeInput").write("2");
        robot.clickOn(".button");
        //then
        verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
        verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
        verifyThat("#sensedTemperatureValue", LabeledMatchers.hasText("1.0ºC"));
    }

    private record MockPresenter(WeatherView view) implements IWeatherPresenter {

        @Override
        public void getWeatherByCity(String city) {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void getWeatherByCoordinates(String lon, String lat) {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void getWeatherByCities(String cityA, String cityB) {
        }

        @Override
        public void getWeatherByCoordinates(String latA, String lonA, String latB, String lonB) {
        }

        private void insertMockData() {
            view.updateWeatherDisplay(getExampleWeatherData());
        }
    }

    private static WeatherData getExampleWeatherData() {
        WeatherData weatherData = new WeatherData();
        weatherData.setName("Perfect city");
        weatherData.setClouds(new CloudsDTO());
        weatherData.getClouds().setAll(1);
        weatherData.setMain(new MainInfoDTO());
        weatherData.getMain().setPressure(1);
        weatherData.getMain().setHumidity(1);
        weatherData.getMain().setTemp(1);
        weatherData.getMain().setTempMax(1);
        weatherData.getMain().setTempMin(1);
        weatherData.setWind(new WindDTO());
        weatherData.getWind().setSpeed(1);
        weatherData.getMain().setFeelsLike(1);
        SysDTO sys = new SysDTO();
        sys.setCountry("Perfect Country");
        weatherData.setSys(sys);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain("Perfect weather");
        weather.setIconList(List.of(URL, URL));
        weatherData.setWeather(weather);
        return weatherData;
    }
}
