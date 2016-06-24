package com.walmart.apps.video360app.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.walmart.apps.video360app.models.VideoAdapter;
import com.walmart.apps.video360app.util.CommonUtils;

/**
 * Created by dkthaku on 6/23/16.
 */
public class HomeLandingFragement extends BaseFragement{
    static final String TAG = HomeLandingFragement.class.getSimpleName();

    private OnMovieTimelineFragmentInteractionListener mListener;

    public static HomeLandingFragement newInstance(String timeline, String movieId) {
        Bundle args = new Bundle();
        args.putString(CommonUtils.TIMELINE_ARG, timeline);
        args.putString(CommonUtils.MOVIE_ID_ARG, movieId);
        HomeLandingFragement fragment = new HomeLandingFragement();
        fragment.setArguments(args);
        return fragment;
    }
    public static HomeLandingFragement newInstance() {
        HomeLandingFragement fragment = new HomeLandingFragement();
        return fragment;
    }

    @Override
    public void populateVideos(int page) {
        Log.d(VideoAdapter.class.getSimpleName(), "populateVideos: ");
        VideoAdapter lVideoAdapter =getAdapter();
        Log.d(TAG, "populateVideos: lVideoAdapter "+lVideoAdapter);
        if(lVideoAdapter!=null){
            Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            swipeContainer.setRefreshing(false);
            lVideoAdapter.setVideos( CommonUtils.getDefaultMovies());
            Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            lVideoAdapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(true);
        }else {
            VideoAdapter lvideoAdapter = new VideoAdapter(CommonUtils.getDefaultMovies());
            this.videoAdapter=lvideoAdapter;
        }


    }

    public void loadViewItems(int pos, Context ctx){
        Log.d(TAG, "loadViewItems: populateVideos call");
         this.populateVideos(pos);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: call populateVideos");
        super.onCreate(savedInstanceState);

        populateVideos(1);
    }

}

