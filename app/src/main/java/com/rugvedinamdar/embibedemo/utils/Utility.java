package com.rugvedinamdar.embibedemo.utils;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static final String TAG = "Utility";

    public static void addFragmentToActivity(@NonNull int frame, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, String tag) {
        if (fragmentManager !=null && fragment!=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(tag);
            transaction.add(frame, fragment, tag);
            transaction.commit();
        }
    }

    public static void setImage(Context context, final ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }

    public static List<Movie> getStaticMovieList(){
        List<Movie> movies = new ArrayList<>();
        //1
        Movie movie1 = new Movie("tt0111161","The Shawshank Redemption",1994,"https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_QL50_.jpg",9.3f,1);
        movies.add(movie1);
        //2
        Movie movie2 = new Movie("tt0068646","The Godfather",1972,"https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,704,1000_AL_.jpg",9.2f,2);
        movies.add(movie2);
        //3
        Movie movie3 = new Movie("tt0071562","The Godfather: Part II",1974,"https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,701,1000_AL_.jpg",9.0f,3);
        movies.add(movie3);
        //4
        Movie movie4 = new Movie("tt0468569","The Dark Knight",2008,"https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_QL50_SY1000_CR0,0,675,1000_AL_.jpg",9.0f,4);
        movies.add(movie4);
        //5
        Movie movie5 = new Movie("tt0050083","12 Angry Men",1957,"https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_QL50_SY1000_CR0,0,649,1000_AL_.jpg",8.9f,5);
        movies.add(movie5);
        //6
        Movie movie6 = new Movie("tt0108052","Schindler's List",1993,"https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_QL50_SY1000_CR0,0,666,1000_AL_.jpg",8.9f,6);
        movies.add(movie6);
        //7
        Movie movie7 = new Movie("tt0167260","The Lord of the Rings: The Return of the King",2003,"https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,675,1000_AL_.jpg",8.9f,7);
        movies.add(movie7);
        //8
        Movie movie8 = new Movie("tt0110912","Pulp Fiction",1994,"https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,686,1000_AL_.jpg",8.9f,8);
        movies.add(movie8);
        //9
        Movie movie9 = new Movie("tt0060196","The Good, the Bad and the Ugly",1966,"https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_QL50_SY1000_CR0,0,656,1000_AL_.jpg",8.8f,9);
        movies.add(movie9);
        //10
        Movie movie10 = new Movie("tt0137523","Fight Club",1999,"https://m.media-amazon.com/images/M/MV5BMjJmYTNkNmItYjYyZC00MGUxLWJhNWMtZDY4Nzc1MDAwMzU5XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,676,1000_AL_.jpg",8.8f,10);
        movies.add(movie10);
        return movies;
    }

    public static void inflateDb(Context context){
        //inflate db if empty
        MovieRepository repository = MovieRepository.getsInstance(EmbibeDatabase.getInstance(context).getMovieDao());
        LiveData<List<Movie>> movies = repository.getAllTopMoviesByRank();
        if(movies.getValue() == null){
            repository.insertMovies(Utility.getStaticMovieList());
        }
    }

    public static boolean isDbEmpty(Context context){
        MovieRepository repository = MovieRepository.getsInstance(EmbibeDatabase.getInstance(context).getMovieDao());
        List<Movie> movies = repository.getAllTopMoviesListByRank();
        if(movies !=null && movies.size()>0){
            return false;
        }
        return true;
    }
}
