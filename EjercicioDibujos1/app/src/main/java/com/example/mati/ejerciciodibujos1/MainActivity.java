package com.example.mati.ejerciciodibujos1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
    }

    class MiDibujo extends View {
        public MiDibujo(Context c){

            super(c);
        }

        protected void onDraw(Canvas lienzo){


            Paint miPincel= new Paint();

            lienzo.drawColor(Color.BLACK);

            miPincel.setColor(Color.parseColor("#FF00FF"));
            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setStrokeWidth(8);
            lienzo.drawCircle(lienzo.getWidth() / 2, lienzo.getHeight() / 2, 250, miPincel);

            miPincel.setStyle(Paint.Style.FILL);
            miPincel.setColor(Color.parseColor("#FF0000")); //Cuadrado rojo
            lienzo.drawRect(150, 150, 250, 250, miPincel);
            miPincel.setColor(Color.parseColor("#00FF00")); //Cuadrado Verder
            lienzo.drawRect(lienzo.getWidth() / 2 - 50, lienzo.getHeight() - 250, lienzo.getWidth() / 2 + 50, lienzo.getHeight() - 150, miPincel);
            miPincel.setColor(Color.parseColor("#0000FF")); //Cuadrado blue
            lienzo.drawRect(lienzo.getWidth() - 250, 150, lienzo.getWidth() - 150, 250, miPincel);
            miPincel.setColor(Color.parseColor("#00FFFF"));
            lienzo.drawLine(lienzo.getWidth() / 2,lienzo.getHeight(),lienzo.getWidth(), 250, miPincel);

            miPincel.setColor(Color.WHITE);
            lienzo.drawPoint(lienzo.getWidth()/2,lienzo.getHeight()/2,miPincel);

        }
    }
}
