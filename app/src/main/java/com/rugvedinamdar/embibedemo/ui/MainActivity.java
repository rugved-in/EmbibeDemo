package com.rugvedinamdar.embibedemo.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.utils.Constants;
import com.rugvedinamdar.embibedemo.utils.Utility;

public class MainActivity extends AppCompatActivity implements MovieDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        FrameLayout frameLayout = findViewById(R.id.frame_main);
        frameLayout.setId(Constants.MOVIE_FRAME_ID);
        MovieListFragment movieListFragment = new MovieListFragment();
        if (!getSupportFragmentManager().getFragments().contains(movieListFragment)) {
            Utility.addFragmentToActivity(Constants.MOVIE_FRAME_ID, getSupportFragmentManager(), movieListFragment, Constants.MOVIE_FRAME_TAG);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                sortMovieListByName();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortMovieListByName() {

    }
}
