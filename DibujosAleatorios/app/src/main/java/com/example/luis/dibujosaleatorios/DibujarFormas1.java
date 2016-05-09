package com.example.luis.dibujosaleatorios;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class DibujarFormas1 extends Activity {

    private VistaFormasAleatorias mDrawingArea;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibujar_formas1);
        mDrawingArea = (VistaFormasAleatorias) findViewById(R.id.drawing_area);
    }


    public void redraw(View clickedButton) {
        mDrawingArea.invalidate();
    }


}