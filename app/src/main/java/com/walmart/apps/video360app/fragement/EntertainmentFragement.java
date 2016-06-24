package com.walmart.apps.video360app.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.walmart.apps.video360app.R;
import com.walmart.apps.video360app.models.Video;
import com.walmart.apps.video360app.models.VideoAdaperTest;

import java.util.ArrayList;

/**
 * Created by ssahu6 on 6/22/16.
 */
public class EntertainmentFragement extends BaseFragement{


    private ListView lvVideos;
    private VideoAdaperTest adaperTest;

    private ArrayList<Video> videos = new ArrayList<>();

    private static Fragment fragment;

    @Override
    public void populateVideos(int page) {

        // random feed
        for(int i=0;i<10;i++){
            Video v = new Video();
            v.setBody("saumya entertainment"+i);
            videos.add(v);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateVideos(1);
    }

    public static Fragment newInstance() {

        if(fragment == null) {
            fragment =  new EntertainmentFragement();
        }
        return fragment;

    }


    private VideoAdaperTest adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragement_video_list, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        lvVideos = (ListView) view.findViewById(R.id.lvVideos);
        lvVideos.setAdapter(adapter1);
        Toast.makeText(getActivity(), "EntertainmentFragement", Toast.LENGTH_LONG).show();

        // Attach the listener to the AdapterView onCreate
    }
}
