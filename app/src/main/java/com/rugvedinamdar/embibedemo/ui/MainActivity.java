package com.rugvedinamdar.embibedemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.utils.Constants;
import com.rugvedinamdar.embibedemo.utils.Utility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.frame_main);
        frameLayout.setId(Constants.MOVIE_FRAME_ID);
        MovieListFragment movieListFragment = new MovieListFragment();
        if(!getSupportFragmentManager().getFragments().contains(movieListFragment)){
            Utility.addFragmentToActivity(Constants.MOVIE_FRAME_ID,getSupportFragmentManager(),movieListFragment,Constants.MOVIE_FRAME_TAG);
        }
    }
}
