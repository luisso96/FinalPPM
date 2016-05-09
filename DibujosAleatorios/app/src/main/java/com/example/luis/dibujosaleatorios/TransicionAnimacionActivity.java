package com.example.luis.dibujosaleatorios;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class TransicionAnimacionActivity extends Activity {
    AnimationDrawable animacion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicion_animacion);

        ImageView imagenTransicion = (ImageView)findViewById(R.id.imageAnimacion);
        ImageView imagen2 = (ImageView)findViewById(R.id.imagenTransicion);

        // codigo animacion
        animacion = (AnimationDrawable) getResources().getDrawable(R.drawable.animacion);
        ImageView imagen = new ImageView(this);
        imagen.setBackgroundColor(Color.WHITE);
        imagen.setImageDrawable(animacion);


        // codigo transicion
        TransitionDrawable miTransicion = (TransitionDrawable) getResources().getDrawable(R.drawable.transicion);
        imagenTransicion.setImageDrawable(miTransicion);
        imagen2.setImageDrawable(animacion);

        miTransicion.startTransition(2000);


    }

    // evento touch de animacion
    public boolean onTouchEvent(MotionEvent evento) {
        if (evento.getAction() == MotionEvent.ACTION_DOWN) {
            animacion.start();
            return true;
        }
        return super.onTouchEvent(evento);
    }
}