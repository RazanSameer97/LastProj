package com.sss.razan_sameer_abushaban_lasttask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDatabases extends SQLiteAssetHelper {
    public static final String DB_NAME = "Cars.db";
    public static final int DB_VERSION = 1;
    public static final String CAR_TAB_NAME = "car";
    public static final String CAR_CLN_ID = "id";
    public static final String CAR_CLN_MODEL = "model";
    public static final String CAR_CLN_COLOR = "color";
    public static final String CAR_CLN_DESCRIPTION = "description";
    public static final String CAR_CLN_IMAGE = "image";
    public static final String CAR_CLN_DPL = "distancePerLetter";


    public MyDatabases(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
}

//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("Create Table "+CAR_TAB_NAME+" ("+CAR_CLN_ID+" Intteger " +
//                "Primary key Autoincrement, " +
//                ""+CAR_CLN_MODEL+" Text , "+CAR_CLN_COLOR+" text" + ", "+CAR_CLN_DPL+" Real)");
//    }

//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("Drop table if Exists "+CAR_TAB_NAME+"");
//        onCreate(sqLiteDatabase);
//    }



