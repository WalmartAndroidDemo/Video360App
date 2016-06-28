package com.walmart.apps.video360app.fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public static HomeLandingFragement newInstance(String timeline, String movieId) {
        Log.d(TAG, "newInstance: timeline "+timeline);
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
        Log.d(VideoAdapter.class.getSimpleName(), "populateVideos: 1 ");
        VideoAdapter lVideoAdapter =getAdapter();
        Log.d(TAG, "populateVideos: lVideoAdapter "+lVideoAdapter);
        if(lVideoAdapter!=null){
           // Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            swipeContainer.setRefreshing(false);
            lVideoAdapter.setVideos( CommonUtils.getDefaultMovies());
            Log.d(TAG, "populateVideos: lVideoAdapter getVideos "+lVideoAdapter.getVideos());
            lVideoAdapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(true);
        }else {
            Log.d(TAG, "populateVideos: initialized adapter :::::::::");
            VideoAdapter lvideoAdapter = new VideoAdapter( getContext(), CommonUtils.getDefaultMovies());
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

        // populateVideos(1);
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

