package com.walmart.apps.video360app.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import com.walmart.apps.video360app.R;
import com.walmart.apps.video360app.models.Video;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by dkthaku on 6/23/16.
 */
public class CommonUtils {

    public static final String TIMELINE_ARG = "timeline";
    public static final String TIMELINE_TRENDING = "Trending";
    public static final String TIMELINE_FUN = "Fun";
    public static final String TIMELINE_MEDIA = "Media";
    public static final String TIMELINE_POLITICS = "Politics";
    public static final String TIMELINE_NEWS = "New";


    public static final int TIMELINE_TRENDING_TAB = 0;
    public static final int TIMELINE_FUN_TAB = 1;
    public static final int TIMELINE_NEWS_TAB = 2;
    public static final int TIMELINE_POLITICS_TAB = 3;

    public static final String MOVIE_ID_ARG = "movieId";
    public static final String MOVIE_VR = "VrMovie";
    static final String TAG = CommonUtils.class.getSimpleName();
    public static final String DATE_FORMAT = "ccc MMM dd hh:mm:ss Z yyyy";


    public static ArrayList<Video> getDefaultMovies(){
            ArrayList<Video>  movieList = new ArrayList<>();
            movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/9256d26c-2712-446e-b1dc-f461f0478fc4/web.mp4"));
             movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/db0d960d-5e76-4f6f-9332-14fce8952f87/web.mp4"));
        movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/a25016a8-de5e-40bb-8b41-4557cca15965/web.mp4"));
        movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/be941812-6478-4a07-93f8-dd71f2075616/web.mp4"));
        movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/7103e91c-e292-4993-81fc-66352ab9ce3c/web.mp4"));
        return movieList;

    }

    public static int getMovieType(Bundle bundle){
        Log.d(TAG, "getMovieType: bundle "+bundle);
        int tab=-1;
        String timelineArg =null;
        if(bundle != null) {
            timelineArg=bundle.getString(CommonUtils.TIMELINE_ARG);
            Log.d(TAG, "getMovieType: timelineArg "+timelineArg);
            if (timelineArg.equalsIgnoreCase(CommonUtils.TIMELINE_TRENDING)){
                    tab=CommonUtils.TIMELINE_TRENDING_TAB;
            }else if(timelineArg.equalsIgnoreCase(CommonUtils.TIMELINE_NEWS)){
                tab=CommonUtils.TIMELINE_NEWS_TAB;
            }else if(timelineArg.equalsIgnoreCase(CommonUtils.TIMELINE_FUN)){
                tab=CommonUtils.TIMELINE_FUN_TAB;
            }else if(timelineArg.equalsIgnoreCase(CommonUtils.TIMELINE_POLITICS)){
                tab=CommonUtils.TIMELINE_POLITICS_TAB;
            }
        }
        Log.d(TAG, "getMovieType: tab "+tab);
        return tab;
    }

    public static ArrayList<Video> getDefaultMovies(int movieTypes){
        Log.d(TAG, "getDefaultMovies: movieTypes "+movieTypes);
        ArrayList<Video> movieList = new ArrayList<>();
        switch (movieTypes) {
            case TIMELINE_TRENDING_TAB:
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/f1e1a16a-854e-4ff6-bc94-0c55fa5d41e5/web.mp4"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/0d2187b9-0186-462e-b943-06da419818c5/web.mp4"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/9256d26c-2712-446e-b1dc-f461f0478fc4/web.mp4"));
                //movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/db0d960d-5e76-4f6f-9332-14fce8952f87/web.mp4"));

            case TIMELINE_FUN_TAB:
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/a25016a8-de5e-40bb-8b41-4557cca15965/web.mp4"));
               // movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/d82c5cf1-9197-4a38-b096-41307fdff9b4/web.mp4"));
               // movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/d0eef638-8e8c-4940-8def-8db07ebd8b69/web.mp4"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/1c8b0cbc-4dcd-4c14-a0ed-7f1d5f6632ff/web2.mp4", "Dancing With the Stars 360"));
                break;
            case TIMELINE_NEWS_TAB :
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/7103e91c-e292-4993-81fc-66352ab9ce3c/web.mp4"));
               // movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/9256d26c-2712-446e-b1dc-f461f0478fc4/web.mp4", "360 journey through Chernobyl 30 years after the worst nuclear disaster in history"));
                //movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/9626ac69-a0bd-4fc7-86b4-cdc18089969f/web.mp4", "Project Earth: Greenland Climate Change 360"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/0987e3d9-3f99-4fc7-92a8-2ac1e962f714/web.mp4","Virtual guided tour of Paris"));

                break;
            case TIMELINE_POLITICS_TAB :
                //movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/5aff875a-32df-46fb-8f53-b92bf86bb853/web.mp4","Inside Trump America"));
               // movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/83ef40fe-6e8e-45ed-86f8-871c89c3a60f/web.mp4", "Virtual Yellowstone 360"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/d2b34e83-c22c-4f86-9776-7e1afb0c0b49/web.mp4", "After twin earthquakes in Japan, ABC News"));
                movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/1a38869c-bded-43aa-a9dc-5afb9fbd7742/web.mp4", "NEPAL: After The Earthquake VR"));
                break;

            default:
                break;
        }
        return movieList;

    }

    public static String getFormattedTimestamp(String date) {

        try {
            // DateFormat df = DateFormat.getDateInstance(DATE_FORMAT);
            //DateTime createdAt = new DateTime(date);
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("h:mm a - dd MMM yy");

            Date myDate = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(date);
            Log.d(TAG, "getFormattedTimestamp: date passed "+myDate);
            String newDate = new SimpleDateFormat("h:mm a - dd MMM yy").format(myDate);
            Log.d(TAG, "getFormattedTimestamp: newDate "+newDate);


            return newDate;
        } catch (Exception e){
            e.printStackTrace();
            return "1";
        }

    }


    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), 0, DateUtils.FORMAT_ABBREV_ALL).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }



    public static String getFormattedRelativeTimestamp(String date) {

        try {
            // DateFormat df = DateFormat.getDateInstance(DATE_FORMAT);
            Date myDate = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(date);
            // Log.d(TAG, "getFormattedRelativeTimestamp: myDate "+myDate);
            DateTime historicDateTime = new DateTime(myDate);
            // Log.d(TAG, "getFormattedRelativeTimestamp: historicDateTime "+historicDateTime);
            DateTime now = new DateTime();
            // Log.d(TAG, "getFormattedRelativeTimestamp: now date "+date);
            Interval interval = new Interval(historicDateTime, now);

            Period period = interval.toPeriod();

            String elapsed;

            if (period.getYears() > 0) {
                elapsed = String.valueOf(period.getYears()) + "Y";
            } else if (period.getMonths() > 0) {
                elapsed = String.valueOf(period.getMonths()) + "M";
            } else if (period.getWeeks() > 0) {
                elapsed = String.valueOf(period.getWeeks()) + "W";
            } else if (period.getDays() > 0) {
                elapsed = String.valueOf(period.getDays()) + "d";
            } else if (period.getMinutes() > 0) {
                elapsed = String.valueOf(period.getMinutes()) + "m";
            } else {
                elapsed = String.valueOf(period.getSeconds()) + "s";
            }

            return elapsed;
        } catch (Exception e){
            e.printStackTrace();
            return "001";
        }

    }

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean value = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        if (!value) {
            renderSnackBar(activity, "No network connection. Please check network settings and activate either Wifi or Data.");
        }
        return value;
    }

    public static boolean isOnline(Activity activity) {
        Runtime runtime = Runtime.getRuntime();
        Boolean value = false;
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            value = (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!value) {
            renderSnackBar(activity, "Current network not connected to the internet. Please try again after some time or contact network operator.");
        }
        return value;
    }

    private static void renderSnackBar(Activity activity, String msg) {

        final Snackbar snackBar = Snackbar.make(activity.getWindow().findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE);

        snackBar.setAction("Dismiss", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                snackBar.dismiss();

            }
        });
        snackBar.setActionTextColor(Color.WHITE).show();
    }

    public static SpannableString formatTwitterText(Context context, String text) {
        SpannableString captionSpannableString = new SpannableString(text);
        Pattern pattern = Pattern.compile("[#|@].+?\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ForegroundColorSpan tagMentionColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary));
            captionSpannableString.setSpan(tagMentionColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return captionSpannableString;
    }
}
