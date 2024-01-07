package pl.edu.agh.to2.weather_app.view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pl.edu.agh.to2.weather_app.persistence.favourite.Favourite;
import pl.edu.agh.to2.weather_app.presenter.IFavouritesPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouritesView {
    @FXML
    private ListView<String> favouritesList;
    private IFavouritesPresenter presenter;


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
}