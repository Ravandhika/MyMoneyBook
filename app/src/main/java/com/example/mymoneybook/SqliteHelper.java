package com.example.mymoneybook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

public class SqliteHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "SaveItDatabase.db";
    private static final int DATABASE_VERSION = 1;

    //  tabel flow
    private static final String TABLE_CASH_FLOW = "cash_flow";
    private static final String COLUMN_ID_CASH_FLOW = "id";
    private static final String COLUMN_TANGGAL = "tanggal";
    private static final String COLUMN_NOMINAL = "nominal";
    private static final String COLUMN_TIPE = "tipe";
    private static final String COLUMN_KETERANGAN = "keterangan";

    //  tabel user
    private static final String TABLE_USER = "user";
    private static final String COLUMN_ID_USER = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_cash_flow =
                "CREATE TABLE " + TABLE_CASH_FLOW +
                        " (" + COLUMN_ID_CASH_FLOW + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TANGGAL + " TEXT, " +
                        COLUMN_NOMINAL + " INTEGER, " +
                        COLUMN_TIPE + " TEXT," +
                        COLUMN_KETERANGAN + " TEXT);";

        String create_user =
                "CREATE TABLE " + TABLE_USER +
                        " (" + COLUMN_ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT);";

        db.execSQL(create_cash_flow);
        db.execSQL(create_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CASH_FLOW);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
    }

    public void tambahPemasukan(String tanggal, int nominal, String tipe, String keterangan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TANGGAL, tanggal);
        cv.put(COLUMN_NOMINAL, nominal);
        cv.put(COLUMN_TIPE, tipe);
        cv.put(COLUMN_KETERANGAN, keterangan);

        long result = db.insert(TABLE_CASH_FLOW, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        }
    }
    public void tambahPengeluaran(String tanggal, int nominal, String tipe, String keterangan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TANGGAL, tanggal);
        cv.put(COLUMN_NOMINAL, nominal);
        cv.put(COLUMN_TIPE, tipe);
        cv.put(COLUMN_KETERANGAN, keterangan);

        long result = db.insert(TABLE_CASH_FLOW, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    public void tambahUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USER, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(context, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_CASH_FLOW;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean user(String username, String password) {
        String[] columns = {COLUMN_ID_USER};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_USERNAME + "=?" + " AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
            return true;
        }

        else {
            return false;
        }
    }
}