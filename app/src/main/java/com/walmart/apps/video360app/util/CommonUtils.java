package com.walmart.apps.video360app.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    public static final String MOVIE_ID_ARG = "movieId";
    public static final String MOVIE_VR = "VrMovie";
    static final String TAG = CommonUtils.class.getSimpleName();
    public static final String DATE_FORMAT = "ccc MMM dd hh:mm:ss Z yyyy";


    public static ArrayList<Video> getDefaultMovies(){
            ArrayList<Video>  movieList = new ArrayList<>();
            movieList.add(new Video("https://kelley-holmes-uljc.squarespace.com/s/space_background.mp4"));
             movieList.add(new Video("https://d3uo9a4kiyu5sk.cloudfront.net/production/db0d960d-5e76-4f6f-9332-14fce8952f87/web.mp4"));
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
