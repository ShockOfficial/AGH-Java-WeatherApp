package pl.edu.agh.to2.WeatherApp.view;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.edu.agh.to2.WeatherApp.model.weatherData.WeatherData;
import pl.edu.agh.to2.WeatherApp.presenter.WeatherPresenter;

public class WeatherView {
    @FXML
    private TextField cityInput;
    @FXML
    private TextField latitudeInput;
    @FXML
    private TextField longitudeInput;
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

    private WeatherPresenter presenter;

    @Inject
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
            setWeatherDisplaying(false);
            weatherError.setText("Please provide city name or coordinates");
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
        } else {
            setWeatherError("City not found");
            setWeatherDisplaying(false);
        }
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
