package com.walmart.apps.video360app.models;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.walmart.apps.video360app.R;

import java.util.List;

/**
 * Created by ssahu6 on 6/22/16.
 */

public class VideoAdaperTest extends ArrayAdapter<Video> {


    public VideoAdaperTest(Context context, List<Video> videos) {
        super(context, android.R.layout.simple_list_item_1, videos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Video video = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_video1, parent, false);
        }

        TextView tvStatus = (TextView)convertView.findViewById(R.id.status_text1);

        tvStatus.setText(video.getBody());
        return convertView;
    }
}
