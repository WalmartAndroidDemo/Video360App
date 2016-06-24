package com.walmart.apps.video360app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.walmart.apps.video360app.fragement.HomeLandingFragement;

/**
 * Created by dkthaku on 6/23/16.
 */
public class BaseFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private static final int PAGE_COUNT = 3;
    private static final String TAB_TITLES[] = new String[]{"Trending", "Fun", "Media"};
    private Context mContext;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {
        String timeline = TAB_TITLES[position];
        return HomeLandingFragement.newInstance(timeline, "");
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}

