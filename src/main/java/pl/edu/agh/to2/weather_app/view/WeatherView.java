package pl.edu.agh.to2.weather_app.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataToDisplay;
import pl.edu.agh.to2.weather_app.presenter.IWeatherPresenter;
import pl.edu.agh.to2.weather_app.utils.FXMLLoaderUtility;
import pl.edu.agh.to2.weather_app.utils.converter.AirQualityConverter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    @FXML
    private TextField forecastTimeInput;
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
                presenter.getWeatherByCities(List.of(aInputCity.getText(), bInputCity.getText()));
            } else {
                presenter.getWeatherByCity(aInputCity.getText());
            }
        } else if (aCoordsProvided) {
            if (bCoordsProvided) {
                presenter.getWeatherByCoordinates(List.of(aLatitudeInput.getText(), bLatitudeInput.getText()), List.of(aLongitudeInput.getText(), bLongitudeInput.getText()));
            } else {
                presenter.getWeatherByCoordinates(aLatitudeInput.getText(), aLongitudeInput.getText());
            }
        } else {
            showError("Please provide city name or coordinates");
        }
    }

    public void updateWeatherDisplay(WeatherDataToDisplay weatherData) {
        setWeatherOutputInformer("Weather in " + weatherData.getCityName() + " (" + weatherData.getCountry() + "): " + weatherData.getWeatherParameter() + "\n");
        setPressureValue(weatherData.getPressure() + " hPa");
        setHumidityValue(weatherData.getHumidity() + "%");
        setWindValue(weatherData.getWindSpeed() + " m/s");
        setSensedTemperatureValue(weatherData.getTemperature() + "" + (char)186 + "C");

        if (!weatherData.getAirQuality().equals("Unknown")) {
            setAirQuality(AirQualityConverter.getAirQualityString(weatherData.getAirQuality()));
        } else {
            setAirQuality("Unknown");
        }

        if (!isWeatherDisplaying()) {
            setWeatherDisplaying(true);
        }

        // Set weather icons
        List<String> icons = weatherData.getIconList();

        if (!icons.isEmpty()) {
            updateIcons(icons);
        } else {
            hideIcons();
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

    public void setACityInput(String city) {
        aInputCity.setText(city);
    }

    public void setALatitudeInput(String lat) {
        aLatitudeInput.setText(lat);
    }

    public void setALongitudeInput(String lon) {
        aLongitudeInput.setText(lon);
    }

    public void setTimeInput(String time) {
        forecastTimeInput.setText(time);
    }

    @FXML
    private void handleAddToFavourites() {
        String city = aInputCity.getText();
        String time = forecastTimeInput.getText();
        String lat = aLatitudeInput.getText();
        String lon = aLongitudeInput.getText();

        if (city.isEmpty() && time.isEmpty()  ) {
            showError("Please provide city name and time or coordinates and time");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Your Favourite Place");
        dialog.setHeaderText("Enter favourite place name");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(customName -> {
            if (!customName.isEmpty()) {
                presenter.addFavourite(customName, city, lon, lat, time);
            } else {
                showError("Please provide custom name");
            }
        });
    }

    @FXML
    private void handleShowFavourites() {
        try {
            Parent favouritesView = FXMLLoaderUtility.loadFavouritesView();
            Stage stage = new Stage();
            stage.setScene(new Scene(favouritesView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
