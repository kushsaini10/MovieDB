package com.example.kush.moviedb.utilities;

/**
 * Created by saini on 11-Jan-17.
 */

public class MovieDetailClass {
    private int id;
    private String name;
    private String synops;
    private String releaseDate;
    private int rating;
    private String backDrop;
    private String poster;

    public MovieDetailClass(int id, String name, String synops, String releaseDate, int rating, String backDrop, String poster) {
        this.id = id;
        this.name = name;
        this.synops = synops;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.backDrop = backDrop;
        this.poster = poster;
    }

    public String getBackDrop() {
        return backDrop;
    }

    public void setBackDrop(String backDrop) {
        this.backDrop = backDrop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynops() {
        return synops;
    }

    public void setSynops(String synops) {
        this.synops = synops;
    }

}
