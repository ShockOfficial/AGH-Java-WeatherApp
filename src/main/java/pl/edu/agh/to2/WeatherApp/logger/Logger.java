package pl.edu.agh.to2.WeatherApp.logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Singleton
public class Logger {
        protected static Logger logger;

        protected DateFormat dateFormat;

        @Inject
        protected IMessageSerializer registeredSerializer;

        public Logger() {
            init();
        }

        public Logger(IMessageSerializer registeredSerializer) {
            init();
            if (registeredSerializer == null) {
                throw new IllegalArgumentException("null argument");
            }
            this.registeredSerializer = registeredSerializer;
        }

        public static Logger getInstance() {
            if (logger == null)
                logger = new Logger();
            return logger;
        }

        public void registerSerializer(IMessageSerializer messageSerializer) {
            registeredSerializer = messageSerializer;
        }

        public void log(String message) {
            log(message, null);
        }

        public void log(String message, Throwable error) {
                String formattedMessage = dateFormat.format(new Date())
                        + ": " + message + (error != null ? error.toString() : "");
                registeredSerializer.serializeMessage(formattedMessage);

        }

        private void init() {
            dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        }

}

