package com.example.fitnessbud.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "nutrition_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "food";
    private static final String COL3 = "calories";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT)";

        DB.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);

    }

    public boolean insertData(String food, String calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("food", food);
        contentValues.put("calories",calories);

        long res = db.insert(TABLE_NAME, null, contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public int addCalories() {
        SQLiteDatabase db = this.getWritableDatabase();

        int total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(calories) FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        return total;
    }


}
