package com.test.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Ivan on 2015/6/13.
 */
public class DatabaseProvider extends ContentProvider {

    private static final int BOOK_DIR = 0;

    private static final int BOOK_ITEM = 1;

    private static final int CATEGORY_DIR = 2;

    private static final int CATEGORY_ITEM = 3;

    private static final String AUTHORITY = "com.test.databasetest.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                return db.query("Book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
            case CATEGORY_DIR:
                return db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                return db.query("Category", projection, "id = ?", new String[]{categoryId}, null, null, sortOrder);
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                return Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategory = db.insert("Category", null, values);
                return Uri.parse("content://" + AUTHORITY + "/book/" + newCategory);
            default:
                return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return db.update("Book", values, selection, selectionArgs);
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                return db.update("Book", values, "id = ?", new String[]{bookId});
            case CATEGORY_DIR:
                return db.update("Category", values, selection, selectionArgs);
            case CATEGORY_ITEM:
                String categroyId = uri.getPathSegments().get(1);
                return db.update("Category", values, "id = ?", new String[]{categroyId});
            default:
                return 0;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return db.delete("Book", selection, selectionArgs);
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                return db.delete("Book", "id = ?", new String[]{bookId});
            case CATEGORY_DIR:
                return db.delete("Category", selection, selectionArgs);
            case CATEGORY_ITEM:
                String categroyId = uri.getPathSegments().get(1);
                return db.delete("Category", "id = ?", new String[]{categroyId});
            default:
                return 0;
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.test.databasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.test.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.test.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.test.databasetest.provider.category";
            default:
                return null;
        }
    }
}
