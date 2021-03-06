package com.walmart.apps.video360app.fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.walmart.apps.video360app.VideoActivity;
import com.walmart.apps.video360app.models.Video;
import com.walmart.apps.video360app.models.VideoAdapter;
import com.walmart.apps.video360app.util.CommonUtils;

import org.parceler.Parcels;

/**
 * Created by dkthaku on 6/23/16.
 */
public class HomeLandingFragement extends BaseFragement{
    static final String TAG = HomeLandingFragement.class.getSimpleName();
    private OnMovieTimelineFragmentInteractionListener mListener;
    private static Fragment fragment;

    public static Fragment newInstance(String arg) {
        Log.d(TAG, "newInstance: timeline "+arg);
        Bundle args = new Bundle();
        args.putString(CommonUtils.TIMELINE_ARG, arg);
        fragment = new HomeLandingFragement();
        fragment.setArguments(args);
        return fragment;
    }
    public static HomeLandingFragement newInstance() {
        HomeLandingFragement fragment = new HomeLandingFragement();
        return fragment;
    }

    @Override
    public void populateVideos(int page) {
        Log.d(TAG, "populateVideos: 1 ");
        VideoAdapter lVideoAdapter =getAdapter();
        int tab = CommonUtils.getMovieType(getArguments());
        Log.d(TAG, "populateVideos: lVideoAdapter "+lVideoAdapter);
        if(lVideoAdapter!=null){
            // Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            swipeContainer.setRefreshing(false);
            lVideoAdapter.setVideos( CommonUtils.getDefaultMovies(tab));
            Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            lVideoAdapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(true);
        }else {
            Log.d(TAG, "populateVideos: initialized adapter :::::::::");
            VideoAdapter lvideoAdapter = new VideoAdapter( getContext(), CommonUtils.getDefaultMovies(tab));
            this.videoAdapter=lvideoAdapter;
        }


    }

    public void loadViewItems(int pos, Context ctx){
        Log.d(TAG, "loadViewItems: populateVideos call 2");
        this.populateVideos(pos);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: call populateVideos ########  3");
        super.onCreate(savedInstanceState);
        VideoAdapter lVideoAdapter =getAdapter();
        int tab = CommonUtils.getMovieType(getArguments());
        Log.d(TAG, "onCreate: lVideoAdapter "+lVideoAdapter);
        if(lVideoAdapter!=null){
            // Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            swipeContainer.setRefreshing(false);
            lVideoAdapter.setVideos( CommonUtils.getDefaultMovies(tab));
            Log.d(TAG, "onCreate: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            lVideoAdapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(true);
        }else {
            Log.d(TAG, "onCreate: initialized adapter :::::::::");
            VideoAdapter lvideoAdapter = new VideoAdapter( getContext(), CommonUtils.getDefaultMovies(tab));
            this.videoAdapter=lvideoAdapter;
        }
    }

    public void watchVideo(View v){
        try {
            Log.d(TAG, "watchVideo: ###############");
            int position = v.getVerticalScrollbarPosition();
            Video video = getAdapter().getItem(position);
            Intent intent = new Intent(getContext(), VideoActivity.class);
            intent.putExtra(CommonUtils.MOVIE_VR, Parcels.wrap(video));
            getContext().startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}