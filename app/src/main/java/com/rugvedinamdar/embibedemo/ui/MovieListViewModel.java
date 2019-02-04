package com.rugvedinamdar.embibedemo.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rugvedinamdar.embibedemo.data.model.Movie;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    MutableLiveData<List<Movie>> movieMutableLiveData;

    public MovieListViewModel() {
        this.movieMutableLiveData = movieMutableLiveData;
    }

    public MutableLiveData<List<Movie>> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }

    public void setMovieMutableLiveData(MutableLiveData<List<Movie>> movieMutableLiveData) {
        this.movieMutableLiveData = movieMutableLiveData;
    }
}
