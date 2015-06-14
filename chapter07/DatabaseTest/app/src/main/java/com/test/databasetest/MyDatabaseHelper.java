package com.test.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Ivan on 2015/6/13.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_BOOK = "CREATE TABLE Book("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "author TEXT,"
            + "price REAL,"
            + "pages INTEGER,"
            + "name TEXT"
            + ")";

    private static final String CREATE_CATEGORY = "CREATE TABLE Category("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "category_name TEXT,"
            + "category_code INTEGER"
            + ")";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
