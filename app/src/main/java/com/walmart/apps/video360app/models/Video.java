package com.walmart.apps.video360app.models;

import android.net.Uri;
import android.text.format.DateUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ssahu6 on 6/22/16.
 */


// models/Video.java
@Parcel
public class Video {
    int uid;
    String userHandle;
    String createdAt;
    String timeFrom;
    String urlStr;
    Uri uri ;


    public Video(String urlStr) {
        this.urlStr = urlStr;
        this.uri = Uri.parse(urlStr);
        Log.d(Video.class.getSimpleName(), "Video: "+this);

    }
    public Video(Uri uri) {
        this.uri = uri;
        this.urlStr=uri.toString();
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    String body;

    public int getUid() {
        return uid;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getBody() {
        return body;
    }


    // Make sure to always define this constructor with no arguments
    public Video() {
    }
    // Add a constructor that creates an object from the JSON response
    public Video(JSONObject object){
        try {
            this.uid = object.getInt("id");
            this.userHandle = object.getString("user_username");
            this.createdAt = object.getString("created_at");

            // "created_at":"Mon Jan 23 06:24:52 +0000 2012"
            String aFormat = "EEE MMM d HH:mm:ss Z yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(aFormat);
            Date date = null;
            try {
                date = dateFormat.parse(this.createdAt);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // use createdAt time to set timeFrom
            this.timeFrom = DateUtils.getRelativeTimeSpanString(date.getTime(),
                    System.currentTimeMillis(),
                    DateUtils.SECOND_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE
            ).toString();

            this.body = object.getString("body");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Video fromJSON(JSONObject json){
        Video video = new Video();

        // add code here

        return video;
    }

    public static ArrayList<Video> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Video> videos = new ArrayList<Video>(jsonArray.length());

        // add code here

        return videos;
    }

    @Override
    public String toString() {
        return "Video{" +
                "uid=" + uid +
                ", userHandle='" + userHandle + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", urlStr='" + urlStr + '\'' +
                ", uri=" + uri +
                ", body='" + body + '\'' +
                '}';
    }
}