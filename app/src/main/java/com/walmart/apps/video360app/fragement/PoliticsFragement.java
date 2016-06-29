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
public class PoliticsFragement extends BaseFragement{
    static final String TAG = PoliticsFragement.class.getSimpleName();

    private static Fragment fragment;

    public static Fragment newInstance() {

        if(fragment == null) {
            fragment =  new PoliticsFragement();
        }
        return fragment;

    }

    private OnMovieTimelineFragmentInteractionListener mListener;

    public static Fragment newInstance(String timeline) {

        if(fragment == null) {
            Bundle args = new Bundle();
            args.putString(CommonUtils.TIMELINE_ARG, timeline);
           // args.putString(CommonUtils.MOVIE_ID_ARG, movieId);
            fragment = new PoliticsFragement();
            fragment.setArguments(args);
        }
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
        Log.d(TAG, "loadViewItems: populateVideos call");
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
