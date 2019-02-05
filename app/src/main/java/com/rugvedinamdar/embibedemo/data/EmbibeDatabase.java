package com.rugvedinamdar.embibedemo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.model.MovieDao;

/**
 * {@link EmbibeDatabase} database for the application including a table for {@link com.rugvedinamdar.embibedemo.data.model.Movie}
 *  * with the DAO {@link com.rugvedinamdar.embibedemo.data.model.MovieDao}.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class EmbibeDatabase extends RoomDatabase {
    private static final String LOG_TAG = EmbibeDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "embibe_database";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static EmbibeDatabase sInstance;

    public static EmbibeDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        EmbibeDatabase.class, EmbibeDatabase.DATABASE_NAME).allowMainThreadQueries().build();
                Log.d(LOG_TAG, "Made new database");
            }
        }
        return sInstance;
    }

    public abstract MovieDao getMovieDao();
}
