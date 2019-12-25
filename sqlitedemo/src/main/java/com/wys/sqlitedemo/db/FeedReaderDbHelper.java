package com.wys.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

import static com.wys.sqlitedemo.db.FeedReaderContract.SQL_CREATE_ENTRIES;
import static com.wys.sqlitedemo.db.FeedReaderContract.SQL_DELETE_ENTRIES;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "FeedReader.db";
    public static final String PATH             = Environment.getExternalStorageDirectory() + File.separator + "wys" + File.separator + DATABASE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, PATH, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
