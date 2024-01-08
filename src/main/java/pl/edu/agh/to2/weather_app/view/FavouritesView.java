package pl.edu.agh.to2.weather_app.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.edu.agh.to2.weather_app.presenter.IFavouritesPresenter;

import java.util.List;
import java.util.Optional;

public class FavouritesView {
    @FXML
    private TextField cityInput;

    @FXML
    private TextField latitudeInput;

    @FXML
    private TextField longitudeInput;

    @FXML
    private TextField forecastTimeInput;

    @FXML
    private Label favouriteErrorLabel;

    @FXML
    private ListView<String> favouritesList;
    @FXML
    private Button removeFromFavouritesButton;
    private IFavouritesPresenter presenter;

    @FXML
    private void initialize() {
        removeFromFavouritesButton.setDisable(true);
        favouritesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> removeFromFavouritesButton.setDisable(newValue == null));
    }


    public void setPresenter(IFavouritesPresenter presenter) {
        this.presenter = presenter;
        setupFavouritesListClickHandler();
    }

    public void updateFavouritesList(List<String> favourites) {
        favouritesList.getItems().setAll(favourites);
    }

    private void setupFavouritesListClickHandler() {
        favouritesList.setOnMouseClicked(event -> {
            String selectedFavouriteText = favouritesList.getSelectionModel().getSelectedItem();
            if (selectedFavouriteText != null && presenter != null) {
                presenter.favouriteSelectedByText(selectedFavouriteText);
            }
        });
    }

    public void setError(String weatherError) {
        this.favouriteErrorLabel.setText(weatherError);
    }

    public void showError(String error) {
        setError(error);
        this.favouriteErrorLabel.setVisible(true);
    }

    public void hideError() {
        this.favouriteErrorLabel.setVisible(false);
    }

    @FXML
    private void handleAddToFavourites() {
        String city = cityInput.getText();
        String lon = longitudeInput.getText();
        String lat = latitudeInput.getText();
        String time = forecastTimeInput.getText();


        if ((city.isEmpty() && (lat.isEmpty() || lon.isEmpty())) || time.isEmpty()) {
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
                presenter.addToFavourites(customName, city, lon, lat, time);
                hideError();
            } else {
                showError("Please provide custom name");
            }
        });
    }

    public void setCityInput(String cityInput) {
        this.cityInput.setText(cityInput);
    }

    public void setLatitudeInput(String latitudeInput) {
        this.latitudeInput.setText(latitudeInput);
    }

    public void setLongitudeInput(String longitudeInput) {
        this.longitudeInput.setText(longitudeInput);
    }

    public void setForecastTimeInput(String forecastTimeInput) {
        this.forecastTimeInput.setText(forecastTimeInput);
    }

    @FXML
    private void handleRemoveFromFavourites() {
        String selectedFavouriteText = favouritesList.getSelectionModel().getSelectedItem();
        if (selectedFavouriteText != null && presenter != null) {
            presenter.removeFromFavourites(selectedFavouriteText);
        }
    }

}