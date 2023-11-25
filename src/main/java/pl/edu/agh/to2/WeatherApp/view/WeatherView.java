package pl.edu.agh.to2.WeatherApp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;

public class WeatherView {
    @FXML
    private TextField cityInput;

    @FXML
    private TextField latitudeInput;

    @FXML
    private TextField longitudeInput;

    @FXML
    private Label weatherOutput;

    private WeatherPresenter presenter;

    public void setPresenter(WeatherPresenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    private void handleGetWeatherAction() {
        // TODO
    }

    public void updateWeatherDisplay(String weatherInfo) {
        // TODO
    }
}
