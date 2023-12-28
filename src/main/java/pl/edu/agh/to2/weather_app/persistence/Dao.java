package pl.edu.agh.to2.weather_app.persistence;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(String name);

    void save(T t);
    void update();
    void delete(T t);
}
