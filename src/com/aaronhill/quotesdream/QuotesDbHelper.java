package com.aaronhill.quotesdream;

import com.aaronhill.quotesdream.QuotesContract.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuotesDbHelper /*extends SQLiteOpenHelper*/ {
	/*
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quotes.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + Quote.TABLE_NAME + " (" +
        Quote._ID + " INTEGER PRIMARY KEY," +
        Quote.COLUMN_NAME_QUOTE_ID + TEXT_TYPE + COMMA_SEP +
        Quote.COLUMN_NAME_BODY + TEXT_TYPE + COMMA_SEP + Quote.COLUMN_NAME_AUTHOR +
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + Quote.TABLE_NAME;

    public QuotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    */

}