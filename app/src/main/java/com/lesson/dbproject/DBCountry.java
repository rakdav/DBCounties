package com.lesson.dbproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBCountry
{
    private static final String DATABASE_NAME="countries.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="tableCountries";
    private static final String COLUMN_ID="ID";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_CAPITAL="capital";
    private static final String COLUMN_POPULATION="population";

    private SQLiteDatabase database;

    public DBCountry(Context context) {
        OpenHelper openHelper=new OpenHelper(context);
        database=openHelper.getWritableDatabase();
    }
    public long Insert(long id,String name,String capital,int population)
    {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_ID,id);
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_CAPITAL,capital);
        cv.put(COLUMN_POPULATION,population);
        return database.insert(TABLE_NAME,null,cv);
    }
    public int Update(Country country){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,country.getName());
        cv.put(COLUMN_CAPITAL,country.getCapital());
        cv.put(COLUMN_POPULATION,country.getPopulation());
        return database.update(TABLE_NAME,cv,COLUMN_ID+"=?",
                new String[]{String.valueOf(country.getID())});
    }
    public void deleteAll()
    {
        database.delete(TABLE_NAME,null,null);
    }
    public void deleteById(long id)
    {
        database.delete(TABLE_NAME,COLUMN_ID+"=?",
                new String[] {String.valueOf(id)});
    }

    public ArrayList<Country> selectAll()
    {
        Cursor cursor=database.query(TABLE_NAME,null,null,
                null,null,null,null);
        ArrayList<Country> list=new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast())
        {
            do
            {
                long id=cursor.getLong(0);
                String name=cursor.getString(1);
                String capital=cursor.getString(2);
                int population=cursor.getInt(3);
                list.add(new Country(id,name,capital,population));
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public Country select(long id)
    {
        Cursor cursor=database.query(TABLE_NAME,null,COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);
        cursor.moveToFirst();
        String name=cursor.getString(1);
        String capital=cursor.getString(2);
        int population=cursor.getInt(3);
        return new Country(id,name,capital,population);
    }
    private class OpenHelper extends SQLiteOpenHelper{
        public OpenHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query="CREATE TABLE "+TABLE_NAME+"("+
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "Name TEXT,"+
                    "Capital TEXT,"+
                    "Population INTEGER)";
            db.execSQL(query);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
        }
    }
}
