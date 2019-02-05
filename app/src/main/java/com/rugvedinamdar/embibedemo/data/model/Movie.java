package com.rugvedinamdar.embibedemo.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "movies")
public class Movie implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String  imdbId;
    private String title;
    private int releaseYear;
    private String posterImageUrl;
    private float rating;
    private int rank;

    @Ignore
    public Movie(String imdbId, String title, int releaseYear, String posterImageUrl, float rating, int rank) {
        this.imdbId = imdbId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.posterImageUrl = posterImageUrl;
        this.rating = rating;
        this.rank = rank;
    }

    /**
     * Used by Room
     * @param id
     * @param imdbId
     * @param title
     * @param releaseYear
     * @param posterImageUrl
     * @param rating
     * @param rank
     */
    public Movie(int id, String imdbId, String title, int releaseYear, String posterImageUrl, float rating, int rank) {
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.posterImageUrl = posterImageUrl;
        this.rating = rating;
        this.rank = rank;
    }

    public int getId() {
        return id;
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

    public String  getImdbId() {
        return imdbId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imdbId);
        dest.writeString(this.title);
        dest.writeInt(this.releaseYear);
        dest.writeString(this.posterImageUrl);
        dest.writeFloat(this.rating);
        dest.writeInt(this.rank);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.imdbId = in.readString();
        this.title = in.readString();
        this.releaseYear = in.readInt();
        this.posterImageUrl = in.readString();
        this.rating = in.readFloat();
        this.rank = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
