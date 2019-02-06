package com.rugvedinamdar.embibedemo.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.model.MovieDao;

import java.util.List;

public class MovieRepository {

    private final static String LOG_TAG = MovieRepository.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieRepository sInstance;
    private MovieDao mMovieDao;

    public MovieRepository(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    public synchronized static MovieRepository getsInstance(MovieDao movieDao){
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MovieRepository(movieDao);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<Movie>> getAllTopMoviesByRank(){
        return mMovieDao.getAllTopMoviesByRank();
    }

    public List<Movie> getAllTopMoviesListByRank(){
        return mMovieDao.getAllTopMoviesListByRank();
    }

    public List<Movie> getAllTopMoviesListByName(){
        return mMovieDao.getAllTopMoviesListByTitle();
    }

    public void insertMovies(List<Movie> movies){
        mMovieDao.insertMovies(movies.toArray(new Movie[movies.size()]));
    }
}
