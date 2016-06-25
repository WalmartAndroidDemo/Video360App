package com.walmart.apps.video360app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.walmart.apps.video360app.adapters.BaseFragmentPagerAdapter;
import com.walmart.apps.video360app.adapters.SmartFragmentStatePagerAdapter;
import com.walmart.apps.video360app.fragement.BaseFragement;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ssahu6 on 6/23/16.
 */

public class LandingActivity extends AppCompatActivity implements BaseFragement.OnMovieTimelineFragmentInteractionListener{
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Bind(R.id.sliding_tabs)
    PagerSlidingTabStrip slidingTabs;

//    @Bind(R.id.tool_bar)
//    Toolbar toolbar;
    private BaseFragmentPagerAdapter mBaseFragmentPagerAdapter;
    public SmartFragmentStatePagerAdapter mSmartFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // setup the toolbar.
//            setSupportActionBar(toolbar);
            setContentView(R.layout.activity_landing);
            ButterKnife.bind(this);

            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), LandingActivity.this);
            mSmartFragmentStatePagerAdapter = mBaseFragmentPagerAdapter;
            viewpager.setAdapter(mBaseFragmentPagerAdapter);
            slidingTabs.setViewPager(viewpager);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    @Override
//    public void onResume(){
//        super.onResume();
//        HomeLandingFragement timelineFragement = (HomeLandingFragement) mSmartFragmentStatePagerAdapter.getRegisteredFragment(viewpager.getCurrentItem());
//        if(timelineFragement != null){
//
//            timelineFragement.populateVideos(1);
//        }
//    }

}
