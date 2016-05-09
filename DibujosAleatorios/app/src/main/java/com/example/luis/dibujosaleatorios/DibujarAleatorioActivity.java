package com.example.luis.dibujosaleatorios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class DibujarAleatorioActivity extends AppCompatActivity {

    private VistaFormasAleatorias areaDibujo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar_aleatorio);
        areaDibujo = (VistaFormasAleatorias) findViewById(R.id.viAreaDibujo);
    }

    public void reDibujar(View clickedButton) {
        areaDibujo.invalidate();
    }


}