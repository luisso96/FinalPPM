package com.example.mati.lalmendros;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mati on 9/11/15.
 */



public class PantallaFactura extends AppCompatActivity {

    ImageView pizzaImagen;
    TextView pizzaTXV;
    TextView txtPrecio;
    TextView txtExtra;
    TextView txtUnidades;
    TextView txtEnvio;
    TextView txtCoste;
    TextView txtCalculo;

    String [] informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        pizzaImagen = (ImageView) findViewById (R.id.imagePizza);
        pizzaTXV = (TextView) findViewById(R.id.txtPizza);
        txtPrecio = (TextView) findViewById(R.id.txtPrecio);
        txtExtra = (TextView) findViewById(R.id.txtExtra);
        txtUnidades = (TextView) findViewById(R.id.txtUnidades);
        txtEnvio = (TextView) findViewById(R.id.txtEnvio);
        txtCoste = (TextView) findViewById(R.id.txtCoste);
        txtCalculo = (TextView) findViewById(R.id.txtCoste);

        Bundle  bundleRecoger = getIntent().getExtras();

        informacion = bundleRecoger.getStringArray("TEXTO");

        //relleno de las imagenes
        if ( informacion[0] == "MARGARITA") {
            pizzaImagen.setImageResource(R.drawable.pizza1);
        } else if (informacion[0] == "TRES QUESOS") {
            pizzaImagen.setImageResource(R.drawable.pizza2);
        } else if (informacion[0] == "BARBACOA") {
            pizzaImagen.setImageResource(R.drawable.pizza3);
        } else {
            pizzaImagen.setImageResource(R.drawable.logopizza);
        }
        //relelno todos los Textview
        pizzaTXV.setText("Pizza: " + informacion[0]);
        txtPrecio.setText("Precio Base: " + informacion[1]+"€");
        txtExtra.setText("Extra: " + informacion[2]+"€");
        txtUnidades.setText("Unidades: " + informacion[3]);
        if (informacion[4] == "true"){
            txtEnvio.setText("Unidades: Domicilio");
        } else if (informacion[4] == "false") {
            txtEnvio.setText("Unidades: Local");
        } else {
            txtEnvio.setText("Unidades: Local");
        }
        txtCoste.setText("Coste Final: " + informacion[5]+"€");
        if (informacion[4] == "true"){
            txtCalculo.setText("(("+informacion[1]+" + "+informacion[2]+") * "+informacion[3]+")10%)");
        } else if (informacion[4] == "false") {
            txtCalculo.setText("(("+informacion[1]+" + "+informacion[2]+") * "+informacion[3]+")");
        } else {
            txtCalculo.setText("(("+informacion[1]+" + "+informacion[2]+") * "+informacion[3]+")");
        }






        final String completarSaludo=bundleRecoger.getString("TEXTO");


    }
}
