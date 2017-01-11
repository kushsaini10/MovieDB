package com.example.kush.moviedb.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by saini on 02-Jan-17.
 */

public class MovieDBJSONUtils {
    public static MoviePosterClass[] getMovieDataFromJson(Context context, String moviesJson) {
        JSONObject obj;
        try {
                obj = new JSONObject(moviesJson);
            JSONArray movies = obj.getJSONArray("results");
            MoviePosterClass[] output = new MoviePosterClass[movies.length()];
            for (int i = 0; i < movies.length(); i++) {
                JSONObject batchesJSONObject = movies.getJSONObject(i);
                String imagePosterPath = batchesJSONObject.getString("poster_path");
                int movieId = batchesJSONObject.getInt("id");
                String imageButtonPath = "http://image.tmdb.org/t/p/w342" + imagePosterPath;
                output[i] = new MoviePosterClass(imageButtonPath, movieId);
            }
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MovieDetailClass getMovieDetailsFromJson(Context context, String movieJson) {
        JSONObject obj;
        try {
            obj = new JSONObject(movieJson);
            String backDrop = "http://image.tmdb.org/t/p/w500/" + obj.getString("backdrop_path");
            String poster = "http://image.tmdb.org/t/p/w342/" + obj.getString("poster_path");
            int id = obj.getInt("id");
            int rating = obj.getInt("vote_average");
            String date = obj.getString("release_date");
            String name = obj.getString("original_title");
            String synops = obj.getString("overview");
            return new MovieDetailClass(id,name,synops,date,rating,backDrop,poster);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
