package com.example.harveypokedex;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class SQLContentProvider extends ContentProvider {

    public static final String DBNAME = "PokeDB";
    public static final String TABLE_NAME = "Entries";
    public static final  String COL_NATNUM = "Number";
    public static final String COL_NAME = "Name";
    public static final String COL_SPECIES = "Species";
    public static final String COL_GENDER = "Gender";
    public static final String COL_HEIGHT = "Height";
    public static final String COL_WEIGHT = "Weight";
    public static final String COL_LEVEL = "Level";
    public static final String COL_HP = "HP";
    public static final String COL_ATTACK = "Attack";
    public static final String COL_DEFENSE = "Defense";

    public static final String AUTHORITY = "com.harvey.pokedex";

    public static final Uri contentURI = Uri.parse("content://" + AUTHORITY + "/" + DBNAME);

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + TABLE_NAME +
            "( _ID INTEGER PRIMARY KEY," +
            COL_NATNUM + " TEXT," +
            COL_NAME + " TEXT," +
            COL_SPECIES + " TEXT," +
            COL_GENDER + " TEXT," +
            COL_HEIGHT + " TEXT," +
            COL_WEIGHT + " TEXT," +
            COL_LEVEL + " TEXT," +
            COL_HP + " TEXT," +
            COL_ATTACK + " TEXT," +
            COL_DEFENSE + " TEXT)";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_QUERY);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };
    private MainDatabaseHelper SQLHelper;


    public SQLContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return SQLHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = SQLHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
        return Uri.withAppendedPath(contentURI, "" + id);
    }

    @Override
    public boolean onCreate() {
        SQLHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return SQLHelper.getReadableDatabase().query(TABLE_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}