package com.example.kush.moviedb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kush.moviedb.utilities.MovieDBJSONUtils;
import com.example.kush.moviedb.utilities.MoviePosterClass;
import com.example.kush.moviedb.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView homeRecyclerView;
    ArrayList<MoviePosterClass> data;
    TextView mErrorMessageDisplay;
    ProgressBar mLoadingIndicator;
    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);
        data = new ArrayList<>();
        adapter = new HomeAdapter(data,this);

        homeRecyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        homeRecyclerView.setLayoutManager(gridLayoutManager);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadHomeData("pop");
    }

    private void loadHomeData(String type) {
        new FetchHomeTask().execute(type);
    }

    private void showHomeDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        homeRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        homeRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchHomeTask extends AsyncTask<String, Void, MoviePosterClass[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected MoviePosterClass[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String type = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(type);

            String jsonResponse;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                MoviePosterClass[] homeData = MovieDBJSONUtils.getMovieDataFromJson(HomeActivity.this, jsonResponse);
                return homeData;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MoviePosterClass[] moviePosterClasses) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (moviePosterClasses != null) {
                showHomeDataView();
                data.clear();

                for(MoviePosterClass i : moviePosterClasses)
                    data.add(i);
                adapter.notifyDataSetChanged();
            } else {
                showErrorMessage();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.popular){
            loadHomeData("pop");
        }
        else if (menuItemId == R.id.highrateing){
            loadHomeData("top");
        }
        return super.onOptionsItemSelected(item);
    }
}
