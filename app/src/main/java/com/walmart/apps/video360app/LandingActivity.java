package com.walmart.apps.video360app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

            // setup tab icons.
            setupTabIcons();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setupTabIcons(){

        for(int i=0;i< BaseFragmentPagerAdapter.PAGE_COUNT; i++) {

            int type = i % BaseFragmentPagerAdapter.FRAGMENT_COUNT;

            switch (type) {
                case 0:
                    slidingTabs.getTabAt(i).setIcon(R.drawable.trending_tab);
                    break;
                case 1:
                    slidingTabs.getTabAt(i).setIcon(R.drawable.entertainment_tab);
                    break;
                case 2:
                    slidingTabs.getTabAt(i).setIcon(R.drawable.news_tab);
                    break;
                case 3:
                    slidingTabs.getTabAt(i).setIcon(R.drawable.politics_tab);
                    break;
            }
        }
    }


//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.d(LandingActivity.class.getSimpleName(), "onResume: !!!!!!!!!!!!!!!! 111111");
//        Log.d(LandingActivity.class.getSimpleName(), "onResume: viewpager.getCurrentItem() "+viewpager.getCurrentItem());
//        HomeLandingFragement timelineFragement = (HomeLandingFragement) mSmartFragmentStatePagerAdapter.getRegisteredFragment(viewpager.getCurrentItem());
//        if(timelineFragement != null){
//            Log.d(LandingActivity.class.getSimpleName(), "onResume: !!!!!!!!!!!!!!!! 111111 2222222222222222222222 2 2  22 2 2 2 2 "+timelineFragement);
//            timelineFragement.populateVideos(viewpager.getCurrentItem());
//        }
//    }
}