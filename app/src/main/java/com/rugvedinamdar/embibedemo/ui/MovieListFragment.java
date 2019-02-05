package com.rugvedinamdar.embibedemo.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;
import com.rugvedinamdar.embibedemo.utils.Constants;
import com.rugvedinamdar.embibedemo.utils.Utility;

import java.util.ArrayList;
import java.util.List;


public class MovieListFragment extends Fragment implements MovieListRecyclerViewAdapter.MovieListItemClickListener {
    private final String LOG_TAG = getClass().getSimpleName();

    private MovieViewModel mViewModel;
    private RecyclerView mMoviesList;
    MovieListRecyclerViewAdapter adapter;
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
        if(mMoviesList !=null){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mMoviesList.setLayoutManager(layoutManager);
        }

        MovieViewModelFactory mViewModelFactory = new MovieViewModelFactory(MovieRepository.getsInstance(EmbibeDatabase.getInstance(getContext().getApplicationContext()).getMovieDao()));
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieViewModel.class);
        mViewModel.movieMutableLiveData.observe(this, movieList -> {
            if(movieList !=null) {
                bindMovieListToUI(movieList);
            }
        });
        if(Utility.isDbEmpty(getContext().getApplicationContext())) {
            Utility.inflateDb(getContext().getApplicationContext());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mMoviesList = (RecyclerView) getView().findViewById(R.id.rv_movie_list);
    }

    @Override
    public void onResume() {
        ((MainActivity)getActivity()).setTitle("Embibe Demo");
        super.onResume();
    }

    private void bindMovieListToUI(List<Movie> movieList) {
        Log.d(LOG_TAG, "bindMovieListToUI");
        if(adapter == null) {
            adapter = new MovieListRecyclerViewAdapter(movieList, getContext(), this);
        }
            mMoviesList.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(View view, int position, Movie movie) {
        Log.d(LOG_TAG,"onItemClicked");
        Utility.addFragmentToActivity(Constants.MOVIE_FRAME_ID, getFragmentManager(), MovieDetailFragment.newInstance(position,(ArrayList<Movie>)adapter.getData()),Constants.MOVIE_DETAIL_FRAME_TAG);
    }
}
