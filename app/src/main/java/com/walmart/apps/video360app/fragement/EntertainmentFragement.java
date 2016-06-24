package com.walmart.apps.video360app.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by ssahu6 on 6/22/16.
 */
public class EntertainmentFragement extends BaseFragement{


    @Override
    public void populateVideos(int page) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateVideos(1);
    }
}
