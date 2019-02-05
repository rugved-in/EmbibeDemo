package com.rugvedinamdar.embibedemo.ui;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;
import com.rugvedinamdar.embibedemo.data.model.Movie;
import com.rugvedinamdar.embibedemo.data.model.MovieDao_Impl;
import com.rugvedinamdar.embibedemo.data.repository.MovieRepository;
import com.rugvedinamdar.embibedemo.utils.Utility;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SplashActivity extends Activity {

    private static final String LOG_TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, 2000);

    }
}
