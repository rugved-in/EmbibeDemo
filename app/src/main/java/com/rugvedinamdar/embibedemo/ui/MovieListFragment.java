package com.rugvedinamdar.embibedemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.model.Movie;

import java.util.List;

public class MovieListFragment extends Fragment {

    private MovieListViewModel mViewModel;
    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        mViewModel.movieMutableLiveData.observe(this, movieList -> {
            if(movieList !=null) {
                bindMovieListToUI(movieList);
            }
        });
    }

    private void bindMovieListToUI(List<Movie> movieList) {

    }

}
