package com.example.luis.panaderia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.panaderia.Objetos.Usuario;
import com.example.luis.panaderia.Otros.SQLiteHelper;

public class IniciarActivity extends AppCompatActivity {

    String usu;
    EditText usuario, clave;
    Button iniciar;
    TextView enlaceRegistro;
    int id;
    String usuarioElegido;

    Usuario[] listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
        this.setTitle("Iniciar sesion");

        usuario = (EditText)findViewById(R.id.etUsuario);
        clave = (EditText)findViewById(R.id.etContrasenya);
        iniciar = (Button)findViewById(R.id.btIniciar);
        enlaceRegistro = (TextView)findViewById(R.id.tvEnlaceRegistro);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        enlaceRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarseActivity.class);
                startActivity(intent);
            }
        });
    }


    public void iniciarSesion() {

        usu = usuario.getText().toString();
        String con = clave.getText().toString();

        Usuario[] usuarios = listaUsuarios();

        boolean existe = false;

        for (Usuario usuario1 : usuarios) {
            if (usuario1.getUsuario().equals(usu) && usuario1.getContrasenya().equals(con)) {
                existe = true;
                id = usuario1.getId();
                usuarioElegido = usuario1.getUsuario();
            }
        }


        if (usu.equals("admin") && usu.equals("admin")) {
            Toast.makeText(this, "INICIANDO COMO ADMIN", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent);
        }
        else {

            if (validar() && existe) {
                iniciar.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(IniciarActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autentificando...");
                progressDialog.show();

                new android.os.Handler().postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        Bundle bundle = new Bundle();
                        bundle.putInt("IDUSUARIO", id);
                        bundle.putString("NOMUSUARIO", usuarioElegido);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            } else
                Toast.makeText(this, "Datos inexistentes/erroneos", Toast.LENGTH_SHORT).show();
        }

    }


    public boolean validar() {
        boolean valido = true;

        String usu = usuario.getText().toString();
        String con = clave.getText().toString();

        if (usu.isEmpty()) {
            usuario.setError("Introduce un usuario");
            valido = false;
        }
        else
            usuario.setError(null);

        if (con.isEmpty() || con.length() < 4 || con.length() > 10) {
            clave.setError("Entre 4-10 caracteres alfanuméricos");
            valido = false;
        }
        else
            clave.setError(null);

        return valido;
    }

    public Usuario[] listaUsuarios() {

        SQLiteHelper usuariosSqliteHelper = new SQLiteHelper(this, "panaderia.sqlite", null, 1);
        SQLiteDatabase bd = usuariosSqliteHelper.getReadableDatabase();

        if(bd != null) {
            Cursor cursor = bd.rawQuery("SELECT * FROM usuarios", null);
            int cantidad = cursor.getCount();
            int i = 0;
            listaUsuarios = new Usuario[cantidad];

            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(0);
                    String usuario = cursor.getString(1);
                    String contrasenya   = cursor.getString(2);

                    listaUsuarios[i] = new Usuario(id, usuario, contrasenya);
                    i++;
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }

        return listaUsuarios;
    }

}