package com.example.luis.panaderia;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.panaderia.Otros.SQLiteHelper;

public class RegistrarseActivity extends AppCompatActivity {

    EditText usuario, contrasenya;
    Button crearCuenta;
    TextView enlaceIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        this.setTitle("Registrarse");

        usuario = (EditText)findViewById(R.id.etUsuario2);
        contrasenya = (EditText)findViewById(R.id.etContrasena2);
        crearCuenta = (Button)findViewById(R.id.btRegistrarse);
        enlaceIniciarSesion = (TextView)findViewById(R.id.tvEnlaceIniciar);

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });

        enlaceIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registrarse() {

        if (!validar()) {
            registroFallado();
            return;
        }

        crearCuenta.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistrarseActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creando cuenta...");
        progressDialog.show();

        String nom = usuario.getText().toString();
        String cla = contrasenya.getText().toString();

        insertarUsuario(nom, cla);


        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                registroCorrecto();
                progressDialog.dismiss();
            }
        }, 3000);
    }


    public void registroCorrecto() {
        crearCuenta.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void registroFallado() {
        crearCuenta.setEnabled(true);
    }


    public boolean validar() {

        boolean valido = true;

        String nom = usuario.getText().toString();
        String cla = this.contrasenya.getText().toString();

        if (nom.isEmpty() || nom.length() < 6) {
            usuario.setError("Minimo 6 carácteres alfanuméricos");
            valido = false;
        }
        else
            usuario.setError(null);

        if (cla.isEmpty() || contrasenya.length() < 6) {
            contrasenya.setError("Minimo 6 carácteres");
            valido = false;
        }
        else
            contrasenya.setError(null);

        return valido;
    }


    public void insertarUsuario(String usuario, String contrasenya){

        SQLiteHelper admin = new SQLiteHelper(this,"panaderia.sqlite", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("usuario" , usuario);
        contentValues.put("contrasenya"   , contrasenya);

        bd.insert("usuarios", null, contentValues);
        bd.close();

        Toast.makeText(this, "Usuario creado Correctamente", Toast.LENGTH_SHORT).show();
    }

}
