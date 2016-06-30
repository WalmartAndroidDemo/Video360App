package com.walmart.apps.video360app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.walmart.apps.video360app.adapters.BaseFragmentPagerAdapter;
import com.walmart.apps.video360app.adapters.SmartFragmentStatePagerAdapter;
import com.walmart.apps.video360app.fragement.BaseFragement;
import com.walmart.apps.video360app.models.ZoomOutPageTransformer;

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

            final RelativeLayout layout = (RelativeLayout)findViewById(R.id.landing_page);
            setContentView(R.layout.activity_landing);
            ButterKnife.bind(this);
            mBaseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), LandingActivity.this);
            viewpager.setAdapter(mBaseFragmentPagerAdapter);
            slidingTabs.setupWithViewPager(viewpager);


            // setup tab icons
            setupTabIcons();

            viewpager.setPageTransformer(true, new ZoomOutPageTransformer());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setupTabIcons(){

        for(int i=0;i< BaseFragmentPagerAdapter.PAGE_COUNT; i++) {

            int type = i % BaseFragmentPagerAdapter.FRAGMENT_COUNT;
//            RelativeLayout tab = null;
//            tab = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.customtab, null);
//            ImageView imageView = (ImageView)tab.findViewById(R.id.icon);
//            TextView textView = (TextView)tab.findViewById(R.id.textView);
            switch (type) {
                case 0:

//                    imageView.setImageResource(R.drawable.ic_action_trending_icon);
//                    textView.setText("hello 1");
                    slidingTabs.getTabAt(i).setIcon(R.drawable.trending_icon);
                    break;
                case 1:
//                    imageView.setImageResource(R.drawable.entertainment_icon);
//                    textView.setText("hello 1");
                    slidingTabs.getTabAt(i).setIcon(R.drawable.entertainment_icon);
                    break;
                case 2:
//                    imageView.setImageResource(R.drawable.news_icon);
//                    textView.setText("hello 1");
                    slidingTabs.getTabAt(i).setIcon(R.drawable.news_icon);
                    break;
                case 3:
//                    imageView.setImageResource(R.drawable.politics_icon);
//                    textView.setText("hello 1");
                    slidingTabs.getTabAt(i).setIcon(R.drawable.politics_icon);
                    break;
            }
//            slidingTabs.getTabAt(i).setCustomView(tab);
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
