package com.platine.firemap.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;

public class ViewModelFavoriteFactory implements ViewModelProvider.Factory {

    private final FireworkDisplayDataRepository fireworkRepository;

    public ViewModelFavoriteFactory(FireworkDisplayDataRepository fireworkRepository) {
        this.fireworkRepository = fireworkRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(fireworkRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
