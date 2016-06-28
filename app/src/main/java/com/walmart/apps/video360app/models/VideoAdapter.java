package com.walmart.apps.video360app.models;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.vr.sdk.widgets.video.VrVideoView;
import com.walmart.apps.video360app.R;
import com.walmart.apps.video360app.VideoActivity;
import com.walmart.apps.video360app.util.CommonUtils;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ssahu6 on 6/22/16.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Video> videos;
    private Context mContext;
    private final int MOVIE_VR = 0, MOVIE_YOUTUBE = 1;
    static final String TAG = VideoAdapter.class.getSimpleName();
    private VrVideoView.Options videoOptions = new VrVideoView.Options();


    public VideoAdapter(FragmentActivity activity, List<Video> videos) {
        this.mContext=activity;
        this.videos=videos;
    }

    public VideoAdapter(Context lContext, List<Video> lvideos) {
        this.mContext=lContext;
        this.videos=lvideos;
    }
    public Video getItem(int position) {
        return videos.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

            // add code here
        }
    }

    public VideoAdapter(List<Video> videos) {

        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder =null;
        try {
            Log.d(TAG, "onCreateViewHolder: !!!!! 11111  viewType "+viewType);
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            switch (viewType) {
                case MOVIE_VR:
                    View videoVR = inflater.inflate(R.layout.item_video, parent, false);
                    viewHolder = new ViewHolderVrMovie(videoVR);
                    Log.d(TAG, "onCreateViewHolder: MOVIE_VR viewHolder "+viewHolder);
                    break;

                case MOVIE_YOUTUBE:
                    Log.d(TAG, "onCreateViewHolder: MOVIE_YOUTUBE");
                    break;

                default:
                    Log.d(TAG, "onCreateViewHolder: default");
                    View videoVR1 = inflater.inflate(R.layout.item_video, parent, false);
                    viewHolder = new ViewHolderVrMovie(videoVR1);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return viewHolder;
        //return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        try {
            switch (viewHolder.getItemViewType()) {
                case MOVIE_VR:
                    Log.d(TAG, "onBindViewHolder:22222 MOVIE_VR  2222222222 mContext "+mContext);

                    ViewHolderVrMovie viewHolderVr = (ViewHolderVrMovie) viewHolder;
                    Video video = videos.get(position);
                    viewHolderVr.video_view.loadVideo(video.getUri(), videoOptions);
                    viewHolderVr.video_view.setClickable(true);
                    viewHolderVr.video_view.pauseVideo();
                    if (viewHolderVr.video_view.callOnClick()){
                        Log.d(TAG, " setOnClickListener onClick:#################################   position " + position);
                        Intent intent = new Intent(mContext, VideoActivity.class);
                        intent.putExtra(CommonUtils.MOVIE_VR, Parcels.wrap(video));
                        mContext.startActivity(intent);
                    }
                    viewHolderVr.video_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = v.getVerticalScrollbarPosition();
                            Log.d(TAG, " setOnClickListener onClick:#################################   position " + position);
                            Video video = videos.get(position);
                            Intent intent = new Intent(mContext, VideoActivity.class);
                            intent.putExtra(CommonUtils.MOVIE_VR, Parcels.wrap(video));
                            mContext.startActivity(intent);
                        }
                    });

                    break;
                case MOVIE_YOUTUBE:
                    Log.d(TAG, "onBindViewHolder: MOVIE_YOUTUBE");

                    break;
                default:
                    Log.d(TAG, "onBindViewHolder: default");

                    break;
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    public abstract class ViewHolderCommon extends RecyclerView.ViewHolder implements View.OnClickListener {



        public ViewHolderCommon(View itemView) {
            super(itemView);
        }
    }


    public class ViewHolderVrMovie extends ViewHolderCommon {

        @Bind(R.id.video_view)
        public VrVideoView video_view;
        // @Bind(R.id.bttn_watch_vid)
        public Button bttn_watch_vid;

        public ViewHolderVrMovie(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {

                int position = getLayoutPosition();
                Video video = videos.get(position);
                Log.d(TAG, "onClick: position @@@@@@@  lllll " + position);
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra(CommonUtils.MOVIE_VR, Parcels.wrap(video));
                Log.d(TAG, "onClick: startActivity $$$$$$$$$$$$$$$$$$$$ ");
                mContext.startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }




}