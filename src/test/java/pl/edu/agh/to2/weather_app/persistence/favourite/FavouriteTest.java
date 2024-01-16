package pl.edu.agh.to2.weather_app.persistence.favourite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavouriteTest {

    @Test
    void getName() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        assertEquals("Krakow", favourite.getCity());
    }

    @Test
    void setName() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        favourite.setName("First");
        assertEquals("First", favourite.getName());
    }

    @Test
    void getLon() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        favourite.setLon(50);
        assertEquals(50, favourite.getLon());
    }

    @Test
    void setLon() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        favourite.setLon(60);
        assertEquals(60, favourite.getLon());
    }

    @Test
    void getLat() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        favourite.setLat(50);
        assertEquals(50, favourite.getLat());
    }

    @Test
    void setLat() {
        Favourite favourite = new Favourite("First","Krakow", "12:00");
        favourite.setLat(60);
        assertEquals(60, favourite.getLat());
    }
}