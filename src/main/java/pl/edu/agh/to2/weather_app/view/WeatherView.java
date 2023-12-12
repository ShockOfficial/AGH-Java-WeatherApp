package pl.edu.agh.to2.weather_app.view;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.edu.agh.to2.weather_app.model.weatherData.WeatherData;
import pl.edu.agh.to2.weather_app.presenter.WeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.converter.AirQualityConverter;

import java.util.List;

public class WeatherView {
    @FXML
    private TextField aInputCity;
    @FXML
    private TextField bInputCity;
    @FXML
    private TextField aLatitudeInput;
    @FXML
    private TextField aLongitudeInput;
    @FXML
    private TextField bLatitudeInput;
    @FXML
    private TextField bLongitudeInput;
    @FXML
    public Label weatherError;
    @FXML
    private VBox weatherInfo;
    @FXML
    private Label weatherOutputInformer;
    @FXML
    private Label humidityValue;
    @FXML
    private Label pressureValue;
    @FXML
    private Label windValue;
    @FXML
    private Label sensedTemperatureValue;
    @FXML
    private Label airQuality;
    @FXML
    private ImageView weatherIconLeft;
    @FXML
    private ImageView weatherIconRight;
    @FXML
    private ImageView maskIcon;
    private WeatherPresenter presenter;

    @FXML
    public void initialize() {
        setupInputListeners();
    }

    private void updateFieldsState() {
        boolean cityFieldsFilled = !aInputCity.getText().isEmpty() || !bInputCity.getText().isEmpty();
        boolean cordFieldsFilled = !aLatitudeInput.getText().isEmpty() || !aLongitudeInput.getText().isEmpty()
                || !bLatitudeInput.getText().isEmpty() || !bLongitudeInput.getText().isEmpty();

        aLatitudeInput.setDisable(cityFieldsFilled);
        aLongitudeInput.setDisable(cityFieldsFilled);
        bLatitudeInput.setDisable(cityFieldsFilled);
        bLongitudeInput.setDisable(cityFieldsFilled);

        aInputCity.setDisable(cordFieldsFilled);
        bInputCity.setDisable(cordFieldsFilled);
    }

    private void setupInputListeners() {
        aInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());
        bInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());

        aLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());
        aLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());
        bLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());
        bLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateFieldsState());
    }

    @Inject
    public void setPresenter(WeatherPresenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    public void handleGetWeatherAction() {
        boolean aCityProvided = !aInputCity.getText().isEmpty();
        boolean bCityProvided = !bInputCity.getText().isEmpty();
        boolean aCoordsProvided = !aLatitudeInput.getText().isEmpty() && !aLongitudeInput.getText().isEmpty();
        boolean bCoordsProvided = !bLatitudeInput.getText().isEmpty() && !bLongitudeInput.getText().isEmpty();

        if (aCityProvided) {
            if (bCityProvided) {
                presenter.getWeatherByCities(aInputCity.getText(), bInputCity.getText());
            } else {
                presenter.getWeatherByCity(aInputCity.getText());
            }
        } else if (aCoordsProvided) {
            if (bCoordsProvided) {
                presenter.getWeatherByCoordinates(aLatitudeInput.getText(), aLongitudeInput.getText(), bLatitudeInput.getText(), bLongitudeInput.getText());
            } else {
                presenter.getWeatherByCoordinates(aLatitudeInput.getText(), aLongitudeInput.getText());
            }
        } else {
            showError("Please provide city name or coordinates");
        }
    }

    public void updateWeatherDisplay(WeatherData weatherData) {
        if (weatherData.getSys() != null) {
            setWeatherOutputInformer("Weather in " + weatherData.getName() + " (" + weatherData.getSys().getCountry() + "): " + weatherData.getWeather().get(0).getMain() + "\n");

            setPressureValue(weatherData.getMain().getPressure() + " hPa");
            setHumidityValue(weatherData.getMain().getHumidity() + "%");
            setWindValue(weatherData.getWind().getSpeed() + " m/s");
            setSensedTemperatureValue(weatherData.getMain().getFeelsLike() + "" + (char)186 + "C");

            if (weatherData.getAirPollutionData() != null) {
                setAirQuality(AirQualityConverter.getAirQualityString(weatherData.getAirPollutionData()));
            } else {
                setAirQuality("Unknown");
            }

            updateTemperatureValueColor(weatherData.getMain().getFeelsLike());

            if (!isWeatherDisplaying()) {
                setWeatherDisplaying(true);
            }

            // Set weather icons
            List<String> icons = weatherData.getWeather().get(0).getIconList();

            if (!icons.isEmpty()) {
                updateIcons(icons);
            } else {
                hideIcons();
            }
        } else {
            showError("City not found");
        }
    }

    private void updateIcons(List<String> icons) {
        List<ImageView> iconsImageViews = List.of(weatherIconLeft, weatherIconRight, maskIcon);
        int counter = 0;
        for (String icon : icons) {
            Image image = new Image(icon);
            Image imageView = new ImageView(image).getImage();
            imageView = new Image(imageView.getUrl(), 145, 100, false, true);
            iconsImageViews.get(counter).setImage(imageView);
            iconsImageViews.get(counter).setVisible(true);
            counter++;
        }
        for (int i = counter; i < 3; i++) {
            iconsImageViews.get(i).setImage(null);
            iconsImageViews.get(i).setVisible(false);
        }
    }

    private void hideIcons() {
        weatherIconLeft.setImage(null);
        weatherIconLeft.setVisible(false);
        weatherIconRight.setImage(null);
        weatherIconRight.setVisible(false);
        maskIcon.setImage(null);
        maskIcon.setVisible(false);
    }

    public void setWeatherError(String weatherError) {
        this.weatherError.setText(weatherError);
    }

    public void setWeatherOutputInformer(String weatherOutput) {
        this.weatherOutputInformer.setText(weatherOutput);
    }

    public boolean isWeatherDisplaying() {
        return weatherInfo.isVisible();
    }

    public void setWeatherDisplaying(boolean weatherDisplaying) {
        this.weatherInfo.setVisible(weatherDisplaying);
        this.weatherError.setVisible(!weatherDisplaying);
        if (!weatherDisplaying) {
            hideIcons();
        }
    }

    public void setHumidityValue(String humidityValue) {
        this.humidityValue.setText(humidityValue);
    }

    public void setPressureValue(String pressureValue) {
        this.pressureValue.setText(pressureValue);
    }

    public void setWindValue(String windValue) {
        this.windValue.setText(windValue);
    }

    public void setSensedTemperatureValue(String sensedTemperatureValue) {
        this.sensedTemperatureValue.setText(sensedTemperatureValue);
    }

    public void setAirQuality(String airQuality) {
        this.airQuality.setText(airQuality);
    }

    public void showError(String error) {
        setWeatherError(error);
        setWeatherDisplaying(false);
    }

    // Update color of label displaying temperature, according to the temperature scale
    // (cold (-inf;0), medium <0;10), warm <10;20), hot <20;inf))
    public void updateTemperatureValueColor(float temperature) {
        sensedTemperatureValue.getStyleClass().removeIf(c -> c.startsWith("temperature"));
        if (temperature < 0) {
            sensedTemperatureValue.getStyleClass().add("temperature-cold");
        } else if (temperature < 10) {
            sensedTemperatureValue.getStyleClass().add("temperature-medium");
        } else if (temperature < 20) {
            sensedTemperatureValue.getStyleClass().add("temperature-warm");
        } else {
            sensedTemperatureValue.getStyleClass().add("temperature-hot");
        }
    }
}
