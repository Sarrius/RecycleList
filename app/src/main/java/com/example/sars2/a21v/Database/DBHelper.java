package com.example.sars2.a21v.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sars2.a21v.Constants;

/**
 * Created by sars2 on 12.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DATABASE_FILE_NAME, factory, Constants.DATABASE_VERSION_1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Constants.DATABASE_TABLE_NAME + "(" +
                Constants.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                Constants.TABLE_COLUMN_FOR_NAME + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DATABASE_TABLE_NAME);
        onCreate(db);
    }

    public void addProduct (Products product) {
        ContentValues values = new ContentValues();
        values.put(Constants.TABLE_COLUMN_FOR_NAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Constants.DATABASE_TABLE_NAME, null, values);
        db.close();

    }

    public void deleteProduct (String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Constants.DATABASE_TABLE_NAME + " WHERE " +
                Constants.TABLE_COLUMN_FOR_NAME + "\"" + productName + "\";" );
    }

    public String databaseToString (){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + Constants.DATABASE_TABLE_NAME + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex(Constants.TABLE_COLUMN_FOR_NAME))!=null){
                dbString += cursor.getString(cursor.getColumnIndex(Constants.TABLE_COLUMN_FOR_NAME));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }



}