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
    private TextField cInputCity;
    @FXML
    private TextField dInputCity;
    @FXML
    private TextField eInputCity;
    @FXML
    private TextField aLatitudeInput;
    @FXML
    private TextField aLongitudeInput;
    @FXML
    private TextField bLatitudeInput;
    @FXML
    private TextField bLongitudeInput;
    @FXML
    private TextField cLatitudeInput;
    @FXML
    private TextField cLongitudeInput;
    @FXML
    private TextField dLatitudeInput;
    @FXML
    private TextField dLongitudeInput;
    @FXML
    private TextField eLatitudeInput;
    @FXML
    private TextField eLongitudeInput;
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
    private ImageView icon1;
    @FXML
    private ImageView icon2;
    @FXML
    private ImageView icon3;
    @FXML
    private ImageView icon4;
    @FXML
    private ImageView icon5;
    @FXML
    private ImageView icon6;
    @FXML
    private ImageView icon7;
    @FXML
    private TextField aForecastTimeInput;
    @FXML
    private TextField bForecastTimeInput;
    @FXML
    private TextField cForecastTimeInput;
    @FXML
    private TextField dForecastTimeInput;
    @FXML
    private TextField eForecastTimeInput;
    private IWeatherPresenter presenter;


    @FXML
    public void initialize() {
        setupInputListeners();
    }

    private void updateAFieldsState() {
        boolean cityFieldFilled = !aInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !aLatitudeInput.getText().isEmpty() || !aLongitudeInput.getText().isEmpty();

        aLatitudeInput.setDisable(cityFieldFilled);
        aLongitudeInput.setDisable(cityFieldFilled);

        aInputCity.setDisable(coordFieldsFilled);
    }

    private void updateBFieldsState() {
        boolean cityFieldFilled = !bInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !bLatitudeInput.getText().isEmpty() || !bLongitudeInput.getText().isEmpty();

        bLatitudeInput.setDisable(cityFieldFilled);
        bLongitudeInput.setDisable(cityFieldFilled);

        bInputCity.setDisable(coordFieldsFilled);
    }

    private void updateCFieldsState() {
        boolean cityFieldFilled = !cInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !cLatitudeInput.getText().isEmpty() || !cLongitudeInput.getText().isEmpty();

        cLatitudeInput.setDisable(cityFieldFilled);
        cLongitudeInput.setDisable(cityFieldFilled);

        cInputCity.setDisable(coordFieldsFilled);
    }

    private void updateDFieldsState() {
        boolean cityFieldFilled = !dInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !dLatitudeInput.getText().isEmpty() || !dLongitudeInput.getText().isEmpty();

        dLatitudeInput.setDisable(cityFieldFilled);
        dLongitudeInput.setDisable(cityFieldFilled);

        dInputCity.setDisable(coordFieldsFilled);
    }

    private void updateEFieldsState() {
        boolean cityFieldFilled = !eInputCity.getText().isEmpty();
        boolean coordFieldsFilled = !eLatitudeInput.getText().isEmpty() || !eLongitudeInput.getText().isEmpty();

        eLatitudeInput.setDisable(cityFieldFilled);
        eLongitudeInput.setDisable(cityFieldFilled);

        eInputCity.setDisable(coordFieldsFilled);
    }

    private void setupInputListeners() {
        aInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateAFieldsState());
        aLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateAFieldsState());
        aLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateAFieldsState());

        bInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateBFieldsState());
        bLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateBFieldsState());
        bLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateBFieldsState());

        cInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateCFieldsState());
        cLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateCFieldsState());
        cLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateCFieldsState());

        dInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateDFieldsState());
        dLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateDFieldsState());
        dLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateDFieldsState());

        eInputCity.textProperty().addListener((observable, oldValue, newValue) -> updateEFieldsState());
        eLatitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateEFieldsState());
        eLongitudeInput.textProperty().addListener((observable, oldValue, newValue) -> updateEFieldsState());
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
        List<ImageView> iconsImageViews = List.of(icon1, icon2, icon3, icon4, icon5, icon6, icon7);
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
        icon1.setImage(null);
        icon1.setVisible(false);
        icon2.setImage(null);
        icon2.setVisible(false);
        icon3.setImage(null);
        icon3.setVisible(false);
        icon4.setImage(null);
        icon4.setVisible(false);
        icon5.setImage(null);
        icon5.setVisible(false);
        icon6.setImage(null);
        icon6.setVisible(false);
        icon7.setImage(null);
        icon7.setVisible(false);
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

    public void showError(String error) {
        setWeatherError(error);
        setWeatherDisplaying(false);
    }

    private void clearAInputs() {
        aInputCity.setText("");
        aLatitudeInput.setText("");
        aLongitudeInput.setText("");
        aForecastTimeInput.setText("");
    }

    private void clearBInputs() {
        bInputCity.setText("");
        bLatitudeInput.setText("");
        bLongitudeInput.setText("");
        bForecastTimeInput.setText("");
    }

    private void clearCInputs() {
        cInputCity.setText("");
        cLatitudeInput.setText("");
        cLongitudeInput.setText("");
        cForecastTimeInput.setText("");
    }

    private void clearDInputs() {
        dInputCity.setText("");
        dLatitudeInput.setText("");
        dLongitudeInput.setText("");
        dForecastTimeInput.setText("");
    }

    private void clearEInputs() {
        eInputCity.setText("");
        eLatitudeInput.setText("");
        eLongitudeInput.setText("");
        eForecastTimeInput.setText("");
    }

    public boolean areAInputsClear() {
        return aInputCity.getText().isEmpty() && aLatitudeInput.getText().isEmpty() && aLongitudeInput.getText().isEmpty() && aForecastTimeInput.getText().isEmpty();
    }

    public boolean areBInputsClear() {
        return bInputCity.getText().isEmpty() && bLatitudeInput.getText().isEmpty() && bLongitudeInput.getText().isEmpty() && bForecastTimeInput.getText().isEmpty();
    }

    public boolean areCInputsClear() {
        return cInputCity.getText().isEmpty() && cLatitudeInput.getText().isEmpty() && cLongitudeInput.getText().isEmpty() && cForecastTimeInput.getText().isEmpty();
    }

    public boolean areDInputsClear() {
        return dInputCity.getText().isEmpty() && dLatitudeInput.getText().isEmpty() && dLongitudeInput.getText().isEmpty() && dForecastTimeInput.getText().isEmpty();
    }

    public boolean areEInputsClear() {
        return eInputCity.getText().isEmpty() && eLatitudeInput.getText().isEmpty() && eLongitudeInput.getText().isEmpty() && eForecastTimeInput.getText().isEmpty();
    }

    @FXML
    public void handleGetWeatherAction() {
        presenter.handleGetForecastAction();
    }

    @FXML
    private void handleAddToFavourites() {
        String city = aInputCity.getText();
        String time = aForecastTimeInput.getText();
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

    @FXML
    private void handleClear() {
        clearAInputs();
        clearBInputs();
        clearCInputs();
        clearDInputs();
        clearEInputs();
    }

    public String getACityInput() {
        return aInputCity.getText();
    }

    public String getBCityInput() {
        return bInputCity.getText();
    }

    public String getCCityInput() {
        return cInputCity.getText();
    }

    public String getDCityInput() {
        return dInputCity.getText();
    }

    public String getECityInput() {
        return eInputCity.getText();
    }

    public String getALatitudeInput() {
        return aLatitudeInput.getText();
    }

    public String getALongitudeInput() {
        return aLongitudeInput.getText();
    }

    public String getBLatitudeInput() {
        return bLatitudeInput.getText();
    }

    public String getBLongitudeInput() {
        return bLongitudeInput.getText();
    }

    public String getCLatitudeInput() {
        return cLatitudeInput.getText();
    }

    public String getCLongitudeInput() {
        return cLongitudeInput.getText();
    }

    public String getDLatitudeInput() {
        return dLatitudeInput.getText();
    }

    public String getDLongitudeInput() {
        return dLongitudeInput.getText();
    }

    public String getELatitudeInput() {
        return eLatitudeInput.getText();
    }

    public String getELongitudeInput() {
        return eLongitudeInput.getText();
    }

    public String getATimeInput() {
        return aForecastTimeInput.getText();
    }

    public String getBTimeInput() {
        return bForecastTimeInput.getText();
    }

    public String getCTimeInput() {
        return cForecastTimeInput.getText();
    }

    public String getDTimeInput() {
        return dForecastTimeInput.getText();
    }

    public String getETimeInput() {
        return eForecastTimeInput.getText();
    }

    public void setPresenter(IWeatherPresenter presenter) {
        this.presenter = presenter;
    }

    public void setWeatherError(String weatherError) {
        this.weatherError.setText(weatherError);
    }

    public void setWeatherOutputInformer(String weatherOutput) {
        this.weatherOutputInformer.setText(weatherOutput);
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

    public void setACityInput(String city) {
        aInputCity.setText(city);
    }

    public void setBCityInput(String city) {
        bInputCity.setText(city);
    }

    public void setCCityInput(String city) {
        cInputCity.setText(city);
    }

    public void setDCityInput(String city) {
        dInputCity.setText(city);
    }

    public void setECityInput(String city) {
        eInputCity.setText(city);
    }

    public void setALatitudeInput(String lat) {
        aLatitudeInput.setText(lat);
    }

    public void setALongitudeInput(String lon) {
        aLongitudeInput.setText(lon);
    }

    public void setBLatitudeInput(String lat) {
        bLatitudeInput.setText(lat);
    }

    public void setBLongitudeInput(String lon) {
        bLongitudeInput.setText(lon);
    }

    public void setCLatitudeInput(String lat) {
        cLatitudeInput.setText(lat);
    }

    public void setCLongitudeInput(String lon) {
        cLongitudeInput.setText(lon);
    }

    public void setDLatitudeInput(String lat) {
        dLatitudeInput.setText(lat);
    }

    public void setDLongitudeInput(String lon) {
        dLongitudeInput.setText(lon);
    }

    public void setELatitudeInput(String lat) {
        eLatitudeInput.setText(lat);
    }

    public void setELongitudeInput(String lon) {
        eLongitudeInput.setText(lon);
    }

    public void setATimeInput(String time) {
        aForecastTimeInput.setText(time);
    }

    public void setBTimeInput(String time) {
        bForecastTimeInput.setText(time);
    }

    public void setCTimeInput(String time) {
        cForecastTimeInput.setText(time);
    }

    public void setDTimeInput(String time) {
        dForecastTimeInput.setText(time);
    }

    public void setETimeInput(String time) {
        eForecastTimeInput.setText(time);
    }
}
