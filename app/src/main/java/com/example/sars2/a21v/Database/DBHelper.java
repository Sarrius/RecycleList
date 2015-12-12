package com.example.sars2.a21v.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.sars2.a21v.Constants;
import com.example.sars2.a21v.Person;
import java.util.List;

/**
 * Created by sars2 on 12.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private List<Person> persons;

       private String query = "CREATE TABLE " + Constants.DATABASE_TABLE_NAME + "(" +
            Constants.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            Constants.TABLE_COLUMN_FOR_NAME + " TEXT " +
            Constants.TABLE_COLUMN_FOR_INFO + " TEXT " +
            ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.DATABASE_TABLE_NAME);
        onCreate(db);
    }

    public void addPerson (Person person){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TABLE_COLUMN_FOR_NAME, person.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Constants.DATABASE_TABLE_NAME, null, contentValues);
        db.close();
    }


}
