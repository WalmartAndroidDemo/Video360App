package com.walmart.apps.video360app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.walmart.apps.video360app.adapters.BaseFragmentPagerAdapter;
import com.walmart.apps.video360app.adapters.SmartFragmentStatePagerAdapter;
import com.walmart.apps.video360app.fragement.BaseFragement;
import com.walmart.apps.video360app.fragement.HomeLandingFragement;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ssahu6 on 6/23/16.
 */

public class LandingActivity extends AppCompatActivity implements BaseFragement.OnMovieTimelineFragmentInteractionListener{
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.sliding_tabs)
    TabLayout slidingTabs;

    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    public SmartFragmentStatePagerAdapter mSmartFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.d(LandingActivity.class.getSimpleName(), "onCreate: SSSSSSSS  SSSSSSSSS ");
            setContentView(R.layout.activity_landing);
            ButterKnife.bind(this);
            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), LandingActivity.this);
            mSmartFragmentStatePagerAdapter = mBaseFragmentPagerAdapter;
            viewpager.setAdapter(mBaseFragmentPagerAdapter);
            slidingTabs.setupWithViewPager(viewpager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.d(LandingActivity.class.getSimpleName(), "onResume: !!!!!!!!!!!!!!!! 111111");
        HomeLandingFragement timelineFragement = (HomeLandingFragement) mSmartFragmentStatePagerAdapter.getRegisteredFragment(viewpager.getCurrentItem());
        if(timelineFragement != null){
            Log.d(LandingActivity.class.getSimpleName(), "onResume: !!!!!!!!!!!!!!!! 111111 2222222222222222222222 2 2  22 2 2 2 2 ");
            timelineFragement.populateVideos(1);
        }
    }

}
