package com.example.kush.moviedb.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by saini on 24-Dec-16.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private final static String POPULAR_PATH = "popular";
    private final static String TOP_RATED_PATH = "top_rated";
    private final static String QUERY_API_KEY = "api_key";
    private final static String QUERY_LANGUAGE = "language";
    private final static String QUERY_PAGE = "page";
    private final static String QUERY_API_KEY_VALUE = ApiKey.API_KEY;
    private final static String QUERY_LANGUAGE_VALUE = "en-US";
    private final static String QUERY_PAGE_VALUE = "1";

    public static URL buildUrl(String type) {
        String PATH;

        if (type == "pop")
            PATH = POPULAR_PATH;
        else if (type == "top")
            PATH = TOP_RATED_PATH;
        else
            PATH = type;

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH)
                .appendQueryParameter(QUERY_API_KEY,QUERY_API_KEY_VALUE)
                .appendQueryParameter(QUERY_LANGUAGE,QUERY_LANGUAGE_VALUE)
                .appendQueryParameter(QUERY_PAGE,QUERY_PAGE_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
