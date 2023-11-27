package pl.edu.agh.to2.WeatherApp.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.WeatherApp.logger.ConsoleSerializer;
import pl.edu.agh.to2.WeatherApp.logger.IMessageSerializer;
import pl.edu.agh.to2.WeatherApp.model.Converter.GsonConverter;
import pl.edu.agh.to2.WeatherApp.model.Converter.IResponseToModelConverter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherModuleTest {

    @Test
    public void testInjectorCreation() {
        Injector injector = Guice.createInjector(new WeatherModule());

        assertNotNull(injector);

        IResponseToModelConverter converter = injector.getInstance(IResponseToModelConverter.class);
        assertNotNull(converter);

        IMessageSerializer serializer = injector.getInstance(IMessageSerializer.class);
        assertNotNull(serializer);
    }
    @Test
    public void testResponseToModelConverterInstance() {
        Injector injector = Guice.createInjector(new WeatherModule());

        IResponseToModelConverter converter = injector.getInstance(IResponseToModelConverter.class);
        assertNotNull(converter);

        assertTrue(converter instanceof GsonConverter);
    }
    @Test
    public void testMessageSerializerInstance() {
        Injector injector = Guice.createInjector(new WeatherModule());

        IMessageSerializer serializer = injector.getInstance(IMessageSerializer.class);
        assertNotNull(serializer);

        assertTrue(serializer instanceof ConsoleSerializer);
    }
}
