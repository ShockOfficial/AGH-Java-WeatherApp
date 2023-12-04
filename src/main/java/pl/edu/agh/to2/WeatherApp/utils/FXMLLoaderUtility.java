package pl.edu.agh.to2.WeatherApp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.edu.agh.to2.WeatherApp.model.Impl.WeatherModelImpl;
import pl.edu.agh.to2.WeatherApp.model.WeatherModel;
import pl.edu.agh.to2.WeatherApp.presenter.impl.WeatherPresenterImpl;
import pl.edu.agh.to2.WeatherApp.view.WeatherView;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtility {

    private FXMLLoaderUtility(){}

    public static Parent loadMainView(WeatherModel weatherModel) throws IOException {
        URL fxmlResource = FXMLLoaderUtility.class.getResource("/pl.edu.agh.to2.WeatherApp/weatherApp.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent root = loader.load();

        WeatherView viewController = loader.getController();
        WeatherPresenterImpl presenter = new WeatherPresenterImpl(weatherModel, viewController);
        viewController.setPresenter(presenter);

        return root;
    }
}