package pl.edu.agh.to2.weather_app.persistence.favourite;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FavouritesList implements Iterable<Favourite>{
    private List<Favourite> list = new ArrayList<>();

    public List<Favourite> getList() {
        return list;
    }

    public void setList(List<Favourite> list) {
        this.list = list;
    }

    @NotNull
    @Override
    public Iterator<Favourite> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super Favourite> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Favourite> spliterator() {
        return Iterable.super.spliterator();
    }

    public void add(Favourite item){
        this.list.add(item);
    }

    public void remove(Favourite item){
        this.list.remove(item);
    }
}
