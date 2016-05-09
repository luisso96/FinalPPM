package com.example.luis.dibujosaleatorios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button figurasAleatorias = (Button)findViewById(R.id.btFigurasAleatorias);
        Button figurasPulsar = (Button)findViewById(R.id.btFigurasPulsar);
        Button figurasSpinner = (Button)findViewById(R.id.btSpinnerFiguras);
        Button transicion = (Button)findViewById(R.id.btTransicion);


        figurasAleatorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, DibujarAleatorioActivity.class);
                startActivity(activityIntent);
            }
        });

        figurasPulsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, DibujarPulsarActivity.class);
                startActivity(activityIntent);
            }
        });

        figurasSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, SpinnerFigurasActivity.class);
                startActivity(activityIntent);
            }
        });

        transicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityIntent = new Intent(MainActivity.this, TransicionAnimacionActivity.class);
                startActivity(activityIntent);
            }
        });


    }
}
