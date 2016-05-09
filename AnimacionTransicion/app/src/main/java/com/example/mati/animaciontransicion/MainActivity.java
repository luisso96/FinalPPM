package com.example.mati.animaciontransicion;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable miAnimacion;
    TransitionDrawable miTransicion;
    ImageView imagen1;
    ImageView imagen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen1 = (ImageView) findViewById(R.id.img1);
        imagen2 = (ImageView) findViewById(R.id.img2);
        miTransicion = (TransitionDrawable)


    }

}
