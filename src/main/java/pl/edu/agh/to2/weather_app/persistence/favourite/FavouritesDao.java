package pl.edu.agh.to2.weather_app.persistence.favourite;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pl.edu.agh.to2.weather_app.persistence.Dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FavouritesDao implements Dao<Favourite> {

    private static final String persistencePath = "src/persistence/favourites.json";
    private Gson gson;
    private FavouritesList list;

    public FavouritesDao(Gson gson) throws FileNotFoundException{
        this.gson = gson;
        JsonReader reader = new JsonReader(new FileReader(persistencePath));
        this.list = this.gson.fromJson(reader, FavouritesList.class);
    }

    @Override
    public Optional<Favourite> get(String name){
        for(Favourite el: this.list){
            if(el.getName().equals(name)){
                return Optional.of(el);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Favourite favourite) throws RuntimeException{
        for(Favourite el: this.list){
            if(el.getName().equals(favourite.getName())){
                throw new RuntimeException();
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
        return this.list;
    }

    private void serialize() throws RuntimeException{
        try(FileWriter writer = new FileWriter(persistencePath)){
            gson.toJson(this.list, writer);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}
