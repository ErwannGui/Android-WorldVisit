package ynov.worldvisit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "pays_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_pays = "pays";
    private static final String KEY_ID = "id";
    private static final String NAME = "name";
    private static final String CAPITALE = "capital";
    private static final String CONTINENT = "name";
    private static final String DATE = "date";

    /*CREATE TABLE pays ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_pays = "CREATE TABLE "
            + TABLE_pays + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + CAPITALE + CONTINENT + DATE + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_pays);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_pays);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_pays + "'");
        onCreate(db);
    }

    public long addpaysDetail(String name, String capital, String region, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(CAPITALE, capital);
        values.put(CONTINENT, region);
        values.put(DATE, date);
        // insert row in pays table
        long insert = db.insert(TABLE_pays, null, values);

        return insert;
    }

    public ArrayList<String> getAllpaysList() {
        ArrayList<String> paysArrayList = new ArrayList<String>();
        String name="";
        String capital="";
        String region="";
        String date="";
        String selectQuery = "SELECT  * FROM " + TABLE_pays;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(NAME));
                capital = c.getString(c.getColumnIndex(CAPITALE));
                region = c.getString(c.getColumnIndex(CONTINENT));
                date = c.getString(c.getColumnIndex(DATE));
                // adding to pays list
                paysArrayList.add(name);
                paysArrayList.add(capital);
                paysArrayList.add(region);
                paysArrayList.add(date);
            } while (c.moveToNext());
            Log.d("array", paysArrayList.toString());
        }
        return paysArrayList;
    }

}
