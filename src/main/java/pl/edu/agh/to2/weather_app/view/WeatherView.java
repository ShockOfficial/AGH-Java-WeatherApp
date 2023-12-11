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
    private Label cloudinessValue;
    @FXML
    private Label humidityValue;
    @FXML
    private Label pressureValue;
    @FXML
    private Label windValue;
    @FXML
    private Label rainValue;
    @FXML
    public Label snowValue;
    @FXML
    private Label temperatureValue;
    @FXML
    private Label minimumTemperatureValue;
    @FXML
    private Label sensedTemperatureValue;
    @FXML
    private Label maximumTemperatureValue;
    @FXML
    private ImageView weatherIconLeft;
    @FXML
    private ImageView weatherIconRight;

    private WeatherPresenter presenter;

    @FXML
    public void initialize() {
        setupInputListeners();
    }

    private void updateFieldsState() {
        boolean cityFieldsFilled = !aInputCity.getText().isEmpty() || !bInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !aLatitudeInput.getText().isEmpty() || !aLongitudeInput.getText().isEmpty()
                || !bLatitudeInput.getText().isEmpty() || !bLongitudeInput.getText().isEmpty();

        aLatitudeInput.setDisable(cityFieldsFilled);
        aLongitudeInput.setDisable(cityFieldsFilled);
        bLatitudeInput.setDisable(cityFieldsFilled);
        bLongitudeInput.setDisable(cityFieldsFilled);

        aInputCity.setDisable(coordFieldsFilled);
        bInputCity.setDisable(coordFieldsFilled);
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
                presenter.getWeatherByCoordinates(aLatitudeInput.getText(), aLongitudeInput.getText(),
                        bLatitudeInput.getText(), bLongitudeInput.getText());
            } else {
                presenter.getWeatherByCoordinates(aLatitudeInput.getText(), aLongitudeInput.getText());
            }
        } else {
            setWeatherError("Please provide at least A city name or A coordinates");
            setWeatherDisplaying(false);
            hideIcons();
        }
    }

    public void updateWeatherDisplay(WeatherData weatherData) {
        if (weatherData.getSys() != null) {
            setWeatherOutputInformer("Weather in " + weatherData.getName() + " (" + weatherData.getSys().getCountry() + "): " + weatherData.getWeather().get(0).getMain() + "\n");

            setCloudinessValue(Integer.toString(weatherData.getClouds().getAll()));
            setPressureValue(Integer.toString(weatherData.getMain().getPressure()));
            setHumidityValue(Integer.toString(weatherData.getMain().getHumidity()));
            setWindValue(Float.toString(weatherData.getWind().getSpeed()));

            if (weatherData.getRain() != null) {
                setRainValue(Float.toString(weatherData.getRain().getOneH()));
            } else {
                setRainValue("Unknown");
            }

            if (weatherData.getSnow() != null) {
                setSnowValue(Float.toString(weatherData.getSnow().getOneH()));
            } else {
                setSnowValue("Unknown");
            }

            setTemperatureValue(Float.toString(weatherData.getMain().getTemp()));
            setSensedTemperatureValue(Float.toString(weatherData.getMain().getFeelsLike()));
            setMinimumTemperatureValue(Float.toString(weatherData.getMain().getTempMin()));
            setMaximumTemperatureValue(Float.toString(weatherData.getMain().getTempMax()));

            if (!isWeatherDisplaying()) {
                setWeatherDisplaying(true);
            }

            // Set weather icons
            List<String> icons = weatherData.getWeather().get(0).getIconList();
            if (!icons.isEmpty()) {
                weatherIconLeft.setImage(new Image(icons.get(0)));
                weatherIconLeft.setVisible(true);

                if (icons.size() > 1) {
                    weatherIconRight.setImage(new Image(icons.get(1)));
                    weatherIconRight.setVisible(true);
                } else {
                    weatherIconRight.setVisible(false);
                }
            } else {
                hideIcons();
            }
        } else {
            setWeatherError("City not found");
            setWeatherDisplaying(false);
            hideIcons();
        }

    }

    private void hideIcons() {
        weatherIconLeft.setImage(null);
        weatherIconLeft.setVisible(false);
        weatherIconRight.setImage(null);
        weatherIconRight.setVisible(false);
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
    }

    public void setCloudinessValue(String cloudinessValue) {
        this.cloudinessValue.setText(cloudinessValue);
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

    public void setRainValue(String rainValue) {
        this.rainValue.setText(rainValue);
    }

    public void setSnowValue(String snowValue) {
        this.snowValue.setText(snowValue);
    }

    public void setTemperatureValue(String temperatureValue) {
        this.temperatureValue.setText(temperatureValue);
    }

    public void setMinimumTemperatureValue(String minimumTemperatureValue) {
        this.minimumTemperatureValue.setText(minimumTemperatureValue);
    }

    public void setSensedTemperatureValue(String sensedTemperatureValue) {
        this.sensedTemperatureValue.setText(sensedTemperatureValue);
    }

    public void setMaximumTemperatureValue(String maximumTemperatureValue) {
        this.maximumTemperatureValue.setText(maximumTemperatureValue);
    }
}
