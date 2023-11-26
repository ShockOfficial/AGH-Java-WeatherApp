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
    public void handleGetWeatherAction() {
        if (!cityInput.getText().isEmpty()) {
            presenter.getWeatherByCity(cityInput.getText());
        } else if (!latitudeInput.getText().isEmpty() && !longitudeInput.getText().isEmpty()) {
            presenter.getWeatherByCoordinates(latitudeInput.getText(), longitudeInput.getText());
        } else {
            weatherOutput.setText("Please provide city name or coordinates");
        }
    }

    public void updateWeatherDisplay(String weatherInfo) {
        // TODO
    }
}
