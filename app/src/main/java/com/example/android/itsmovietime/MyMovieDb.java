package com.example.android.itsmovietime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 21/03/2018.
 */

public class MyMovieDb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "itsMovieTime";

    public static final String TABLE_MOVIES = "Movies";

    public static final String KEY_ID = "id";
    public static final String KEY_MOVIEID = "idMovie";
    public static final String KEY_NAME = "movieName";
    public static final String KEY_DESCRIPTION= "movieDescription";
    public static final String KEY_DATERELEASE= "movieDateRelease";
    public static final String KEY_URL = "movieURL";
    public static final String KEY_ADULT = "ADULT";
    ;

    //We need to pass database information along to superclass because the super class doesn't have any default constructor

    public MyMovieDb(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //in the first time that we run this app we want to create our table in order to store data
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +KEY_MOVIEID+"TEXT"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATERELEASE + " TEXT, "
                + KEY_URL + " TEXT, "
                + KEY_ADULT + " TEXT, "
                +")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        // Create tables again
        onCreate(db);
    }

    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES,null,null);
        db.execSQL("delete from "+ TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        onCreate(db);
    }

    public void addMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_MOVIEID, movie.getId());
        values.put(KEY_NAME, movie.getTitle());
        values.put(KEY_DESCRIPTION, movie.getOverview());
        values.put(KEY_DATERELEASE, movie.getRelease_date());
        values.put(KEY_URL, movie.getPoster_path());
        values.put(KEY_ADULT, movie.getAdult());


        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }


    public boolean deleteMovie(int delID) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_MOVIES, KEY_ID + "=" + delID, null) > 0;

    }


    public List<Movie> getAllMovieList() {


        List<Movie> MovieList = new ArrayList<Movie>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Movie movie = new Movie();
                movie.setId((cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setOverview(cursor.getString(2));
                movie.setPoster_path(cursor.getString(3));
                movie.setRelease_date((cursor.getString(4)));
                movie.setAdult((cursor.getString(5)));

                // Adding contact to list
                MovieList.add(movie);

            } while (cursor.moveToNext());
        }

        // return contact list
        return MovieList;
    }

}
