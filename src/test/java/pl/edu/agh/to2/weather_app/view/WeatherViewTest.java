package pl.edu.agh.to2.weather_app.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;
import pl.edu.agh.to2.weather_app.model.weatherData.json.*;
import pl.edu.agh.to2.weather_app.presenter.WeatherPresenter;

import static org.testfx.api.FxAssert.verifyThat;


public class WeatherViewTest extends ApplicationTest {

    private WeatherView weatherView;
    private static class MockPresenter implements WeatherPresenter {
        private final WeatherView view;

        public MockPresenter(WeatherView view){
            this.view = view;
        }
        @Override
        public void getWeatherByCity(String city){
            Platform.runLater(() -> insertMockData());
        }
        @Override
        public void getWeatherByCoordinates(String lon, String lat){
            Platform.runLater(() -> insertMockData());
        }
        private void insertMockData(){
            view.updateWeatherDisplay(getExampleWeatherData());
        }
    }

    @Override
    public void start(Stage stage) throws Exception{
        //Given
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/weatherApp/weatherApp.fxml"));
        Parent root = loader.load();
        this.weatherView = loader.getController();
        MockPresenter presenter = new MockPresenter(this.weatherView);
        weatherView.setPresenter(presenter);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void displayWeatherFromCity(){
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#cityInput").write("test");
        robot.clickOn(".button");

        //then
        verifyThat("#cloudinessValue", LabeledMatchers.hasText("1"));
        verifyThat("#pressureValue", LabeledMatchers.hasText("1"));
        verifyThat("#humidityValue", LabeledMatchers.hasText("1"));
        verifyThat("#windValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#temperatureValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#minimumTemperatureValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#maximumTemperatureValue", LabeledMatchers.hasText("1.0"));

    }

    @Test
    void displayWeatherFromCoords(){
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#latitudeInput").write("1");
        robot.clickOn("#longitudeInput").write("1");
        robot.clickOn(".button");

        //then
        verifyThat("#cloudinessValue", LabeledMatchers.hasText("1"));
        verifyThat("#pressureValue", LabeledMatchers.hasText("1"));
        verifyThat("#humidityValue", LabeledMatchers.hasText("1"));
        verifyThat("#windValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#temperatureValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#minimumTemperatureValue", LabeledMatchers.hasText("1.0"));
        verifyThat("#maximumTemperatureValue", LabeledMatchers.hasText("1.0"));

    }

    @NotNull
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
        SysDTO sys = new SysDTO();
        sys.setCountry("Perfect Country");
        weatherData.setSys(sys);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain("Perfect weather");
        weatherData.setWeather(weather);
        return weatherData;
    }

}
