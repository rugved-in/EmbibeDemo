package com.rugvedinamdar.embibedemo.data.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * {@link android.arch.persistence.room.Dao}which provides an api for all data operations
 */

@Dao
public interface MovieDao {

    /**
     * gets all movies in the ascending order of their rank
     * @return
     */

    @Query("select * from movies order by rank asc")
    List<Movie> getAllTopMoviesByRank();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(Movie... movies);
}
