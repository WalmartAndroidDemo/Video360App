package com.walmart.apps.video360app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.walmart.apps.video360app.fragement.EntertainmentFragement;
import com.walmart.apps.video360app.fragement.NewsFragement;
import com.walmart.apps.video360app.fragement.PoliticsFragement;
import com.walmart.apps.video360app.fragement.TrendingFragement;
import com.walmart.apps.video360app.util.CommonUtils;

/**
 * Created by dkthaku on 6/23/16.
 */
public class BaseFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    enum Position {
        Trending(0),
        Entertainment(1),
        News(2),
        Politics(3);

        private final int val;

        Position(int v){
            val = v;
        }

        int getVal(){
            return val;
        }

        public static Position fromValue(int x){

            if(x == 0) {
                return Trending;
            }else if( x == 1) {
                return Entertainment;
            }else if( x == 2) {
                return News;
            }  else if ( x == 3){
                return Politics;
            } else {
                return null;
            }
        }

    };
    private static final String TAB_TITLES[] = new String[]{CommonUtils.TIMELINE_TRENDING, CommonUtils.TIMELINE_FUN, CommonUtils.TIMELINE_NEWS, CommonUtils.TIMELINE_POLITICS};
    public static final int PAGE_COUNT = TAB_TITLES.length;
    public static final int FRAGMENT_COUNT = 4;
    private Context mContext;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Position p = Position.fromValue(position % FRAGMENT_COUNT);
        switch (p){
            case Trending:
                return TrendingFragement.newInstance(CommonUtils.TIMELINE_TRENDING);
            case Entertainment:
                return EntertainmentFragement.newInstance(CommonUtils.TIMELINE_FUN);
            case News:
                return NewsFragement.newInstance(CommonUtils.TIMELINE_NEWS);
            case Politics:
                return PoliticsFragement.newInstance(CommonUtils.TIMELINE_POLITICS);
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TAB_TITLES[position % FRAGMENT_COUNT];
//        return null;
    }
}

