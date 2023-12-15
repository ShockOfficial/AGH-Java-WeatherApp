package pl.edu.agh.to2.weather_app.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.edu.agh.to2.weather_app.model.WeatherModel;
import pl.edu.agh.to2.weather_app.model.WeatherModule;
import pl.edu.agh.to2.weather_app.presenter.impl.WeatherPresenterImpl;
import pl.edu.agh.to2.weather_app.view.WeatherView;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtility {

    private FXMLLoaderUtility(){}

    public static Parent loadMainView() throws IOException {
        URL fxmlResource = FXMLLoaderUtility.class.getResource("/weatherApp/weatherApp.fxml");
        if (fxmlResource == null) {
            throw new IOException("FXML file not found");
        }

        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Parent root = loader.load();


        Injector injector = Guice.createInjector(new WeatherModule());
        WeatherPresenterImpl presenter = injector.getInstance(WeatherPresenterImpl.class);

        WeatherView viewController = loader.getController();

        presenter.setView(viewController);
        viewController.setPresenter(presenter);

        return root;
    }
}