package pl.edu.agh.to2.weather_app.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.logger.ConsoleSerializer;
import pl.edu.agh.to2.weather_app.logger.IMessageSerializer;
import pl.edu.agh.to2.weather_app.model.response_converter.GsonConverter;
import pl.edu.agh.to2.weather_app.model.response_converter.IResponseToModelConverter;

import static org.junit.jupiter.api.Assertions.*;

class WeatherModuleTest {

    @Test
    void testInjectorCreation() {
        // given
        Injector injector = Guice.createInjector(new WeatherModule());

        // when & then
        assertNotNull(injector);

        IResponseToModelConverter converter = injector.getInstance(IResponseToModelConverter.class);
        assertNotNull(converter);

        IMessageSerializer serializer = injector.getInstance(IMessageSerializer.class);
        assertNotNull(serializer);
    }

    @Test
    void testResponseToModelConverterInstance() {
        // given
        Injector injector = Guice.createInjector(new WeatherModule());

        // when
        IResponseToModelConverter converter = injector.getInstance(IResponseToModelConverter.class);

        // then
        assertNotNull(converter);
        assertTrue(converter instanceof GsonConverter);
    }

    @Test
    void testMessageSerializerInstance() {
        // given
        Injector injector = Guice.createInjector(new WeatherModule());

        // when
        IMessageSerializer serializer = injector.getInstance(IMessageSerializer.class);

        // then
        assertNotNull(serializer);
        assertFalse(serializer instanceof ConsoleSerializer);
    }
}
