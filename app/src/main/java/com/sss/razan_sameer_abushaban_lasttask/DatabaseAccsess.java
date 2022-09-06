package com.sss.razan_sameer_abushaban_lasttask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccsess {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static DatabaseAccsess instance;

    private DatabaseAccsess(Context context){
        this.openHelper = new MyDatabases(context);

    }
    public static DatabaseAccsess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccsess(context);
        }
        return instance;
    }

    public void open(){
        this.database = this.openHelper.getReadableDatabase();
    }
    public void close(){
        if (this.database!= null){
            this.database.close();
        }

    }



    //دالة اضـالفة
    public boolean insertCar(Car car){
        ContentValues values = new ContentValues();
        values.put(MyDatabases.CAR_CLN_MODEL, car.getModel());
        values.put(MyDatabases.CAR_CLN_COLOR, car.getColor());
        values.put(MyDatabases.CAR_CLN_DPL, car.getDpl());
        values.put(MyDatabases.CAR_CLN_IMAGE, car.getImage());
        values.put(MyDatabases.CAR_CLN_DESCRIPTION, car.getDescription());

        long result =   database.insert(MyDatabases.CAR_TAB_NAME,null,values);
        return  result !=-1;

    }
    //دالة تعديل
    public boolean updateCar(Car car){
        ContentValues values = new ContentValues();
        values.put(MyDatabases.CAR_CLN_MODEL, car.getModel());
        values.put(MyDatabases.CAR_CLN_COLOR, car.getColor());
        values.put(MyDatabases.CAR_CLN_DPL, car.getDpl());
        values.put(MyDatabases.CAR_CLN_IMAGE, car.getImage());
        values.put(MyDatabases.CAR_CLN_DESCRIPTION, car.getDescription());


        String args[] = new String[]{car.getId()+""};
        long result =   database.update(MyDatabases.CAR_TAB_NAME,values,"id=?", args);
        return  result  > 0;

    }

    // ارجاع عدد الصفوف في جدول معين
    public long getCarsCount(){
        return   DatabaseUtils.queryNumEntries(database,MyDatabases.CAR_TAB_NAME);

    }

    // دالة الحدف
    public boolean deleteCar(Car car){

        String args[] = new String[]{car.getId()+""};
        int result =   database.delete(MyDatabases.CAR_TAB_NAME,"id=?", args);
        return  result > 0;

    }
    //دالة الاسترجاع
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ MyDatabases.CAR_TAB_NAME, null);

        if (cursor != null && cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabases.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_COLOR));
                double dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabases.CAR_CLN_DPL));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_DESCRIPTION));

                Car c = new Car(id, model, color, dpl, image, description);
                cars.add(c);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }

    // دالة البحث
    public ArrayList<Car> getCars(String modelSearch){
        ArrayList<Car> cars = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabases.CAR_TAB_NAME+" WHERE "+MyDatabases.CAR_CLN_MODEL+" =? ",
                new String[]{modelSearch});

        if (cursor != null && cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabases.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_COLOR));
                double dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabases.CAR_CLN_DPL));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_DESCRIPTION));

                Car c = new Car(id, model, color, dpl, image, description);
                cars.add(c);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }


    public Car getCar(int carId){
        Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabases.CAR_TAB_NAME+" where "+MyDatabases.CAR_CLN_ID+" =?"
                ,  new String[]{String.valueOf(carId)});

        if (cursor != null && cursor.moveToFirst()){

                int id = cursor.getInt(cursor.getColumnIndex(MyDatabases.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_COLOR));
                double dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabases.CAR_CLN_DPL));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabases.CAR_CLN_DESCRIPTION));

                Car c = new Car(id, model, color, dpl, image, description);
            cursor.close();
            return c;
        }
        return null;
    }
}
