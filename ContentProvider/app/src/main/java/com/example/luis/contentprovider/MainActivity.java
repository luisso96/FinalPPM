package com.example.luis.contentprovider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listaContactos;

    String[] contactos;
    String linea;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Lista contactos");

        listaContactos = (ListView)findViewById(R.id.lsContactos);


        // la primera vez no lista los contactos porque aun no hay permiso, al abrir la app la segunda vez
        // ya los lista al tener permisos
        solicitarPermisoVerContactos();
    }


    public void verContactos(){

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);

        int i=0;
        int num;

        if(cursor!=null)
            num = cursor.getCount();
        else
            num = 0;

        contactos = new String[num];

        if(num > 0) {
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String telefono = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                linea = nombre + " " + telefono;
                contactos[i] = linea;
                i++;
            }
        }
        if(cursor != null)
            cursor.close();

        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactos);
        listaContactos.setAdapter(miAdaptador);
    }



    // SOLO ES IMPRESCINDIBLE EN API 23
    // SOLICITA AL USUARIO EL PERMISO

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private void solicitarPermisoVerContactos() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                mostrarConfirmacion("Debes permitir el acceso a tus contactos",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS} , REQUEST_CODE_ASK_PERMISSIONS);
            verContactos();
        }
        else
            verContactos();
    }

    private void mostrarConfirmacion(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }
}
