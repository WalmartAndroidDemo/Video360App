package com.walmart.apps.video360app.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.walmart.apps.Video360App.EndlessScrollListener;
import com.walmart.apps.Video360App.R;
import com.walmart.apps.Video360App.VideoActivity;
import com.walmart.apps.Video360App.models.Video;
import com.walmart.apps.Video360App.models.VideoAdapter;

import java.util.List;

/**
 * Created by ssahu6 on 6/22/16.
 */
public abstract class BaseFragement extends Fragment {


    private static int pageScollCount = 0;
    private ListView lvVideos;
    private VideoAdapter aVideos;



    protected abstract void populateVideos(int page);

    // inflation logic
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragement_video_list, parent, false);
        lvVideos = (ListView) v.findViewById(R.id.lvVideos);

        final ListAdapter aTweets = null;
        lvVideos.setAdapter(aTweets);

        // Attach the listener to the AdapterView onCreate
        lvVideos.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                if(page > pageScollCount) {

                    //get another 10 pages.
                    pageScollCount = pageScollCount+10;
                    populateVideos(1);

                }
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }

            @Override
            public int getFooterViewType() {
                return 0;
            }

        });

        lvVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView adapterView, View view, int position, long val)
            {
                Video v = aVideos.getItem(position);
                Bundle b = new Bundle();
                Intent i = new Intent(getActivity(), VideoActivity.class);
                i.putExtras(b);
                startActivity(i);


              //  Toast.makeText(getActivity(), "Stop Clicking me", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    // creating of lifecycle event
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Video> videos = null;
        aVideos = new VideoAdapter(getActivity(), videos);
    }

    public VideoAdapter getAdapter(){

        return aVideos;
    }

    public void addAll(List<Video> videos){
//        aVideos.addAll(videos);
    }

    public void clearAndAddAll(List<Video> videos){
//        aTweets.clear();
//        aTweets.addAll(tweets);
    }
}
