package com.example.kush.moviedb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kush.moviedb.utilities.MovieDBJSONUtils;
import com.example.kush.moviedb.utilities.MovieDetailClass;
import com.example.kush.moviedb.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import it.sephiroth.android.library.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView backDrop;
    ImageView poster;
    TextView releaseDate;
    TextView name;
    TextView synopsis;
    TextView rating;
    LinearLayout details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String id = String.valueOf(getIntent().getExtras().getInt("movieId"));

        loadDetails(id);
    }

    private void loadDetails(String id) {
        new FetchDetailTask().execute(id);
    }

    public class FetchDetailTask extends AsyncTask<String, Void, MovieDetailClass>{

        @Override
        protected MovieDetailClass doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String type = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(type);

            String jsonResponse;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                return MovieDBJSONUtils.getMovieDetailsFromJson(DetailsActivity.this, jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MovieDetailClass movieDetailClass) {
            super.onPostExecute(movieDetailClass);

            backDrop = (ImageView) findViewById(R.id.movieBackDrop);
            poster = (ImageView) findViewById(R.id.moviePoster);
            releaseDate = (TextView) findViewById(R.id.releaseDate);
            synopsis = (TextView) findViewById(R.id.movieDesc);
            name = (TextView) findViewById(R.id.movieName);
            rating = (TextView) findViewById(R.id.rating);

            details = (LinearLayout) findViewById(R.id.activity_details);

            Picasso.with(DetailsActivity.this).load(movieDetailClass.getBackDrop()).into(backDrop);
            Picasso.with(DetailsActivity.this).load(movieDetailClass.getPoster()).into(poster);
            String mDate = movieDetailClass.getReleaseDate();
            int date = Integer.parseInt(mDate.substring(8));
            int month = Integer.parseInt(mDate.substring(5,7));
            int year = Integer.parseInt(mDate.substring(0,4));
            releaseDate.setText("" + date + " / " + month + " / " + year);
            name.setText(movieDetailClass.getName() + "  (" + year +")");
            synopsis.setText(String.valueOf(movieDetailClass.getSynops()));
            rating.setText(String.valueOf(movieDetailClass.getRating()));

            details.setVisibility(View.VISIBLE);
        }
    }
}
