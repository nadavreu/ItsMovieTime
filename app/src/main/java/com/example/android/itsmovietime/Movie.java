package com.example.android.itsmovietime;

import java.io.Serializable;

/**
 * Created by Android on 21/03/2018.
 */

public class Movie implements Serializable {
    private String id;

    private String title;//movie name

    private String overview;//description

    private String release_date;

    private String adult;//true:18+ or false:any age

    private String poster_path;//url for the movie poster

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public String toString() {
        return "ClassMovie [id = " + id + ", title = " + title + ", overview = " + overview + "release_date = " + release_date +", adult = " + adult + ", poster_path = " + poster_path + "]";
    }
}
