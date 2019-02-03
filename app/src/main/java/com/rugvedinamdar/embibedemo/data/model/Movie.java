package com.rugvedinamdar.embibedemo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int releaseYear;
    private String posterImageUrl;
    private float rating;
    private int rank;



    /**
     * Constructor used by Room
     * @param id
     * @param title
     * @param releaseYear
     * @param posterImageUrl
     * @param rating
     * @param rank
     */
    public Movie(int id, String title, int releaseYear, String posterImageUrl, float rating, int rank) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.posterImageUrl = posterImageUrl;
        this.rating = rating;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public float getRating() {
        return rating;
    }

    public int getRank() {
        return rank;
    }
}
