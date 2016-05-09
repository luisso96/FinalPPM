package com.example.mati.basedatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mati on 11/01/16.
 */
public class ClientesSQLiteHelper extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Clientes (codigo INTEGER, nombre TEXT, telefono TEXT)";

    public ClientesSQLiteHelper (Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super (context, name, factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
