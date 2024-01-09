package pl.edu.agh.to2.weather_app.persistence.favourite;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.agh.to2.weather_app.exceptions.ItemAlreadyExistsException;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FavouritesDaoTest {

    private FavouritesDao favouritesDao;

    @BeforeEach
    void setUp() {
        Gson gson = new Gson();
        try {
            favouritesDao = new FavouritesDao(gson);
            Favourite newFavourite = new Favourite("Test_Name1", "Test_City1", "Test_NewTime1");
            favouritesDao.save(newFavourite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        Favourite newFavourite = new Favourite("Test_Name1", "Test_City1", "Test_NewTime1");
        Optional<Favourite> existingFavourite = favouritesDao.get("Test_Name1");

        if (existingFavourite.isPresent()) {
            favouritesDao.delete(existingFavourite.get());
        }
    }


    @Test
    void saveNewFavourite() {
        Favourite newFavourite = new Favourite("Test_Name1new", "Test_City1new", "Test_NewTime1new");

        try {
            favouritesDao.save(newFavourite);
            Optional<Favourite> savedFavourite = favouritesDao.get("Test_Name1new");
            assertTrue(savedFavourite.isPresent());
            assertEquals(newFavourite, savedFavourite.get());
        } catch (ItemAlreadyExistsException e) {
            fail("Unexpected ItemAlreadyExistsException");
        }
        favouritesDao.delete(newFavourite);
    }
    @Test
    void getExistingFavourite() {
        Optional<Favourite> favourite = favouritesDao.get("Test_Name1");
        assertTrue(favourite.isPresent());
        assertEquals("Test_Name1", favourite.get().getName());
    }

    @Test
    void getNonExistingFavourite() {
        Optional<Favourite> favourite = favouritesDao.get("NonExistingName");
        assertTrue(favourite.isEmpty());
    }


    @Test
    void saveExistingFavourite() {
        Favourite newFavourite2 = new Favourite("Test_Name1", "Test_City1", "Test_NewTime1");

        assertThrows(ItemAlreadyExistsException.class, () -> favouritesDao.save(newFavourite2));

    }

    @Test
    void deleteFavourite() {
        Favourite favouriteToDelete = new Favourite("Name2", "City2", "Time2");
        favouritesDao.save(favouriteToDelete);
        favouritesDao.delete(favouriteToDelete);
        Optional<Favourite> deletedFavourite = favouritesDao.get("Name2");
        assertTrue(deletedFavourite.isEmpty());
    }

    @Test
    void updateFavourite() {
        Favourite favouriteToUpdate = new Favourite("Name3", "City3", "Time3");
        favouritesDao.save(favouriteToUpdate);
        favouriteToUpdate.setCity("NewCity3");
        favouritesDao.update();
        Optional<Favourite> updatedFavourite = favouritesDao.get("Name3");
        assertTrue(updatedFavourite.isPresent());
        assertEquals("NewCity3", updatedFavourite.get().getCity());
        favouritesDao.delete(favouriteToUpdate);
    }

}
