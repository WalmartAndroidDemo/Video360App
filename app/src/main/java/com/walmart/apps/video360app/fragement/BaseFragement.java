package com.walmart.apps.video360app.fragement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walmart.apps.video360app.EndlessScrollListener;
import com.walmart.apps.video360app.R;
import com.walmart.apps.video360app.models.Video;
import com.walmart.apps.video360app.models.VideoAdapter;
import com.walmart.apps.video360app.util.CommonUtils;
import com.walmart.apps.video360app.view.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ssahu6 on 6/22/16.
 */
public abstract class BaseFragement extends Fragment {

    private String mTimeline;
    private String movieId;

    private static int pageScollCount = 0;
    @Bind(R.id.lvVideos)
    public RecyclerView lvVideos;

    public VideoAdapter videoAdapter;
    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    private OnMovieTimelineFragmentInteractionListener mListener;
    private Activity mActivity;
    public abstract void populateVideos(int page);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        if (context instanceof OnMovieTimelineFragmentInteractionListener) {
            mListener = (OnMovieTimelineFragmentInteractionListener) context;
        } else {
          //  throw new RuntimeException(context.toString()+ " must implement OnTimelineFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mActivity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            if (getArguments().containsKey(CommonUtils.TIMELINE_ARG)) {
                mTimeline = getArguments().getString(CommonUtils.TIMELINE_ARG).toLowerCase();
            }
            if (getArguments().containsKey(CommonUtils.MOVIE_ID_ARG)) {
                movieId = getArguments().getString(CommonUtils.MOVIE_ID_ARG);
            }
        }
    }


    // inflation logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_video_list, parent, false);
        ButterKnife.bind(this, view);

        // setup the view adapter.

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        videoAdapter = new VideoAdapter(CommonUtils.getDefaultMovies());
        lvVideos.setAdapter(videoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        lvVideos.setLayoutManager(linearLayoutManager);

        lvVideos.addItemDecoration(new DividerItemDecoration((Context) mListener, DividerItemDecoration.VERTICAL_LIST));

        // Attach the listener to the AdapterView onCreate

        lvVideos.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
               // Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            }
        });


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });


    }



    // creating of lifecycle event





    public VideoAdapter getAdapter(){

        return videoAdapter;
    }

    public void addAll(List<Video> videos){

        // add code here.
    }



    public void clearAndAddAll(List<Video> videos){
        // add code here.
    }

    public interface OnMovieTimelineFragmentInteractionListener {

    }
}
