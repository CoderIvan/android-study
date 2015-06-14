package com.test.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Ivan on 2015/6/13.
 */
public class MyProvider extends ContentProvider {

    private static final int TABLE1_DIR = 0;

    private static final int TABLE1_ITEM = 1;

    private static final int TABLE2_DIR = 2;

    private static final int TABLE2_ITEM = 3;

    private static final String AUTHORITY = "com.example.app.provider";

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "table1", TABLE1_DIR);
        uriMatcher.addURI(AUTHORITY, "table1/#", TABLE1_ITEM);
        uriMatcher.addURI(AUTHORITY, "table2", TABLE2_DIR);
        uriMatcher.addURI(AUTHORITY, "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                break;
            case TABLE1_ITEM:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
            default:
                break;
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + ".table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + ".table2";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
