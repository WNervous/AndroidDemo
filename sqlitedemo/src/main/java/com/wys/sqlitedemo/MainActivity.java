package com.wys.sqlitedemo;

import android.Manifest;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wys.sqlitedemo.db.FeedReaderContract;
import com.wys.sqlitedemo.db.FeedReaderDbHelper;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    FeedReaderDbHelper feedReaderDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                                          new String[]{READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                          0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        feedReaderDbHelper = new FeedReaderDbHelper(this);
    }

    public void insert(View view) {

        /**
         * getWritableDatabase
         */
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        //                writableDatabase.close();
        //            }
        //        }).start();
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                insert2();
        //            }
        //        }).start();

        /**
         * getReadableDatabase
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase readableDatabase = feedReaderDbHelper.getReadableDatabase();
                readableDatabase.close();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                query(null);
            }
        }).start();


        //        SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        //        for (int i = 0; i < 10; i++) {
        //            ContentValues contentValues = new ContentValues();
        //            contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title" + i);
        //            contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "content" + i);
        //            writableDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
        //        }
        //        Log.d("MainActivity", "insert success");
    }

    public void delete(View view) {
        SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        writableDatabase.delete(FeedReaderContract.FeedEntry.TABLE_NAME,
                                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?",
                                new String[]{"title11"});
    }

    public void update(View view) {
        SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title11");
        writableDatabase.update(FeedReaderContract.FeedEntry.TABLE_NAME,
                                contentValues,
                                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?",
                                new String[]{"title1"});
    }

    public void query(View view) {
        SQLiteDatabase readableDatabase = feedReaderDbHelper.getReadableDatabase();
        String[] projection = new String[]{FeedReaderContract.FeedEntry._ID, FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE};
        //        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        //        String[] selectionArgs = {"title1"};
        //         How you want the results sorted in the resulting Cursor
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = readableDatabase.query(FeedReaderContract.FeedEntry.TABLE_NAME,
                                               // The table to query
                                               projection,
                                               // The array of columns to return (pass null to get all)
                                               null,
                                               // The columns for the WHERE clause
                                               null,
                                               // The values for the WHERE clause
                                               null,
                                               // don't group the rows
                                               null,
                                               // don't filter by row groups
                                               sortOrder
                                               // The sort order
        );

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE));
            Log.d("MainActivity", "itemId:" + itemId + "  title:  " + title + "   content:  " + content);
            //            itemIds.add(itemId);
        }
        cursor.close();
    }


    public void insert1() {
        SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title" + 101);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "content" + 101);
        writableDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
        writableDatabase.close();
    }

    public void insert2() {
        SQLiteDatabase writableDatabase = feedReaderDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title" + 102);
        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "content" + 103);
        writableDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
        writableDatabase.close();
    }
}
