package com.walmart.apps.video360app.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.walmart.apps.video360app.fragement.EntertainmentFragement;
import com.walmart.apps.video360app.fragement.NewsFragement;
import com.walmart.apps.video360app.fragement.PoliticsFragement;
import com.walmart.apps.video360app.fragement.TrendingFragement;

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

    public static final int PAGE_COUNT = 4;
    public static final int FRAGMENT_COUNT = 4;
    private static final String TAB_TITLES[] = new String[]{"Trending", "Entertain", "News", "Politics"};
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
                return TrendingFragement.newInstance();
            case Entertainment:
                return EntertainmentFragement.newInstance();
            case News:
                return NewsFragement.newInstance();
            case Politics:
                return PoliticsFragement.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        // Generate title based on item position
//        // return tabTitles[position];
//        Drawable image = mContext.getResources().getDrawable(R.drawable.ic_action_politics);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(TAB_TITLES[position % FRAGMENT_COUNT]);
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }

    @Override
    public CharSequence getPageTitle(int position) {

        return TAB_TITLES[position % FRAGMENT_COUNT];
//        return null;
    }
}

