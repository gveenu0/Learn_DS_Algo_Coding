package com.softtechnotech.learndsalgocoding;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student_all.db";
    public static final String TABLE_NAME = "student_info";
    public static final String TABLE_NAME_IMAGE = "student_logo";
    public static final String COL_1 = "studentName";
    public static final String COL_2 = "studentMobile";
    public static final String COL_3 = "highestEducation";
    public static final String COL_4 = "educationField";
    public static final String COL_5 = "studentEmail";
    public static final String COL_IMG_1 = "KEY_NAME";
    public static final String COL_IMG_2 = "KEY_IMAGE";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table IF NOT EXISTS " +TABLE_NAME + "(studentEmail TEXT PRIMARY KEY, studentName TEXT, studentMobile TEXT, highestEducation TEXT, educationField TEXT)"
        );
        db.execSQL("create table IF NOT EXISTS " +TABLE_NAME_IMAGE + "(KEY_NAME TEXT PRIMARY KEY, KEY_IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String studentName, String studentMobile, String highestEducation, String educationField, String studentEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, studentEmail);
        contentValues.put(COL_1, studentName);
        contentValues.put(COL_2, studentMobile);
        contentValues.put(COL_3, highestEducation);
        contentValues.put(COL_4, educationField);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean insertImage(String keyName, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IMG_1, keyName);
        contentValues.put(COL_IMG_2, image);
        long result = db.insert(TABLE_NAME_IMAGE, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData(String studentEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from student_info where studentEmail = '"+studentEmail+"'", null);
    }

    public Cursor getInfo(String studentEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from student_info where studentEmail = '"+studentEmail+"'", null);
    }

    public Cursor getImageInfo(String key_name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from student_logo where KEY_NAME = '"+key_name+"'", null);
    }
    public boolean updateData(String studentName, String studentMobile, String highestEducation, String educationField, String studentEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, studentName);
        contentValues.put(COL_2, studentMobile);
        contentValues.put(COL_3, highestEducation);
        contentValues.put(COL_4, educationField);
        long result = db.update(TABLE_NAME, contentValues, "studentEmail = ?", new String[] {studentEmail});
        return result != -1;
    }

    public boolean updateImage(String keyName, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_IMG_2, image);
        long result = db.update(TABLE_NAME_IMAGE, contentValues, "KEY_NAME = ?", new String[] {keyName});
        return result != -1;
    }
    public boolean deleteData (String studentEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "studentEmail = ?", new String[] {studentEmail}) > 0;
    }
}
