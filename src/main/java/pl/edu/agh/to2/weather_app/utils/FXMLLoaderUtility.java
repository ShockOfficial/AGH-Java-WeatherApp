package pl.edu.agh.to2.weather_app.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.edu.agh.to2.weather_app.model.WeatherModel;
import pl.edu.agh.to2.weather_app.model.weather_data.WeatherDataMerger;
import pl.edu.agh.to2.weather_app.presenter.impl.WeatherPresenterImpl;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtility {

    private FXMLLoaderUtility(){}

    public static Parent loadMainView(WeatherModel weatherModel) throws IOException {
        URL fxmlResource = FXMLLoaderUtility.class.getResource("/weatherApp/weatherApp.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent root = loader.load();

        WeatherView viewController = loader.getController();
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(weatherModel, viewController, new WeatherDataMerger());
        viewController.setPresenter(presenter);

        return root;
    }
}