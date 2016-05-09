package com.example.luis.panaderia.Otros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

   String crearTablaUsuarios = "CREATE TABLE IF NOT EXISTS 'usuarios' (" +
            "  'id' INTEGER NOT NULL PRIMARY KEY," +
            "  'usuario' TEXT NOT NULL UNIQUE," +
            "  'contrasenya' TEXT NOT NULL" +
            "  );";

    String crearTablaPedidos = "CREATE TABLE IF NOT EXISTS 'pedidos' (" +
            "  'id' INTEGER NOT NULL PRIMARY KEY," +
            "  'usuarioId' TEXT NOT NULL," +
            "  'pan' TEXT NOT NULL," +
            "  'tamano' TEXT NOT NULL," +
            "  'complemento' TEXT NOT NULL," +
            "  'unidades' INTEGER NOT NULL," +
            "  'precio' INTEGER NOT NULL," +
            "  'imagen' INTEGER NOT NULL," +
            "   FOREIGN KEY('usuarioId') REFERENCES usuarios('id') ON DELETE CASCADE" +
            "  );";

    public SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory basededatos, int version){
        super(contexto, nombre, basededatos, version);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTablaUsuarios);
        db.execSQL(crearTablaPedidos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
