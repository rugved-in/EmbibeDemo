package com.rugvedinamdar.embibedemo.utils;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static android.support.constraint.Constraints.TAG;
import static android.support.v4.util.Preconditions.checkNotNull;

public class Utility {

    private static final String TAG = "Utility";

    public static void addFragmentToActivity(@NonNull int frame, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, String tag) {
        if (fragmentManager !=null && fragment!=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
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
        //1
        Movie movie2 = new Movie("tt0068646","The Godfather",1972,"https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,704,1000_AL_.jpg",9.2f,2);
        movies.add(movie2);
        //1
        Movie movie3 = new Movie("tt0071562","The Godfather: Part II",1974,"https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY1000_CR0,0,701,1000_AL_.jpg",9.0f,3);
        movies.add(movie3);
        //1
        Movie movie4 = new Movie("tt0468569","The Dark Knight",2008,"https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_QL50_SY1000_CR0,0,675,1000_AL_.jpg",9.0f,4);
        movies.add(movie4);
        //1
        Movie movie5 = new Movie("tt0050083","12 Angry Men",1957,"https://m.media-amazon.com/images/M/MV5BMWU4N2FjNzYtNTVkNC00NzQ0LTg0MjAtYTJlMjFhNGUxZDFmXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_QL50_SY1000_CR0,0,649,1000_AL_.jpg",8.9f,5);
        movies.add(movie5);
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
