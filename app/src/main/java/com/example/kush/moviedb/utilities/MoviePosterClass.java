package com.example.kush.moviedb.utilities;

/**
 * Created by saini on 28-Jun-16.
 */
public class MoviePosterClass {
    private String imagePicPath;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public MoviePosterClass(String imagePicPath, int id) {
        this.imagePicPath = imagePicPath;
        this.id = id;
    }

    public String getImagePicPath() {
        return imagePicPath;
    }

    public void setImagePicPath(String imagePicPath) {
        this.imagePicPath = imagePicPath;
    }
}
