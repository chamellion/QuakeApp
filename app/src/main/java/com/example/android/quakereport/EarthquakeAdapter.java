package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<earthquake> {

    private static final String TAG = "EarthquakeAdapter";
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake, parent, false);
        }
        earthquake currentQuake = getItem(position);
        TextView magTextView = (TextView) listView.findViewById(R.id.textView);
        magTextView.setText(currentQuake.getmMag());

        TextView locTextView = (TextView) listView.findViewById(R.id.textView2);
        locTextView.setText(currentQuake.getmLocation());

        TextView dateTextView = (TextView) listView.findViewById(R.id.textView3);
        Date date = new Date(currentQuake.getmDate());
        Log.d(TAG, "getView: date is" + date);
        dateTextView.setText(formatDate(date));



        return listView;


    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat simpeDateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return simpeDateFormat.format(dateObject);

    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat simpeTimeFormat = new SimpleDateFormat("h mm,a");
        return simpeTimeFormat.format(dateObject);
    }
}
