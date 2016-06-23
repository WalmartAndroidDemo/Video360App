package com.walmart.apps.video360app.models;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walmart.apps.Video360App.R;

import java.util.List;


/**
 * Created by ssahu6 on 6/22/16.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private List<Video> videos;

    public VideoAdapter(FragmentActivity activity, List<Video> videos) {
    }

    public Video getItem(int position) {
        return videos.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvUserName, tvBody, tvTime;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            tvBody = (TextView) view.findViewById(R.id.tvBody);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
        }
    }

    public VideoAdapter(List<Video> videos) {

        this.videos = videos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Video video = videos.get(position);
        holder.tvBody.setText(video.getBody());
        holder.tvTime.setText(video.getTimeFrom());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
