/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<earthquake>> {

    public static final String TAG = EarthquakeActivity.class.getName();

    private ProgressBar mProgressBar;

    static final int LOADER_ID = 11;

    ListView earthquakeListView;

    EarthquakeAdapter mAdapter;

    TextView mEmptyView;

    /**
     * Sample JSON response for a USGS query
     */
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2015-01-01&endtime=2015-01-05&minmagnitude=5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mEmptyView = (TextView) findViewById(R.id.empty_view);


       getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<List<earthquake>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<earthquake>>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                Log.d(TAG, "onStartLoading: onstart loading called");
                forceLoad();
            }

            @Override
            public List<earthquake> loadInBackground() {
                Log.d(TAG, "loadInBackground: load in background called");
                List<earthquake> earthquakeList = QueryUtils.fetchEarthquakeData(SAMPLE_JSON_RESPONSE);
                return earthquakeList;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<earthquake>> loader, List<earthquake> data) {
        // Find a reference to the {@link ListView} in the layout
         earthquakeListView = (ListView) findViewById(R.id.list);
        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, data);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setEmptyView(mEmptyView);
        Log.d(TAG, "onLoadFinished: onload finished called");
    }

    @Override
    public void onLoaderReset(Loader<List<earthquake>> loader) {

    }
}
