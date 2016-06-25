package com.walmart.apps.video360app.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walmart.apps.video360app.models.VideoAdapter;
import com.walmart.apps.video360app.util.CommonUtils;


/**
 * Created by ssahu6 on 6/22/16.
 */
public class NewsFragement extends BaseFragement {

    static final String TAG = HomeLandingFragement.class.getSimpleName();

    private static Fragment fragment;

    public static Fragment newInstance() {

        if(fragment == null) {
            fragment =  new NewsFragement();
        }
        return fragment;

    }

    private OnMovieTimelineFragmentInteractionListener mListener;

    public static Fragment newInstance(String timeline, String movieId) {

        if(fragment == null) {
            Bundle args = new Bundle();
            args.putString(CommonUtils.TIMELINE_ARG, timeline);
            args.putString(CommonUtils.MOVIE_ID_ARG, movieId);
            fragment = new NewsFragement();
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void populateVideos(int page) {
        Log.d(VideoAdapter.class.getSimpleName(), "populateVideos: ");
        VideoAdapter lVideoAdapter =getAdapter();
        if(lVideoAdapter!=null){
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
        Log.d(TAG, "loadViewItems: onViewCreate ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "loadViewItems: onViewCreated ");
        populateVideos(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "loadViewItems: onCreateView ");
        return super.onCreateView(inflater, parent, savedInstanceState);

    }
}
