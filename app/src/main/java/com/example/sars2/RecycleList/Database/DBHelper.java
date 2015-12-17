package com.example.sars2.RecycleList.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sars2.RecycleList.Constants;
import com.example.sars2.RecycleList.GeneralPath;
import com.example.sars2.RecycleList.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sars2 on 12.12.2015.
 */
public  class DBHelper extends SQLiteOpenHelper  {
    GeneralPath generalPath;
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DATABASE_FILE_NAME, factory, Constants.DATABASE_VERSION_1);
         generalPath = GeneralPath.getInstance();
         SQLiteDatabase db = getReadableDatabase();
         generalPath.setPath(db.getPath());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Constants.PERSONS_TABLE_NAME + "(" +
                "" + Constants.TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + Constants.TABLE_COLUMN_FOR_NAME + " TEXT, "+
                "" + Constants.TABLE_COLUMN_FOR_INFO + " TEXT )";
        db.execSQL(query);
        generalPath.setPath(db.getPath());
    }

    public void deleteTable (){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + Constants.PERSONS_TABLE_NAME);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + Constants.PERSONS_TABLE_NAME);
        onCreate(db);
        generalPath.setPath(db.getPath());
    }

    public void addPerson (Person person) {
        ContentValues values = new ContentValues();
        values.put(Constants.TABLE_COLUMN_FOR_NAME, person.getName());
        values.put(Constants.TABLE_COLUMN_FOR_INFO, person.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(Constants.PERSONS_TABLE_NAME, null, values);

    }

    public List getPersonsList (){

        List <Person> persons = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + Constants.PERSONS_TABLE_NAME;
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