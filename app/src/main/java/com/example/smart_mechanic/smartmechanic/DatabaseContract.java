package com.example.smart_mechanic.smartmechanic;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

/**
 * Created by Mary on 11/5/2015.
 */
public final class DatabaseContract {
    private static final String DATABASE_NAME = "CarSounds.db";
    private static final int DBASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String LONG_TYPE = " LONG";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static abstract class CarTypeEntries implements BaseColumns {
        public static final String TABLE_NAME = "Car";
        public static final String _ID = "ID";
        public static final String MAKE = "Make";
        public static final String MODEL = "Model";
        public static final String YEAR = "Year";
        public static final String POSITION = "Position";
        public static final String[] ALLCOLUMNS = {_ID,MAKE,MODEL,YEAR,POSITION};
    }
    private static final String SQL_CREATE_CAR_TABLE =
            "CREATE TABLE " + CarTypeEntries.TABLE_NAME + " (" +
                    CarTypeEntries._ID + " INTEGER PRIMARY KEY," +
                    CarTypeEntries.MAKE + TEXT_TYPE + COMMA_SEP +
                    CarTypeEntries.MODEL + TEXT_TYPE + COMMA_SEP +
                    CarTypeEntries.YEAR + INT_TYPE + COMMA_SEP +
                    CarTypeEntries.POSITION + INT_TYPE +
                    ");";

    private final Context context;

    private DatabaseHelper myDBHelper;


}
