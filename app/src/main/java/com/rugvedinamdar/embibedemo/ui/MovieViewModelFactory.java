package com.rugvedinamdar.embibedemo.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;

public class MovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository mRepository;

    public MovieViewModelFactory(MovieRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MovieViewModel(mRepository);
    }
}
