package pl.edu.agh.to2.weather_app.presenter.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.edu.agh.to2.weather_app.persistence.favourite.*;
import pl.edu.agh.to2.weather_app.view.FavouritesView;

import static org.mockito.Mockito.*;

class FavouritesPresenterImplTest {

    @Mock
    private FavouritesDao mockDao;

    @Mock
    private FavouritesView mockView;

    @InjectMocks
    private FavouritesPresenterImpl favouritesPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        favouritesPresenter = new FavouritesPresenterImpl(mockDao);
    }

    @Test
    void setView_UpdateView_CallsUpdateFavouritesList() {
        FavouritesList fakeList = new FavouritesList();
        when(mockDao.getList()).thenReturn(fakeList);

        favouritesPresenter.setView(mockView);

        verify(mockView, times(1)).updateFavouritesList(anyList());
    }

}
