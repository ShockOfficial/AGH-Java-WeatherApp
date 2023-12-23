package pl.edu.agh.to2.weather_app.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pl.edu.agh.to2.weather_app.model.forecast_data.ForecastData;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherData;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
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
    private ImageView informationIcon1;
    @FXML
    private ImageView informationIcon2;
    private IWeatherPresenter presenter;

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

    public void setPresenter(IWeatherPresenter presenter) {
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

    public void updateWeatherDisplay(ForecastData forecastData) {
        if (!forecastData.getWeatherList().isEmpty()) {
            //this is temporary solution so the code still works, replace it with proper implementation
            WeatherData weatherData = forecastData.getWeatherList().get(0);
            setWeatherOutputInformer("Weather in " + forecastData.getName() + " (" + forecastData.getCountry() + "): " + weatherData.getWeather().get(0).getMain() + "\n");

            setPressureValue(weatherData.getMain().getPressure() + " hPa");
            setHumidityValue(weatherData.getMain().getHumidity() + "%");
            setWindValue(weatherData.getWind().getSpeed() + " m/s");
            setSensedTemperatureValue(weatherData.getMain().getFeelsLike() + "" + (char)186 + "C");

            if (weatherData.getAirPollutionData() != null) {
                setAirQuality(AirQualityConverter.getAirQualityString(weatherData.getAirPollutionData()));
            } else {
                setAirQuality("Unknown");
            }

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
        List<ImageView> iconsImageViews = List.of(weatherIconLeft, weatherIconRight, informationIcon1, informationIcon2);
        int counter = 0;
        for (String icon : icons) {
            Image image = new Image(icon);
            Image imageView = new ImageView(image).getImage();
            imageView = new Image(imageView.getUrl(), 145, 100, false, true);
            iconsImageViews.get(counter).setImage(imageView);
            iconsImageViews.get(counter).setVisible(true);
            counter++;
        }
        for (int i = counter; i < iconsImageViews.size(); i++) {
            iconsImageViews.get(i).setImage(null);
            iconsImageViews.get(i).setVisible(false);
        }
    }

    private void hideIcons() {
        weatherIconLeft.setImage(null);
        weatherIconLeft.setVisible(false);
        weatherIconRight.setImage(null);
        weatherIconRight.setVisible(false);
        informationIcon1.setImage(null);
        informationIcon1.setVisible(false);
        informationIcon2.setImage(null);
        informationIcon2.setVisible(false);
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

    public void setTemperatureValueClass(String temperatureValueClass) {
        this.sensedTemperatureValue.getStyleClass().removeIf(c -> c.startsWith("temperature"));
        this.sensedTemperatureValue.getStyleClass().add(temperatureValueClass);
    }

    public void showError(String error) {
        setWeatherError(error);
        setWeatherDisplaying(false);
    }
}
