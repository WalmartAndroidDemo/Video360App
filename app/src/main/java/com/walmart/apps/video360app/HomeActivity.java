package com.walmart.apps.video360app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by ssahu6 on 6/23/16.
 */

public class HomeActivity extends AppCompatActivity {

    Handler handler;
    EditText mSwitcher;

    Animation in;
    Animation out;

    int fadeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fadeCount = 0;
        handler = new Handler();

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(6000);
        in.setRepeatCount(Animation.INFINITE);
        mSwitcher = (EditText) findViewById(R.id.textView);
        //mSwitcher.setText("Welcome to image1_360 Video App");
        mSwitcher.setFocusable(false);
        mSwitcher.startAnimation(in);
        in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSwitcher.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

         final RotateAnimation anim2 = new RotateAnimation(360.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim2.setInterpolator(new LinearInterpolator());
        anim2.setRepeatCount(Animation.ABSOLUTE);
        anim2.setRepeatMode(RotateAnimation.REVERSE);
        anim2.setRepeatCount(4);
        anim2.setDuration(700);

        final RotateAnimation anim1 = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim1.setInterpolator(new LinearInterpolator());
        anim1.setRepeatCount(Animation.ABSOLUTE);
        anim1.setRepeatMode(RotateAnimation.REVERSE);
        anim1.setRepeatCount(4);
        anim1.setDuration(700);
        anim1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout yourLayout = (RelativeLayout) findViewById(R.id.linearLayout);
                for (int i = 1; i < yourLayout.getChildCount(); i = i + 2) {

                    View subView = yourLayout.getChildAt(i);
                    if (subView instanceof ImageView) {
                        ImageView imageView = (ImageView) subView;
                        imageView.startAnimation(anim2);
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        RelativeLayout yourLayout = (RelativeLayout) findViewById(R.id.linearLayout);

        for (int i = 0; i < yourLayout.getChildCount(); i=i+2) {

            View subView = yourLayout.getChildAt(i);
            Animation anim = anim1;
            if (subView instanceof ImageView) {
                ImageView imageView = (ImageView) subView;
                imageView.startAnimation(anim);
            }
        }
    }

    public void guestLogin(View view) {
        Toast.makeText(this, "guestLogin", Toast.LENGTH_LONG);
        Log.d(HomeActivity.class.getSimpleName(), "onLoginSuccess: ");
        Intent i = new Intent(this, LandingActivity.class);
        startActivity(i);
    }

    private Runnable mFadeOut = new Runnable() {

        @Override
        public void run() {
            //Speed up the last fade-out so that the Activity opens faster
            if (fadeCount == 2) {
                out.setDuration(2000);
            }
            mSwitcher.startAnimation(out);
        }
    };


}
