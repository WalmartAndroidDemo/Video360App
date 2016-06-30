package com.walmart.apps.video360app.util;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.UiThread;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

/**
 * Created by dkthaku on 6/30/16.
 */
public class VideoLoader {
    public  static final String TAG = VideoLoaderTask.class.getSimpleName();

    private static VideoLoader videoLoader;

    private VideoLoader() {
    }

    public static void loadVideo(Uri uri, VrVideoView videoView){
       getVideoLoader().dynamicLoadVideo(uri, videoView);
    }
    private void dynamicLoadVideo(Uri uri, VrVideoView videoView){
        try {
            VideoLoaderTask videoLoaderTask = new VideoLoaderTask();
            videoLoaderTask.execute(Pair.create(uri, videoView));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static VideoLoader getVideoLoader(){
       if(videoLoader==null){
           videoLoader=new VideoLoader();
       }
        return videoLoader;
    }

    /**
     * Helper class to manage threading.
     */

    class VideoLoaderTask extends AsyncTask<Pair<Uri, VrVideoView>, Void, Boolean> {
        private VrVideoView.Options videoOptions = new VrVideoView.Options();

        //  @SuppressWarnings("deprecation")

        @UiThread
        @Override
        protected Boolean doInBackground(Pair<Uri, VrVideoView>... fileInformation) {
            try {
                videoOptions.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
                Log.d(TAG, "doInBackground: @@@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
                if (fileInformation == null || fileInformation.length < 1
                        || fileInformation[0] == null || fileInformation[0].first == null) {
                    Log.d(TAG, "doInBackground: fileInformation == null ");
                   // videoWidgetView.loadVideoFromAsset("congo_2048.mp4");

                } else {
                    VrVideoView  videoWidgetView =fileInformation[0].second;
                    Uri uri = fileInformation[0].first;
                    Log.d(TAG, "doInBackground: fileInformation != null "+ fileInformation[0]);
                    videoWidgetView.loadVideo(uri, videoOptions);
                    videoWidgetView.setClickable(true);
                    videoWidgetView.pauseVideo();
                    View framelayout = ((ViewGroup) videoWidgetView).getChildAt(0);
                    // ((ViewGroup)((ViewGroup)((ViewGroup)framelayout).getChildAt(1)).getChildAt(2)).getChildAt(0).performClick();
                }
            } catch (IOException e) {
                // An error here is normally due to being unable to locate the file.
               // loadVideoStatus = LOAD_VIDEO_STATUS_ERROR;
                // Since this is a background thread, we need to switch to the main thread to show a toast.
//                videoWidgetView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(VideoActivity.this, "Error opening file. ", Toast.LENGTH_LONG).show();
//                    }
//                });
                Log.e(TAG, "Could not open video: " + e);
            }

            return true;
        }
    }


}
