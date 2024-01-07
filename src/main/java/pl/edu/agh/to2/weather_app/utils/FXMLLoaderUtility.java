package pl.edu.agh.to2.weather_app.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.edu.agh.to2.weather_app.model.WeatherModule;
import pl.edu.agh.to2.weather_app.presenter.impl.FavouritesPresenterImpl;
import pl.edu.agh.to2.weather_app.presenter.impl.WeatherPresenterImpl;
import pl.edu.agh.to2.weather_app.view.FavouritesView;
import pl.edu.agh.to2.weather_app.view.WeatherView;
import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtility {
    private static WeatherPresenterImpl weatherPresenter;
    private static FavouritesPresenterImpl favouritesPresenter;

    static {
        Injector injector = Guice.createInjector(new WeatherModule());
        weatherPresenter = injector.getInstance(WeatherPresenterImpl.class);
        favouritesPresenter = injector.getInstance(FavouritesPresenterImpl.class);
        favouritesPresenter.setWeatherPresenter(weatherPresenter);
    }
    private FXMLLoaderUtility(){}

    public static Parent loadMainView() throws IOException {
        URL fxmlResource = FXMLLoaderUtility.class.getResource("/weatherApp/weatherApp.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent root = loader.load();

        WeatherView viewController = loader.getController();
        weatherPresenter.setView(viewController);
        weatherPresenter.setFavouritesPresenter(favouritesPresenter);
        viewController.setPresenter(weatherPresenter);

        return root;
    }

    public static Parent loadFavouritesView() throws IOException {
        URL fxmlResource = FXMLLoaderUtility.class.getResource("/weatherApp/favouritesView.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file for favourites not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        loader.load();

        FavouritesView viewController = loader.getController();
        favouritesPresenter.setWeatherPresenter(weatherPresenter);
        favouritesPresenter.setView(viewController);
        viewController.setPresenter(favouritesPresenter);

        return loader.getRoot();
    }
}