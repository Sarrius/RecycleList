package com.example.sars2.a21v.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sars2.a21v.Constants;
import com.example.sars2.a21v.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sars2 on 12.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context,  SQLiteDatabase.CursorFactory factory) {
        super(context, Constants.DATABASE_FILE_NAME, factory, Constants.DATABASE_VERSION_1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + Constants.PERSONS_TABLE_NAME + "(" +
                "" + Constants.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + Constants.TABLE_COLUMN_FOR_NAME + " TEXT, "+
                "" + Constants.TABLE_COLUMN_FOR_INFO + " TEXT )";
        db.execSQL(query);
    }

    public void deleteTable (){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + Constants.PERSONS_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    public void addPerson (Person person) {

        ContentValues values = new ContentValues();
        values.put(Constants.TABLE_COLUMN_FOR_NAME, person.getName());
        values.put(Constants.TABLE_COLUMN_FOR_INFO, person.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Constants.PERSONS_TABLE_NAME, null, values);


    }

    public void deletePerson (String personName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Constants.PERSONS_TABLE_NAME + " WHERE " +
                Constants.TABLE_COLUMN_FOR_NAME + "\"" + personName + "\";" );

    }

    public List getPersonsList (){

        List <Person> persons = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + Constants.PERSONS_TABLE_NAME;
        //String query = "SELECT * FROM " + Constants.DATABASE_TABLE_NAME + " WHERE 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex(Constants.TABLE_COLUMN_FOR_NAME))!=null){
                Person person = new Person(null, null);
                person.setName(cursor.getString(cursor.getColumnIndex(Constants.TABLE_COLUMN_FOR_NAME)));
                person.setDescription(cursor.getString(cursor.getColumnIndex(Constants.TABLE_COLUMN_FOR_INFO)));
                persons.add(person);
                cursor.moveToNext();

            }
        }

        db.close();
       return persons;
    }


}