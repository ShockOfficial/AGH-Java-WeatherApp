package pl.edu.agh.to2.weather_app.persistence.favourite;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import pl.edu.agh.to2.weather_app.exceptions.DataSerializationException;
import pl.edu.agh.to2.weather_app.exceptions.FavouriteListReloadException;
import pl.edu.agh.to2.weather_app.exceptions.ItemAlreadyExistsException;
import pl.edu.agh.to2.weather_app.persistence.Dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FavouritesDao implements Dao<Favourite> {

    private static final String PERSISTENCE_PATH = "src/persistence/favourites.json";
    private final Gson gson;
    private FavouritesList list;

    @Inject
    public FavouritesDao(Gson gson) throws FileNotFoundException {
        this.gson = gson;
        JsonReader reader = new JsonReader(new FileReader(PERSISTENCE_PATH));
        this.list = this.gson.fromJson(reader, FavouritesList.class);
    }

    public void reloadList() {
        try {
            JsonReader reader = new JsonReader(new FileReader(PERSISTENCE_PATH));
            this.list = this.gson.fromJson(reader, FavouritesList.class);
        } catch (FileNotFoundException e) {
            throw new FavouriteListReloadException("Could not reload favourites list");
        }
    }

    @Override
    public Optional<Favourite> get(String name) {
        for (Favourite el : this.list) {
            if (el.getName().equals(name)) {
                return Optional.of(el);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Favourite favourite) throws ItemAlreadyExistsException {
        for (Favourite el : this.list) {
            if (el.getName().equals(favourite.getName()) && el.getTime().equals(favourite.getTime())) {
                throw new ItemAlreadyExistsException("An item with the name already exists in database");
            }
        }
        this.list.add(favourite);
        this.serialize();
    }

    @Override
    public void update() {
        this.serialize();
    }

    @Override
    public void delete(Favourite item) {
        this.list.remove(item);
        this.serialize();
    }

    public FavouritesList getList() {
        reloadList();
        return this.list;
    }

    private void serialize() throws DataSerializationException {
        try (FileWriter writer = new FileWriter(PERSISTENCE_PATH)) {
            gson.toJson(this.list, writer);
        } catch (IOException e) {
            throw new DataSerializationException(e.getMessage());
        }
    }
}
