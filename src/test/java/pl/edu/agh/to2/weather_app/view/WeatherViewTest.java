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
import pl.edu.agh.to2.weather_app.model.air_pollution_data.AirPollutionData;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirListElementDTO;
import pl.edu.agh.to2.weather_app.model.air_pollution_data.json.AirMainInfoDTO;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataToDisplay;
import pl.edu.agh.to2.weather_app.model.weather_data.json.*;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
import pl.edu.agh.to2.weather_app.presenter.impl.FavouritesPresenterImpl;


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
        robot.clickOn("#aForecastTimeInput").write("12:00");
        robot.clickOn(".button");

        //then
        Platform.runLater(() -> {
            WaitForAsyncUtils.waitForFxEvents();
            verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
            verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
            verifyThat("#humidityValue", LabeledMatchers.hasText("1%"));

        });
    }

    @Test
    void displayWeatherFromCity() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aInputCity").write("test");
        robot.clickOn("#aForecastTimeInput").write("12:00");
        robot.clickOn(".button");

        //then
        Platform.runLater(() -> {
            WaitForAsyncUtils.waitForFxEvents();
            verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
            verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
            verifyThat("#humidityValue", LabeledMatchers.hasText("1%"));
        });
    }

    @Test
    void displayWeatherFromCoordsForTwoPlaces() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aLatitudeInput").write("1");
        robot.clickOn("#aLongitudeInput").write("1");
        robot.clickOn("#aForecastTimeInput").write("12:00");
        robot.clickOn(".button");
        robot.clickOn("#bLatitudeInput").write("2");
        robot.clickOn("#bLongitudeInput").write("2");
        robot.clickOn("#bForecastTimeInput").write("12:00");
        robot.clickOn(".button");
        //then
        verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
        verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
        verifyThat("#humidityValue", LabeledMatchers.hasText("1%"));
    }

    @Test
    void displayWeatherFromCoordsForFivePlaces() {
        //when
        FxRobot robot = new FxRobot();
        robot.clickOn("#aLatitudeInput").write("1");
        robot.clickOn("#aLongitudeInput").write("1");
        robot.clickOn("#bLatitudeInput").write("2");
        robot.clickOn("#bLongitudeInput").write("2");
        robot.clickOn("#cLatitudeInput").write("3");
        robot.clickOn("#cLongitudeInput").write("3");
        robot.clickOn("#dLatitudeInput").write("4");
        robot.clickOn("#dLongitudeInput").write("4");
        robot.clickOn("#eLatitudeInput").write("5");
        robot.clickOn("#eLongitudeInput").write("5");
        robot.clickOn("#aForecastTimeInput").write("12:00");
        robot.clickOn("#bForecastTimeInput").write("12:42");
        robot.clickOn("#cForecastTimeInput").write("12:12");
        robot.clickOn("#dForecastTimeInput").write("12:12");
        robot.clickOn("#eForecastTimeInput").write("12:32");
        robot.clickOn(".button");
        //then
        verifyThat("#pressureValue", LabeledMatchers.hasText("1 hPa"));
        verifyThat("#windValue", LabeledMatchers.hasText("1.0 m/s"));
        verifyThat("#humidityValue", LabeledMatchers.hasText("1%"));
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
        public void getWeatherByCities(List<String> cities) {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void getWeatherByCoordinates(List<String> lat, List<String> lon) {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void getForecast(List<String> cities, List<String> citiesTimes, List<String> latList, List<String> lonList, List<String> coordsTimes) {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void handleGetForecastAction() {
            Platform.runLater(this::insertMockData);
        }

        @Override
        public void onShowFavouritesAction() {

        }


        private void insertMockData() {
            WeatherData weatherData = getExampleWeatherData();
            view.updateWeatherDisplay(new WeatherDataToDisplay(weatherData));
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
        AirPollutionData airPollutionData = new AirPollutionData();
        AirListElementDTO airListElementDTO = new AirListElementDTO();
        AirMainInfoDTO airMainInfoDTO = new AirMainInfoDTO();
        airListElementDTO.setMainInfo(airMainInfoDTO);
        airPollutionData.setPollutionList(List.of(airListElementDTO));
        weatherData.setAirPollutionData(airPollutionData);
        SysDTO sys = new SysDTO();
        sys.setCountry("Perfect Country");
        weatherData.setSys(sys);
        WeatherDTO weather = new WeatherDTO();
        weather.setMain("Perfect weather");
        weather.setIconList(List.of(URL, URL));
        weather.setIcon(URL);
        weatherData.setWeather(weather);
        return weatherData;
    }
}
