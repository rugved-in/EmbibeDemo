package com.rugvedinamdar.embibedemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rugvedinamdar.embibedemo.R;
import com.rugvedinamdar.embibedemo.data.EmbibeDatabase;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*inflate db if its empty*/
        EmbibeDatabase.getInstance(this).getMovieDao().getAllTopMoviesByRank();

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
