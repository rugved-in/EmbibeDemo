package com.rugvedinamdar.embibedemo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    LiveData<List<Movie>> movieMutableLiveData;
    MovieRepository mMovieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
        this.movieMutableLiveData = mMovieRepository.getAllTopMoviesByRank();
    }

    public LiveData<List<Movie>> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }

    public void setMovieMutableLiveData(MutableLiveData<List<Movie>> movieMutableLiveData) {
        this.movieMutableLiveData = movieMutableLiveData;
    }
}
