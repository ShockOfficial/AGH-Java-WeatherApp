package pl.edu.agh.to2.weather_app.persistence.favourite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavouritesListTest {

    @Test
    void testGetList() {
        FavouritesList favouritesList = new FavouritesList();
        assertEquals(0, favouritesList.getList().size());
    }

    @Test
    void testSetList() {
        FavouritesList favouritesList = new FavouritesList();
        favouritesList.setList(null);
        assertNull(favouritesList.getList());
    }

    @Test
    void testIterator() {
        FavouritesList favouritesList = new FavouritesList();
        assertNotNull(favouritesList.iterator());
    }


    @Test
    void testSpliterator() {
        FavouritesList favouritesList = new FavouritesList();
        assertNotNull(favouritesList.spliterator());
    }

    @Test
    void testAdd() {
        FavouritesList favouritesList = new FavouritesList();
        favouritesList.add(null);
        assertEquals(1, favouritesList.getList().size());
    }

    @Test
    void testRemove() {
        FavouritesList favouritesList = new FavouritesList();
        favouritesList.remove(null);
        assertEquals(0, favouritesList.getList().size());
    }

}