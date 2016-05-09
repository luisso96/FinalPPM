package com.example.mati.formulario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        final Button btnFormulario = (Button) findViewById(R.id.btnFormulario);

        txtNombre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txtNombre.setText("");
            }
        });

        btnFormulario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this, Pantalla2.class);
                Bundle miBundle = new Bundle();
                String informacionPaso []={"2"};
                miBundle.putStringArray("TEXTO", informacionPaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
                //elSaludo.setText(mensajePaso);
            }

        });


    }


}
