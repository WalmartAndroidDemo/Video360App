package com.walmart.apps.video360app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ssahu6 on 6/23/16.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void guestLogin(View view){
        Toast.makeText(this, "guestLogin", Toast.LENGTH_LONG);
        Log.d(HomeActivity.class.getSimpleName(), "onLoginSuccess: ");
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }
}
