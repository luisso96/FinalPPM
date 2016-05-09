package com.example.mati.holamundo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Hola extends Activity {
    public static int COD_RESPUESTA = 0;
    TextView elSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola);
        final EditText miTexto = (EditText) findViewById(R.id.miTxt);
        final Button miBoton = (Button) findViewById(R.id.miBtn);
        elSaludo = (TextView) findViewById(R.id.miLbl);

        MediaPlayer miMusica= MediaPlayer.create(getApplicationContext(),R.raw.dragonmiedo);
        miMusica.start();

        //Borrar el texto inicial del EditText
        miTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                miTexto.setText("");
            }
        });
        miBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(Hola.this, Pantalla2.class);
                Bundle miBundle = new Bundle();
                String mensajePaso = "Te saludo " + miTexto.getText();
                miBundle.putString("TEXTO", mensajePaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
                //elSaludo.setText(mensajePaso);
            }

        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this,"onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this,"onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this,"onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this,"onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"onDestroy", Toast.LENGTH_SHORT).show();
    }


    /*protected void showToast (CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    protected void showAlert (CharSequence text) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok, null);
    }*/

    public void onActivityResult(int cod_resp, int cod_result, Intent intent) {
        if (cod_result == RESULT_OK) {
            Bundle otroBundle = intent.getExtras();
            elSaludo.setText(otroBundle.getString("DEVUELTO"));
        }
    }
}